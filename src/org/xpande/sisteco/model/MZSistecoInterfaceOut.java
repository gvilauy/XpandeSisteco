package org.xpande.sisteco.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.xpande.core.model.I_Z_ProductoUPC;
import org.xpande.core.model.MZProductoUPC;
import org.xpande.core.utils.PriceListUtils;
import org.xpande.sisteco.utils.SistecoUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
     * @param trxName
     * @return
     */
    public static MZSistecoInterfaceOut getRecord(Properties ctx, int adTableID, int recordID, String trxName){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_AD_Table_ID + " =" + adTableID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_Record_ID + " =" + recordID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_IsExecuted + " ='N'";

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

            // Si es marca de create
            if ((this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE))
                    || (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_UPDATE))){

                String lineaArchivo = "";

                if (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE)){
                    lineaArchivo ="I" + separadorCampos;
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
                            lineaTandem += this.getM_Product_Tandem().getValue();
                            lineas.add(lineaTandem);
                        }
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
     * @return
     */
    public List<String> getLineasArchivoUPC(int adOrgID, String separadorCampos) {

        List<String> lineas = new ArrayList<String>();

        try{

            if (this.getAD_Table_ID() != I_Z_ProductoUPC.Table_ID){
                return lineas;
            }

            MZProductoUPC productoUPC = new MZProductoUPC(getCtx(), this.getRecord_ID(), get_TrxName());
            MProduct product = (MProduct) productoUPC.getM_Product();

            // Si es marca de create
            if (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE)){

                String lineaArchivo = "";

                lineaArchivo ="I" + separadorCampos;
                lineaArchivo += "ARTICULOS_EQUIVALENTES" + separadorCampos;

                lineaArchivo += productoUPC.getUPC() + separadorCampos;
                lineaArchivo += product.getValue();

                lineas.add(lineaArchivo);
            }
            else if (this.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_DELETE)){

                String lineaArchivo = "";

                lineaArchivo ="D" + separadorCampos;
                lineaArchivo += "ARTICULOS_EQUIVALENTES" + separadorCampos;
                lineaArchivo += productoUPC.getUPC();

                lineas.add(lineaArchivo);
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return lineas;
    }
}
