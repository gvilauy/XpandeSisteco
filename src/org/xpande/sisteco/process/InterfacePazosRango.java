package org.xpande.sisteco.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Trx;
import org.xpande.sisteco.model.MZSistecoConfig;
import org.xpande.sisteco.model.MZSistecoConfigOrg;
import org.xpande.sisteco.model.MZSistecoInterfacePazos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 9/8/17.
 */
public class InterfacePazosRango extends SvrProcess {

    private Timestamp startDate = null;
    private Timestamp endDate = null;
    private int adOrgID = 0;

    @Override
    protected void prepare() {

        ProcessInfoParameter[] para = getParameter();

        for (int i = 0; i < para.length; i++){

            String name = para[i].getParameterName();

            if (name != null){
                if (para[i].getParameter() != null){
                    if (name.trim().equalsIgnoreCase("DateTrx")){
                        this.startDate = (Timestamp)para[i].getParameter();
                        this.endDate = (Timestamp)para[i].getParameter_To();
                    }
                    else if (name.trim().equalsIgnoreCase("AD_Org_ID")){
                        this.adOrgID = ((BigDecimal)para[i].getParameter()).intValueExact();
                    }
                }
            }
        }

    }

    @Override
    protected String doIt() throws Exception {


        MZSistecoConfig sistecoConfig = MZSistecoConfig.getDefault(getCtx(), null);

        // Si indico organización, proceso solo para esta, sino proceso para todas las que tenga asociadas a SISTECO
        List<MZSistecoConfigOrg> orgList = sistecoConfig.getOrganizationsByOrg(this.adOrgID);

        for (MZSistecoConfigOrg configOrg: orgList){

            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();

            start.setTime(this.startDate);
            end.setTime(this.endDate);


            // Loop recorriendo rango de fechas ingresadas
            while( !start.after(end)){

                Timestamp fechaProceso = new Timestamp(start.getTime().getTime());

                Trx trans = null;
                try{

                    String trxName = Trx.createTrxName();
                    trans = Trx.get(trxName, true);

                    MZSistecoInterfacePazos interfacePazos = new MZSistecoInterfacePazos(getCtx(), 0, trxName);
                    interfacePazos.set_ValueOfColumn("AD_Client_ID", sistecoConfig.getAD_Client_ID());
                    interfacePazos.setAD_Org_ID(configOrg.getAD_OrgTrx_ID());
                    interfacePazos.saveEx();

                    String message = interfacePazos.execute(true, fechaProceso);

                    trans.close();
                }
                catch (Exception e){
                    if (trans != null){
                        trans.rollback();
                    }
                    throw new AdempiereException(e);
                }

                start.add(Calendar.DATE, 1);
            }

        }

        return "OK";
    }
}
