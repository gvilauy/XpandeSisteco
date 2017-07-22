package org.xpande.sisteco.utils;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProduct;
import org.compiere.model.MSequence;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.xpande.sisteco.model.*;

import java.util.List;
import java.util.Properties;

/**
 * Clase de métodos staticos para funcionalidad relacioanda a Sisteco.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 7/21/17.
 */
public final class SistecoUtils {


    /***
     * Asocia atributos de producto requeridos por Sisteco a un determinado producto recibido.
     * Xpande. Created by Gabriel Vila on 7/21/17.
     * @param ctx
     * @param product
     * @param trxName
     */
    public static void setProductAttributes(Properties ctx, MProduct product, String trxName){

        try{
            // Modelo configurador de Sisteco
            MZSistecoConfig sistecoConfig = MZSistecoConfig.getDefault(ctx, null);

            // Obtengo y recorro lista de atributos de sisteco
            List<MZSistecoAtributoProd> atributoProds = sistecoConfig.getAtributosProducto();
            for (MZSistecoAtributoProd atributoProd: atributoProds){

                // Asocio atributo al producto recibido.
                MZProductoAtribSisteco productoAtribSisteco = new MZProductoAtribSisteco(ctx, 0, trxName);
                productoAtribSisteco.setZ_SistecoAtributoProd_ID(atributoProd.get_ID());
                productoAtribSisteco.setSeqNo(atributoProd.getSeqNo());
                productoAtribSisteco.setM_Product_ID(product.get_ID());
                productoAtribSisteco.setIsSelected(atributoProd.isSelected());

                // Seteo atributo segun el producto tenga asociado otro producto tandem
                if (atributoProd.isAtributoTandem()){
                    if (product.get_ValueAsInt("M_Product_Tandem_ID") > 0){
                        productoAtribSisteco.setIsSelected(true);
                    }
                    else{
                        productoAtribSisteco.setIsSelected(false);
                    }
                }

                // Seteo atributo segun unidad de medida del producto
                if (product.getC_UOM_ID() > 0){
                    // Si tengo seteo de atributo segun unidad de medida
                    MZSistecoAtributoProdUOM atributoProdUOM = atributoProd.getUOMSet(product.getC_UOM_ID());
                    if ((atributoProdUOM != null) && (atributoProdUOM.get_ID() > 0)){
                        // Seteo atributo segun flag de unidad de medida
                        productoAtribSisteco.setIsSelected(atributoProdUOM.isSelected());
                    }
                }

                productoAtribSisteco.saveEx();
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
    }


    /***
     * Refresca asociación de atributos de producto requeridos por Sisteco a un determinado producto recibido.
     * Xpande. Created by Gabriel Vila on 7/21/17.
     * @param ctx
     * @param product
     * @param trxName
     */
    public static void refreshProductAttributes(Properties ctx, MProduct product, String trxName){

        try{
            // Obtiene y recorre asociacion de atributos al producto recibido
            List<MZProductoAtribSisteco> lineas = SistecoUtils.getAtributosByProduct(ctx, product.get_ID(), trxName);
            for (MZProductoAtribSisteco productoAtribSisteco: lineas) {

                MZSistecoAtributoProd sistecoAtributoProd = (MZSistecoAtributoProd) productoAtribSisteco.getZ_SistecoAtributoProd();

                // Seteo atributo segun el producto tenga asociado otro producto tandem
                if (sistecoAtributoProd.isAtributoTandem()){
                    if (product.get_ValueAsInt("M_Product_Tandem_ID") > 0){
                        productoAtribSisteco.setIsSelected(true);
                    }
                    else{
                        productoAtribSisteco.setIsSelected(false);
                    }
                }

                // Seteo atributo segun unidad de medida del producto
                if (product.getC_UOM_ID() > 0){
                    // Si tengo seteo de atributo segun unidad de medida
                    MZSistecoAtributoProdUOM atributoProdUOM = sistecoAtributoProd.getUOMSet(product.getC_UOM_ID());
                    if ((atributoProdUOM != null) && (atributoProdUOM.get_ID() > 0)){
                        // Seteo atributo segun flag de unidad de medida
                        productoAtribSisteco.setIsSelected(atributoProdUOM.isSelected());
                    }
                }
                productoAtribSisteco.saveEx();
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
    }


    /***
     * Obtiene valor Hexadecimal de array de bits con los valores de los atributos asociados a un determinado producto.
     * Xpande. Created by Gabriel Vila on 7/22/17.
     * @param ctx
     * @param product
     * @param trxName
     * @return
     */
    public static String getHexadecimalAtributos(Properties ctx, MProduct product, String trxName) {

        String valorHexa = null;
        String[] arrayBits = new String[16];

        try{

            // Seteos iniciales en array de bits
            for (int i = 0; i < arrayBits.length; i++){
                arrayBits[i] = "";
            }
            arrayBits[12] = "0000";
            arrayBits[13] = "0000";
            arrayBits[14] = "0000";
            arrayBits[15] = "0000";

            // Obtiene y recorre asociacion de atributos al producto recibido
            List<MZProductoAtribSisteco> lineas = SistecoUtils.getAtributosByProduct(ctx, product.get_ID(), trxName);
            for (MZProductoAtribSisteco productoAtribSisteco: lineas) {

                MZSistecoAtributoProd sistecoAtributoProd = (MZSistecoAtributoProd) productoAtribSisteco.getZ_SistecoAtributoProd();
                String codigoAtrib = sistecoAtributoProd.getValue().toLowerCase().trim();

                // Segun codigo del atributo, seteo el array de bits
                if ((codigoAtrib.equalsIgnoreCase("attr_1")) || (codigoAtrib.equalsIgnoreCase("attr_2"))
                        || (codigoAtrib.equalsIgnoreCase("attr_3")) || (codigoAtrib.equalsIgnoreCase("attr_4"))) {

                    if (productoAtribSisteco.isSelected()) {
                        arrayBits[0] = arrayBits[0] + "1";
                    }
                    else {
                        arrayBits[0] = arrayBits[0] + "0";
                    }
                } else if ((codigoAtrib.equalsIgnoreCase("attr_5")) || (codigoAtrib.equalsIgnoreCase("attr_6"))
                        || (codigoAtrib.equalsIgnoreCase("attr_7"))) {

                    if (productoAtribSisteco.isSelected()) {
                        arrayBits[1] = arrayBits[1] + "1";
                    }
                    else {
                        arrayBits[1] = arrayBits[1] + "0";
                    }

                    if (codigoAtrib.equalsIgnoreCase("attr_7")) arrayBits[1] = arrayBits[1] + "0";
                }
                else if ((codigoAtrib.equalsIgnoreCase("attr_9")) || (codigoAtrib.equalsIgnoreCase("attr_11"))
                        || (codigoAtrib.equalsIgnoreCase("attr_12"))) {

                    if (productoAtribSisteco.isSelected()) {
                        arrayBits[2] = arrayBits[2] + "1";
                    }
                    else {
                        arrayBits[2] = arrayBits[2] + "0";
                    }

                    if (codigoAtrib.equalsIgnoreCase("attr_9")) arrayBits[2] = arrayBits[2] + "0";
                }
                else if ((codigoAtrib.equalsIgnoreCase("attr_13")) || (codigoAtrib.equalsIgnoreCase("attr_14"))
                        || (codigoAtrib.equalsIgnoreCase("attr_15")) || (codigoAtrib.equalsIgnoreCase("attr_16"))){

                    if (productoAtribSisteco.isSelected()) {
                        arrayBits[3] = arrayBits[3] + "1";
                    }
                    else {
                        arrayBits[3] = arrayBits[3] + "0";
                    }

                }
                else if ((codigoAtrib.equalsIgnoreCase("attr_17")) || (codigoAtrib.equalsIgnoreCase("attr_19"))
                        || (codigoAtrib.equalsIgnoreCase("attr_20"))) {

                    if (productoAtribSisteco.isSelected()) {
                        arrayBits[4] = arrayBits[4] + "1";
                    }
                    else {
                        arrayBits[4] = arrayBits[4] + "0";
                    }

                    if (codigoAtrib.equalsIgnoreCase("attr_17")) arrayBits[4] = arrayBits[4] + "0";
                }
                else if ((codigoAtrib.equalsIgnoreCase("attr_21")) || (codigoAtrib.equalsIgnoreCase("attr_22"))
                        || (codigoAtrib.equalsIgnoreCase("attr_24"))) {

                    if (productoAtribSisteco.isSelected()) {
                        arrayBits[5] = arrayBits[5] + "1";
                    }
                    else {
                        arrayBits[5] = arrayBits[5] + "0";
                    }

                    if (codigoAtrib.equalsIgnoreCase("attr_22")) arrayBits[5] = arrayBits[5] + "0";
                }
                else if ((codigoAtrib.equalsIgnoreCase("attr_25")) || (codigoAtrib.equalsIgnoreCase("attr_27"))){
                    if (productoAtribSisteco.isSelected()) {
                        arrayBits[6] = arrayBits[6] + "1";
                    }
                    else {
                        arrayBits[6] = arrayBits[6] + "0";
                    }
                    arrayBits[6] = arrayBits[6] + "0";
                }
                else if ((codigoAtrib.equalsIgnoreCase("attr_29")) || (codigoAtrib.equalsIgnoreCase("attr_30"))
                        || (codigoAtrib.equalsIgnoreCase("attr_31")) || (codigoAtrib.equalsIgnoreCase("attr_32"))){

                    if (productoAtribSisteco.isSelected()) {
                        arrayBits[7] = arrayBits[7] + "1";
                    }
                    else {
                        arrayBits[7] = arrayBits[7] + "0";
                    }
                }
                else if (codigoAtrib.equalsIgnoreCase("attr_36")){

                    if (productoAtribSisteco.isSelected()) {
                        arrayBits[8] = "0001";
                    }
                    else {
                        arrayBits[8] = "0000";
                    }
                }
                else if ((codigoAtrib.equalsIgnoreCase("attr_38")) || (codigoAtrib.equalsIgnoreCase("attr_39"))
                        || (codigoAtrib.equalsIgnoreCase("attr_40"))) {

                    if (codigoAtrib.equalsIgnoreCase("attr_38")) arrayBits[9] = arrayBits[9] + "0";

                    if (productoAtribSisteco.isSelected()) {
                        arrayBits[9] = arrayBits[9] + "1";
                    }
                    else {
                        arrayBits[9] = arrayBits[9] + "0";
                    }
                }
                else if (codigoAtrib.equalsIgnoreCase("attr_41")){

                    if (productoAtribSisteco.isSelected()) {
                        arrayBits[10] = "1000";
                    }
                    else {
                        arrayBits[10] = "0000";
                    }
                }
                else if ((codigoAtrib.equalsIgnoreCase("attr_45")) || (codigoAtrib.equalsIgnoreCase("attr_46"))
                        || (codigoAtrib.equalsIgnoreCase("attr_47")) || (codigoAtrib.equalsIgnoreCase("attr_48"))){

                    if (productoAtribSisteco.isSelected()) {
                        arrayBits[11] = arrayBits[11] + "1";
                    }
                    else {
                        arrayBits[11] = arrayBits[11] + "0";
                    }
                }
            }

            // Convierto array de bits en Hexadecimal
            String hexaTmp = "";
            for (int i = 0; i < arrayBits.length; i++){

                hexaTmp = arrayBits[i];
                long longHexa = Long.parseLong(hexaTmp);
                long reminder;

                while (longHexa > 0){

                    reminder = longHexa % 10;
                    longHexa = longHexa / 10;

                    if(reminder != 0 && reminder != 1){
                        return valorHexa;
                    }
                }

                int j = Integer.parseInt(hexaTmp,2);
                valorHexa = valorHexa + Integer.toHexString(j);
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return valorHexa;
    }


    /***
     * Obtiene y retorna lista con asociacion de atributos de producto de Sisteco a un determinado producto recibido.
     * Xpande. Created by Gabriel Vila on 7/21/17.
     * @param ctx
     * @param mProductID
     * @param trxName
     * @return
     */
    public static List<MZProductoAtribSisteco> getAtributosByProduct(Properties ctx, int mProductID, String trxName){

        String whereClause = X_Z_ProductoAtribSisteco.COLUMNNAME_M_Product_ID + " =" + mProductID;

        List<MZProductoAtribSisteco> lines = new Query(ctx, I_Z_ProductoAtribSisteco.Table_Name, whereClause, trxName).setOnlyActiveRecords(true)
                .setOrderBy(" SeqNo ").list();

        return lines;
    }
}
