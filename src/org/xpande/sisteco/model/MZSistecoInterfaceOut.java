package org.xpande.sisteco.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.eevolution.model.X_C_TaxGroup;
import org.xpande.core.model.I_Z_ProductoUPC;
import org.xpande.core.model.MZProductoUPC;
import org.xpande.core.utils.DateUtils;
import org.xpande.core.utils.PriceListUtils;
import org.xpande.sisteco.utils.SistecoUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

/**
 * Modelo para interface de datos desde el sistema hacia Sisteco
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 7/10/17.
 */
public class MZSistecoInterfaceOut extends X_Z_SistecoInterfaceOut {

    public MZSistecoInterfaceOut(Properties ctx, int Z_SistecoInterfaceOut_ID, String trxName) {
        super(ctx, Z_SistecoInterfaceOut_ID, trxName);
    }

    public MZSistecoInterfaceOut(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /***
     * Obtiene y retorna modelo según parametros recibidos
     * Xpande. Created by Gabriel Vila on 7/6/17.
     * @param ctx
     * @param adTableID
     * @param recordID
     * @param adOrgID
     * @param trxName
     * @return
     */
    public static MZSistecoInterfaceOut getRecord(Properties ctx, int adTableID, int recordID, int adOrgID, String trxName){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_AD_Table_ID + " =" + adTableID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_Record_ID + " =" + recordID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_OrgTrx_ID + " =" + adOrgID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_IsExecuted + " ='N'";

        MZSistecoInterfaceOut model = new Query(ctx, I_Z_SistecoInterfaceOut.Table_Name, whereClause, trxName).first();

        return model;

    }

    /***
     * Obtiene y retorna modelo según parametros recibidos
     * Xpande. Created by Gabriel Vila on 7/6/17.
     * @param ctx
     * @param adTableID
     * @param recordID
     * @param crudType
     * @param trxName
     * @return
     */
    public static MZSistecoInterfaceOut getRecordByCrud(Properties ctx, int adTableID, int recordID, String crudType, String trxName){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_AD_Table_ID + " =" + adTableID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_Record_ID + " =" + recordID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_IsExecuted + " ='N'" +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_CRUDType + " ='" + crudType + "'";

        MZSistecoInterfaceOut model = new Query(ctx, I_Z_SistecoInterfaceOut.Table_Name, whereClause, trxName).first();

        return model;

    }

    /***
     * Obtiene y retorna lineas para archivos de interface de salida con información de producto, a partir de la información de este modelo.
     * @param adOrgID
     * @param separadorCampos
     * @return
     */
    public List<String> getLineasArchivoProducto(int adOrgID, String separadorCampos) {

        List<String> lineas = new ArrayList<String>();

        try{

            if (this.getAD_Table_ID() != I_M_Product.Table_ID){
                return lineas;
            }

            MProduct product = new MProduct(getCtx(), this.getRecord_ID(), get_TrxName());
            MPriceList priceList = null;
            if (this.getM_PriceList_ID() > 0){
                priceList = (MPriceList)this.getM_PriceList();
            }
            else{
                priceList = PriceListUtils.getPriceListByOrg(getCtx(), this.getAD_Client_ID(), adOrgID, 142, true, get_TrxName());
                if ((priceList == null) || (priceList.get_ID() <= 0)){
                    priceList = PriceListUtils.getPriceListByOrg(getCtx(), this.getAD_Client_ID(), adOrgID, 100, true, get_TrxName());
                }
            }

            boolean creaProducto = false;

            // Si es marca de create
            if ((this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE))
                    || (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_UPDATE))){

                String lineaArchivo = "";

                if (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE)){
                    lineaArchivo ="I" + separadorCampos;
                    creaProducto = true;
                }
                else{
                    lineaArchivo ="U" + separadorCampos;
                }

                lineaArchivo += "ARTICULOS" + separadorCampos;

                lineaArchivo += product.getValue() + separadorCampos;
                lineaArchivo += product.getDescription().replace(separadorCampos,"_") + separadorCampos;
                lineaArchivo += "0" + separadorCampos; // codigo entorno no utilizado
                lineaArchivo += "0" + separadorCampos; // codigo subfamilia no utilizado

                // Moneda
                String monedaSisteco = "1";  // Pesos por defecto
                if (priceList.getC_Currency_ID() == 100){
                    monedaSisteco = "2";
                }

                lineaArchivo += monedaSisteco + separadorCampos;

                // Codigo IVA
                lineaArchivo += ((MTaxCategory) product.getC_TaxCategory()).getCommodityCode() + separadorCampos;

                // Precio de venta
                MPriceListVersion priceListVersion = priceList.getPriceListVersion(null);
                MProductPrice productPrice = MProductPrice.get(getCtx(), priceListVersion.get_ID(), product.get_ID(), get_TrxName());

                if (productPrice == null){
                    throw new AdempiereException("No se obtuvo precio de venta para el producto con ID : " + product.get_ID());
                }

                BigDecimal priceSO = productPrice.getPriceList();
                this.setPriceSO(priceSO); // Guardo precio de venta obtenido y que será el comunicado al POS

                String precioSisteco = "0", parteDecimalPrecio ="00";
                BigDecimal decimalPrecioSO = priceSO.subtract(priceSO.setScale(0, RoundingMode.FLOOR)).movePointRight(priceSO.scale());
                if (decimalPrecioSO != null){
                    if (decimalPrecioSO.compareTo(Env.ZERO) != 0){
                        if (decimalPrecioSO.toString().length() >= 3){
                            parteDecimalPrecio = decimalPrecioSO.toString().substring(0, 2);
                        }
                        else if (decimalPrecioSO.toString().length() == 2){
                            parteDecimalPrecio = decimalPrecioSO.toString();
                        }
                        else if (decimalPrecioSO.toString().length() == 1){
                            parteDecimalPrecio = decimalPrecioSO.toString() + "0";
                        }
                    }
                }
                precioSisteco = String.valueOf(productPrice.getPriceList().intValue()) + parteDecimalPrecio;
                lineaArchivo += precioSisteco + separadorCampos;

                // Valor Hexadecimal del producto
                String valorHexadecimal = SistecoUtils.getHexadecimalAtributos(getCtx(), product, get_TrxName());
                lineaArchivo += valorHexadecimal + separadorCampos;

                // Unidades por pack
                lineaArchivo += String.valueOf(product.getUnitsPerPack()) + separadorCampos;

                // Unidad de medida
                String unidadSisteco = "1";
                if (product.get_ValueAsBoolean("EsProductoBalanza")){
                    unidadSisteco = "2";
                }
                lineaArchivo += unidadSisteco;

                lineas.add(lineaArchivo);

                // Si es UPDATE y hubo cambio de Tandem
                if (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_UPDATE)){
                    if (this.isTandemChanged()){
                        // Si tengo tandem anterior (o sea, que ya tenia tandem y se modifico)
                        if (this.getM_Product_Tandem_ID() > 0){
                            // Agrego linea de eliminación de tandem anterior
                            String lineaTandem = "";
                            lineaTandem ="D" + separadorCampos;
                            lineaTandem += "TANDEM" + separadorCampos;
                            lineaTandem += product.getValue();
                            lineas.add(lineaTandem);
                        }
                        // Si el producto tiene Tandem
                        if (product.get_ValueAsInt("M_Product_Tandem_ID") > 0){
                            // Agrego linea de nuevo tandem
                            String lineaTandem = "";
                            lineaTandem ="I" + separadorCampos;
                            lineaTandem += "TANDEM" + separadorCampos;
                            lineaTandem += product.getValue() + separadorCampos;

                            MProduct productTandem = new MProduct(getCtx(), product.get_ValueAsInt("M_Product_Tandem_ID"), null);
                            if ((productTandem == null) || (productTandem.get_ID() <= 0)){
                                throw new AdempiereException("No se obtuvo producto tandem con ID : " + product.get_ValueAsInt("M_Product_Tandem_ID"));
                            }
                            lineaTandem += productTandem.getValue();
                            lineas.add(lineaTandem);
                        }
                    }

                    // Mardo producto com comunicado pos si es la primera vez que se comunica
                    if (creaProducto){
                        String action = " update m_product set comunicadopos ='Y' where m_product_id =" + product.get_ID();
                        DB.executeUpdateEx(action, get_TrxName());
                    }
                }
                else if (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE)){

                    // Si el producto tiene Tandem
                    if (product.get_ValueAsInt("M_Product_Tandem_ID") > 0){
                        // Agrego linea de nuevo tandem
                        String lineaTandem = "";
                        lineaTandem ="I" + separadorCampos;
                        lineaTandem += "TANDEM" + separadorCampos;
                        lineaTandem += product.getValue() + separadorCampos;
                        lineaTandem += this.getM_Product_Tandem().getValue();
                        lineas.add(lineaTandem);
                    }
                }
            }
            else if (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_DELETE)){

                String lineaArchivo = "";

                lineaArchivo ="D" + separadorCampos;
                lineaArchivo += "ARTICULOS" + separadorCampos;
                lineaArchivo += product.getValue();

                lineas.add(lineaArchivo);
            }


        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return lineas;
    }


    /***
     * Obtiene y retorna lineas para archivos de interface de salida con información de códigos de barra,
     * a partir de la información de este modelo.
     * @param adOrgID
     * @param separadorCampos
     * @param processPrices
     * @param hashProds
     * @return
     */
    public List<String> getLineasArchivoUPC(int adOrgID, String separadorCampos, boolean processPrices, HashMap<Integer, Integer> hashProds) {

        List<String> lineas = new ArrayList<String>();

        try{

            if (this.getAD_Table_ID() != I_Z_ProductoUPC.Table_ID){
                return lineas;
            }

            // Si es marca de create
            if (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE)){

                // Cuando creo un UPC, el Record_ID de la marca = ID de la tabla de codigos de barra
                MZProductoUPC productoUPC = new MZProductoUPC(getCtx(), this.getRecord_ID(), get_TrxName());
                MProduct product = (MProduct) productoUPC.getM_Product();

                // Obtengo si es que existe, la marca de CREATE del producto de esta barra
                MZSistecoInterfaceOut interfaceOutProd = MZSistecoInterfaceOut.getRecordByCrud(getCtx(), product.Table_ID, product.get_ID(),
                        X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE, get_TrxName());


                // Si estoy en la opcion de no procesar cambios de precios
                if (!processPrices){

                    // Debo verificar que el producto asociado a este codigo de barras, haya sido comunicado alguna vez al pos.

                    // Si el producto de esta barra nunca fue comunicado al POS como Alta
                    if ((interfaceOutProd == null) || (interfaceOutProd.get_ID() <= 0)){

                        // Si el producto fue creado hace menos de un mes, entonces no comunico esta barra.
                        Timestamp fechaHoy = TimeUtil.trunc(new Timestamp(System.currentTimeMillis()), TimeUtil.TRUNC_DAY);
                        Date dateFechaAux = new Date(fechaHoy.getTime());
                        dateFechaAux =  DateUtils.addDays(dateFechaAux, -30);
                        Timestamp fechaTope = new Timestamp(dateFechaAux.getTime());

                        if (product.getCreated().after(fechaTope)){
                            return lineas;
                        }
                    }
                }
                else{
                    // Estoy comunicando precios
                    // Si no tengo marca de create del producto
                    if ((interfaceOutProd == null) || (interfaceOutProd.get_ID() <= 0)){
                        // Si el producto no esta siendo comunicado en este proceso
                        if (!hashProds.containsKey(product.get_ID())){
                            // No comunico esta barra
                            return lineas;
                        }
                    }
                }

                String lineaArchivo = "";

                lineaArchivo ="I" + separadorCampos;
                lineaArchivo += "ARTICULOS_EQUIVALENTES" + separadorCampos;

                lineaArchivo += productoUPC.getUPC() + separadorCampos;
                lineaArchivo += product.getValue();

                lineas.add(lineaArchivo);

                this.setDescription(productoUPC.getUPC()); // Guardo UPC comunicado
            }
            else if (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_DELETE)){

                String lineaArchivo = "";

                lineaArchivo ="D" + separadorCampos;
                lineaArchivo += "ARTICULOS_EQUIVALENTES" + separadorCampos;
                lineaArchivo += this.getDescription().trim();   // Se guarda en description, el codigo de barra ya eliminado.

                lineas.add(lineaArchivo);
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return lineas;
    }


    /***
     * Obtiene y retorna lineas para archivos de interface de salida con información de códigos de barra,
     * a partir de la información de este modelo.
     * Xpande. Created by Gabriel Vila on 8/29/17.
     * @param adOrgID
     * @param separadorCampos
     * @param sistecoConfig
     * @return
     */
    public List<String> getLineasArchivoBPartner(int adOrgID, String separadorCampos, MZSistecoConfig sistecoConfig) {

        List<String> lineas = new ArrayList<String>();

        try{

            if (this.getAD_Table_ID() != I_C_BPartner.Table_ID){
                return lineas;
            }

            // Instancio modelo de socio y localización.
            MBPartner partner = new MBPartner(getCtx(), this.getRecord_ID(), get_TrxName());
            MBPartnerLocation[] partnerLocations = partner.getLocations(true);
            MBPartnerLocation partnerLocation = null;
            MLocation location = null;
            if (partnerLocations.length > 0){
                partnerLocation = partnerLocations[0];
                if (partnerLocation.getC_Location_ID() > 0){
                    location = (MLocation) partnerLocation.getC_Location();
                }
            }

            // Si es marca de create o update
            if ((this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE))
                    || (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_UPDATE))){

                String lineaArchivo = "";

                // Por ahora tanto el crear como el actualizar clientes, tiene como operacion A
                if (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE)){
                    lineaArchivo ="A" + separadorCampos;
                }
                else{
                    lineaArchivo ="A" + separadorCampos;
                }

                lineaArchivo += "CLIENTES" + separadorCampos;

                // Codigo y razon social
                lineaArchivo += partner.getValue().trim() + separadorCampos;
                lineaArchivo += partner.getName().trim() + separadorCampos;

                String direccion = "", telefono = "", email = "";
                String codigoPais = "", ciudad = "", departamento = "", codigoPostal = "";
                String esquina1 = "", esquina2 = "", codigoDptoSisteco = "10";
                if (location != null){
                    if (location.getAddress1() != null){
                        direccion = location.getAddress1().trim().replace(separadorCampos, "_");
                    }
                    if (location.getCity() != null){
                        ciudad = location.getCity().trim().replace(separadorCampos, "_");
                    }
                    if (location.getRegionName() != null){
                        departamento = location.getRegionName().trim().replace(separadorCampos, "_");
                        if (departamento != null){
                            MZSistecoRegion sistecoRegion = sistecoConfig.getByDepartamento(departamento);
                            if ((sistecoRegion != null) && (sistecoRegion.get_ID() > 0)){
                                codigoDptoSisteco = sistecoRegion.getST_CodigoDepartamento().trim();
                            }
                        }
                    }
                    else{
                        throw new AdempiereException("Falta definir Departamento para el Socio de Negocio : " + partner.getValue());
                    }
                    if (location.getPostal() != null){
                        codigoPostal = location.getPostal().trim().replace(separadorCampos, "_");
                    }
                    if (location.getCountry() != null){
                        codigoPais = ((MCountry) location.getCountry()).getCountryCode().trim();
                    }
                    else{
                        throw new AdempiereException("Falta definir Pais para el Socio de Negocio : " + partner.getValue());
                    }
                }

                if (partnerLocation.getPhone() != null){
                    telefono = partnerLocation.getPhone().trim().replace(separadorCampos, "_");
                }

                if (partner.get_Value("EMail") != null){
                    email = ((String) partner.get_Value("EMail")).trim().replace(separadorCampos, "_");
                }

                String tipoDocumento = "", nroDocumento = "", cedula = "";
                nroDocumento = partner.getTaxID();
                if ((nroDocumento == null) || (nroDocumento.trim().equalsIgnoreCase(""))){
                    throw new AdempiereException("Falta definir Numero de Identificación para el Socio de Negocio : " + partner.getValue());
                }

                X_C_TaxGroup taxGroup = (X_C_TaxGroup) partner.getC_TaxGroup();
                if (taxGroup.getValue().equalsIgnoreCase("RUT")){
                    tipoDocumento = "2";
                }
                else if (taxGroup.getValue().equalsIgnoreCase("CI")){
                    tipoDocumento = "3";
                    nroDocumento = "";
                    cedula = partner.getTaxID();
                }
                else{
                    tipoDocumento = "4"; // Otros
                }

                // Valor Hexadecimal del socio de negocio
                String valorHexadecimal = SistecoUtils.getHexadecimalAtributosPartner(getCtx(), partner, get_TrxName());

                // Concateno campos y armo linea de archivo
                lineaArchivo += direccion + separadorCampos;
                lineaArchivo += esquina1 + separadorCampos;
                lineaArchivo += esquina2 + separadorCampos;
                lineaArchivo += telefono + separadorCampos;
                lineaArchivo += nroDocumento + separadorCampos;
                lineaArchivo += cedula + separadorCampos;
                lineaArchivo += tipoDocumento + separadorCampos;
                //lineaArchivo += departamento + separadorCampos;
                lineaArchivo += codigoDptoSisteco + separadorCampos;
                lineaArchivo += ciudad + separadorCampos;
                lineaArchivo += codigoPostal + separadorCampos;
                lineaArchivo += email + separadorCampos;
                lineaArchivo += codigoPais + separadorCampos;
                lineaArchivo += valorHexadecimal;

                lineas.add(lineaArchivo);
            }
            else if (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_DELETE)){

                String lineaArchivo = "";

                lineaArchivo ="D" + separadorCampos;
                lineaArchivo += "CLIENTES" + separadorCampos;
                lineaArchivo += partner.getValue().trim();

                lineas.add(lineaArchivo);
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return lineas;
    }
}
