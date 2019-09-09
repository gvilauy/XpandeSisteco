package org.xpande.sisteco.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.acct.Doc;
import org.compiere.model.*;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SavepointVO;
import org.compiere.util.TimeUtil;
import org.xpande.core.utils.PriceListUtils;
import org.xpande.sisteco.model.MZSistecoConfig;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 9/9/19.
 */
public class FixGenericSisteco extends SvrProcess {

    MZSistecoConfig sistecoConfig = null;

    private int zSisteconInterfacePazosID = -1;

    @Override
    protected void prepare() {

        ProcessInfoParameter[] para = getParameter();

        for (int i = 0; i < para.length; i++){

            String name = para[i].getParameterName();

            if (name != null){
                if (para[i].getParameter() != null){
                    if (name.trim().equalsIgnoreCase("Record_ID")){
                        this.zSisteconInterfacePazosID = ((BigDecimal)para[i].getParameter()).intValueExact();
                    }
                }
            }
        }

        this.sistecoConfig = MZSistecoConfig.getDefault(getCtx(), null);
    }

    @Override
    protected String doIt() throws Exception {

        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{

            // Producto a considerar en los documentos de venta
            if (this.sistecoConfig.getProdVtasCredPOS_ID() <= 0){
                return null;
            }
            MProduct product = new MProduct(getCtx(), this.sistecoConfig.getProdVtasCredPOS_ID(), null);
            if ((product == null) || (product.get_ID() <= 0)){
                return null;
            }

            sql = " select a.ad_client_id, a.ad_org_id, cvta.st_numeroticket, cvta.datetrx, cfe.st_descripcioncfe, " +
                    " coalesce(cfe.st_seriecfe,'') as st_seriecfe, cfe.st_numerocfe, coalesce(cfe.st_tipocfe,'') as st_tipocfe, " +
                    " a.st_totalmppagomoneda as st_montopagocc, cfel.st_rut, vcli.ST_CodigoCC, coalesce(bp.name, bpcc.name) as st_nombrecc " +
                    " from z_sisteco_tk_vtactacte a " +
                    " inner join z_sisteco_tk_cvta cvta on a.z_sisteco_tk_cvta_id = cvta.z_sisteco_tk_cvta_id " +
                    " inner join z_sisteco_tk_cfecab cfe on cvta.z_sisteco_tk_cvta_id = cfe.z_sisteco_tk_cvta_id " +
                    " left outer join z_sisteco_tk_cfelinea cfel on cvta.z_sisteco_tk_cvta_id = cfel.z_sisteco_tk_cvta_id " +
                    " left outer join z_sisteco_tk_vtacliente vcli on cvta.z_sisteco_tk_cvta_id = vcli.z_sisteco_tk_cvta_id " +
                    " left outer join c_bpartner bp on cfel.st_rut = bp.taxid " +
                    " left outer join c_bpartner bpcc on cast(vcli.st_codigocc as character varying(40)) = bpcc.value " +
                    " where cvta.st_estadoticket::text = 'F' " +
                    " and cvta.z_sistecointerfacepazos_id =" + this.zSisteconInterfacePazosID +
                    " ORDER BY a.datetrx, cvta.st_numeroticket ";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            while(rs.next()){

                BigDecimal amtTotal = rs.getBigDecimal("st_montopagocc");
                if (amtTotal == null) amtTotal = Env.ZERO;
                if (amtTotal.compareTo(Env.ZERO) == 0){
                    continue;
                }
                if (amtTotal.compareTo(Env.ZERO) < 0){
                    amtTotal = amtTotal.negate();
                }

                // Determino tipo de documento segun tipo cfe obtenido
                int cDocTypeID = 0;
                String tipoCFE = rs.getString("st_tipocfe").trim();

                // e-ticket o e-factura
                if ((tipoCFE.equalsIgnoreCase("101")) || (tipoCFE.equalsIgnoreCase("111"))){
                    cDocTypeID = this.sistecoConfig.getDefaultDocPosARI_ID();
                }
                // e-ticket nc o e-factura nc
                else if ((tipoCFE.equalsIgnoreCase("102")) || (tipoCFE.equalsIgnoreCase("112"))){
                    cDocTypeID = this.sistecoConfig.getDefaultDocPosARC_ID();
                }

                if (cDocTypeID <= 0){
                    continue;
                }
                MDocType docType = new MDocType(getCtx(), cDocTypeID, null);

                int cBParnterID = 0;
                String whereClause = "";

                String taxID = rs.getString("st_rut");

                if ((taxID != null) && (!taxID.trim().equalsIgnoreCase(""))){
                    whereClause = " c_bpartner.taxID ='" + taxID + "'";
                }
                else{
                    if (rs.getInt("ST_CodigoCC") > 0){
                        whereClause = " c_bpartner.value ='" + String.valueOf(rs.getInt("ST_CodigoCC")) + "'";
                    }
                }
                int[] partnersIDs = MBPartner.getAllIDs(I_C_BPartner.Table_Name, whereClause, null);
                if (partnersIDs.length <= 0){
                    if (rs.getInt("ST_CodigoCC") > 0){
                        whereClause = " c_bpartner.taxid ='" + String.valueOf(rs.getInt("ST_CodigoCC")) + "'";
                        partnersIDs = MBPartner.getAllIDs(I_C_BPartner.Table_Name, whereClause, null);
                        if (partnersIDs.length <= 0){
                            continue;
                        }
                    }
                    else{
                        continue;
                    }
                }
                cBParnterID = partnersIDs[0];
                MBPartner partner = new MBPartner(getCtx(), cBParnterID, null);


                MBPartnerLocation[] partnerLocations = partner.getLocations(true);
                if (partnerLocations.length <= 0){
                    continue;
                }
                MBPartnerLocation partnerLocation = partnerLocations[0];

                MPaymentTerm paymentTerm = MPaymentTerm.getPaymentTermByDefault(getCtx(), null);
                if ((paymentTerm == null) || (paymentTerm.get_ID() <= 0)){
                    continue;
                }

                MInvoice invoice = new MInvoice(getCtx(), 0, get_TrxName());
                invoice.set_ValueOfColumn("AD_Client_ID", this.sistecoConfig.getAD_Client_ID());
                invoice.setAD_Org_ID(rs.getInt("AD_Org_ID"));
                invoice.setIsSOTrx(true);
                invoice.setC_DocTypeTarget_ID(cDocTypeID);
                invoice.setC_DocType_ID(cDocTypeID);
                String documentNo = rs.getString("st_seriecfe") + rs.getString("st_numerocfe");
                invoice.setDocumentNo(documentNo);
                invoice.setDescription("Generado desde POS. Numero de Ticket : " + rs.getString("st_numeroticket"));

                if (docType.getDocBaseType().equalsIgnoreCase(Doc.DOCTYPE_ARCredit)){
                    invoice.set_ValueOfColumn("ReferenciaCFE", "Referencia Comprobante SISTECO");
                }

                Timestamp fechaDoc = TimeUtil.trunc(rs.getTimestamp("datetrx"), TimeUtil.TRUNC_DAY);
                invoice.setDateInvoiced(fechaDoc);
                invoice.setDateAcct(fechaDoc);
                invoice.setC_BPartner_ID(partner.get_ID());
                invoice.setC_BPartner_Location_ID(partnerLocation.get_ID());
                invoice.setC_Currency_ID(142);
                invoice.setPaymentRule(X_C_Invoice.PAYMENTRULE_OnCredit);
                invoice.setC_PaymentTerm_ID(paymentTerm.get_ID());
                invoice.setTotalLines(amtTotal);
                invoice.setGrandTotal(amtTotal);

                MPriceList priceList = PriceListUtils.getPriceListByOrg(getCtx(), invoice.getAD_Client_ID(), invoice.getAD_Org_ID(),
                        invoice.getC_Currency_ID(), true, null, null);
                if ((priceList == null) || (priceList.get_ID() <= 0)){
                    continue;
                }

                invoice.setM_PriceList_ID(priceList.get_ID());
                invoice.setIsTaxIncluded(priceList.isTaxIncluded());
                invoice.set_ValueOfColumn("AmtSubtotal", amtTotal);
                invoice.set_ValueOfColumn("DocBaseType", docType.getDocBaseType());
                invoice.set_ValueOfColumn("EstadoAprobacion", "APROBADO");
                invoice.set_ValueOfColumn("TipoFormaPago", "CREDITO");
                invoice.saveEx();


                // Linea de Factura
                MInvoiceLine line = new MInvoiceLine(invoice);
                line.set_ValueOfColumn("AD_Client_ID", invoice.getAD_Client_ID());
                line.setAD_Org_ID(invoice.getAD_Org_ID());
                line.setM_Product_ID(product.get_ID());
                line.setC_UOM_ID(product.getC_UOM_ID());
                line.setQtyEntered(Env.ONE);
                line.setQtyInvoiced(Env.ONE);
                line.setPriceEntered(invoice.getTotalLines());
                line.setPriceActual(invoice.getTotalLines());
                line.setLineNetAmt(invoice.getTotalLines());
                line.set_ValueOfColumn("AmtSubTotal", invoice.getTotalLines());
                line.setTax();
                line.setTaxAmt();
                line.setLineNetAmt();
                line.saveEx();

                if (!invoice.processIt(DocAction.ACTION_Complete)){
                    String message = "";
                    if (invoice.getProcessMsg() != null) message = invoice.getProcessMsg();
                    System.out.println("No se pudo completar Invoice en Venta Cuenta Corriente Sisteco : " + message);
                }
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return "OK";
    }
}
