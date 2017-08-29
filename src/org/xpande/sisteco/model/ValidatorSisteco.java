package org.xpande.sisteco.model;

import org.compiere.model.*;
import org.compiere.util.DB;
import org.xpande.core.model.I_Z_ProductoUPC;
import org.xpande.core.model.MZProductoUPC;
import org.xpande.sisteco.utils.SistecoUtils;

import java.util.List;

/**
 * Model Validator para Sisteco
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 7/21/17.
 */
public class ValidatorSisteco implements ModelValidator {

    private int adClientID = 0;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {

        // Guardo compañia
        if (client != null){
            this.adClientID = client.get_ID();
        }

        // DB Validations
        engine.addModelChange(I_M_Product.Table_Name, this);
        engine.addModelChange(I_Z_ProductoUPC.Table_Name, this);
        engine.addModelChange(I_M_ProductPrice.Table_Name, this);
        engine.addModelChange(I_C_BPartner.Table_Name, this);

    }

    @Override
    public int getAD_Client_ID() {
        return this.adClientID;
    }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
        return null;
    }

    @Override
    public String modelChange(PO po, int type) throws Exception {

        if (po.get_TableName().equalsIgnoreCase(I_M_Product.Table_Name)){
            return modelChange((MProduct) po, type);
        }
        else if (po.get_TableName().equalsIgnoreCase(I_Z_ProductoUPC.Table_Name)){
            return modelChange((MZProductoUPC) po, type);
        }
        else if (po.get_TableName().equalsIgnoreCase(I_M_ProductPrice.Table_Name)){
            return modelChange((MProductPrice) po, type);
        }
        else if (po.get_TableName().equalsIgnoreCase(I_C_BPartner.Table_Name)){
            return modelChange((MBPartner) po, type);
        }

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) {
        return null;
    }


    /***
     * Validaciones para el modelo de Productos
     * Xpande. Created by Gabriel Vila on 6/30/17.
     * @param model
     * @param type
     * @return
     * @throws Exception
     */
    public String modelChange(MProduct model, int type) throws Exception {

        String mensaje = null;

        // Sisteco. Interface salida POS
        if ((type == ModelValidator.TYPE_AFTER_NEW) || (type == ModelValidator.TYPE_AFTER_CHANGE)){

            if (type == ModelValidator.TYPE_AFTER_NEW){

                // Si el producto no se vende o no esta activo al momento de crearse, no hago nada
                if ((!model.isSold()) || (!model.isActive())){
                    return mensaje;
                }

                // Marca de Creacion de Producto
                MZSistecoInterfaceOut sistecoInterfaceOut = new MZSistecoInterfaceOut(model.getCtx(), 0, model.get_TrxName());
                sistecoInterfaceOut.setCRUDType(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE);
                sistecoInterfaceOut.setSeqNo(10);
                sistecoInterfaceOut.setAD_Table_ID(I_M_Product.Table_ID);
                sistecoInterfaceOut.setRecord_ID(model.get_ID());
                sistecoInterfaceOut.saveEx();

                // Asociación de atributos que requiere Sisteco al nuevo producto
                SistecoUtils.setProductAttributes(model.getCtx(), model, model.get_TrxName());

                // Obtengo y guardo valor Hexadecimal segun seteos de atributos para el producto recibido.
                // Solo si el producto tiene unidad de medida o tandem asociado.
                if ((model.getC_UOM_ID() > 0) || (model.get_ValueAsInt("M_Product_Tandem_ID") > 0)){
                    String valorHexadecimal = SistecoUtils.getHexadecimalAtributos(model.getCtx(), model, model.get_TrxName());
                    String action = " update m_product set atributoshexa ='" + valorHexadecimal + "' " +
                            " where m_product_id =" + model.get_ID();
                    DB.executeUpdateEx(action, model.get_TrxName());
                }

            }
            else if (type == ModelValidator.TYPE_AFTER_CHANGE){

                // Me aseguro que el producto tenga atributos asociados, sino se los creo ahora.
                // Esto es momentaneo para crear los atributos de los productos migrados.
                String sql = " select count(*) from z_productoatribsisteco where m_product_id =" + model.get_ID();
                int contadorAtribs = DB.getSQLValue(model.get_TrxName(), sql);
                if (contadorAtribs <= 0){
                    // Asociación de atributos que requiere Sisteco al nuevo producto
                    SistecoUtils.setProductAttributes(model.getCtx(), model, model.get_TrxName());

                    // Obtengo y guardo valor Hexadecimal segun seteos de atributos para el producto recibido.
                    // Solo si el producto tiene unidad de medida o tandem asociado.
                    if ((model.getC_UOM_ID() > 0) || (model.get_ValueAsInt("M_Product_Tandem_ID") > 0)){
                        String valorHexadecimal = SistecoUtils.getHexadecimalAtributos(model.getCtx(), model, model.get_TrxName());
                        String action = " update m_product set atributoshexa ='" + valorHexadecimal + "' " +
                                " where m_product_id =" + model.get_ID();
                        DB.executeUpdateEx(action, model.get_TrxName());
                    }
                }

                // Pregunto por los campos cuyo cambio requiere informar a Sisteco
                if ((model.is_ValueChanged("C_UOM_ID")) || (model.is_ValueChanged("M_Product_Tandem_ID"))
                    || (model.is_ValueChanged("Description")) || (model.is_ValueChanged("C_TaxCategory_ID"))
                        || (model.is_ValueChanged("Name")) || (model.is_ValueChanged("EsProductoBalanza"))
                        || (model.is_ValueChanged("Z_ProductoSeccion_ID")) || (model.is_ValueChanged("Z_ProductoRubro_ID"))
                        || (model.is_ValueChanged("Z_ProductoFamilia_ID")) || (model.is_ValueChanged("Z_ProductoSubfamilia_ID"))
                        || (model.is_ValueChanged("IsBonificable"))){

                    // Si el producto tiene unidad de medida o tandem asociado.
                    if ((model.is_ValueChanged("C_UOM_ID")) || (model.is_ValueChanged("M_Product_Tandem_ID"))){

                        // Refreso valores de atributos del producto
                        SistecoUtils.refreshProductAttributes(model.getCtx(), model, model.get_TrxName());

                        // Obtengo y guardo valor Hexadecimal segun seteos de atributos
                        String valorHexadecimal = SistecoUtils.getHexadecimalAtributos(model.getCtx(), model, model.get_TrxName());
                        String action = " update m_product set atributoshexa ='" + valorHexadecimal + "' " +
                                " where m_product_id =" + model.get_ID();
                        DB.executeUpdateEx(action, model.get_TrxName());
                    }

                    // Marca Update para Sisteco
                    MZSistecoInterfaceOut sistecoInterfaceOut = MZSistecoInterfaceOut.getRecord(model.getCtx(), I_M_Product.Table_ID, model.get_ID(), model.get_TrxName());
                    if ((sistecoInterfaceOut != null) && (sistecoInterfaceOut.get_ID() > 0)){
                        // Proceso segun marca que ya tenía este producto antes de su actualización.
                        // Si marca anterior es CREATE
                        if (sistecoInterfaceOut.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE)){
                            // No hago nada y respeto primer marca
                            return mensaje;
                        }
                        else if (sistecoInterfaceOut.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_DELETE)){
                            // Si marca anterior es DELETEAR, es porque el producto se inactivo anteriormente.
                            // Si este producto sigue estando inactivo
                            if (!model.isActive()){
                                // No hago nada y respeto primer marca.
                                return mensaje;
                            }
                        }
                    }

                    // Si no tengo marca de update, la creo ahora.
                    if ((sistecoInterfaceOut == null) || (sistecoInterfaceOut.get_ID() <= 0)){
                        // No existe aun marca de UPDATE sobre este producto, la creo ahora.
                        sistecoInterfaceOut = new MZSistecoInterfaceOut(model.getCtx(), 0, model.get_TrxName());
                        sistecoInterfaceOut.setCRUDType(X_Z_SistecoInterfaceOut.CRUDTYPE_UPDATE);
                        sistecoInterfaceOut.setAD_Table_ID(I_M_Product.Table_ID);
                        sistecoInterfaceOut.setSeqNo(20);
                        sistecoInterfaceOut.setRecord_ID(model.get_ID());
                        sistecoInterfaceOut.setIsPriceChanged(false);
                        sistecoInterfaceOut.saveEx();
                    }

                    // Marca de update para Tandem si cambio
                    if (model.is_ValueChanged("M_Product_Tandem_ID")){
                        sistecoInterfaceOut.setIsTandemChanged(true);
                        // Guardo tandem anterior en caso de haber cambio
                        if (model.get_ValueOldAsInt("M_Product_Tandem_ID") > 0){
                            if (sistecoInterfaceOut.getM_Product_Tandem_ID() <= 0){
                                sistecoInterfaceOut.setM_Product_Tandem_ID(model.get_ValueOldAsInt("M_Product_Tandem_ID"));
                            }
                        }
                        sistecoInterfaceOut.saveEx();
                    }
                }
            }
        }

        return mensaje;
    }


    /***
     * Validaciones para el modelo de Códigos de Barras de Productos.
     * Xpande. Created by Gabriel Vila on 6/30/17.
     * @param model
     * @param type
     * @return
     * @throws Exception
     */
    public String modelChange(MZProductoUPC model, int type) throws Exception {

        String mensaje = null;

        MProduct product = (MProduct)model.getM_Product();

        // Si el producto no se vende o no esta activo, no hago nada
        if ((!product.isSold()) || (!product.isActive())){
            return mensaje;
        }

        // Sisteco. Interface salida POS
        // Para Sisteco, solo se crean la marcas para luego considerarse en la generación del archivo plano.
        if (type == ModelValidator.TYPE_AFTER_NEW){

            // Marca Create
            MZSistecoInterfaceOut sistecoInterfaceOut = new MZSistecoInterfaceOut(model.getCtx(), 0, model.get_TrxName());
            sistecoInterfaceOut.setCRUDType(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE);
            sistecoInterfaceOut.setAD_Table_ID(I_Z_ProductoUPC.Table_ID);
            sistecoInterfaceOut.setRecord_ID(model.get_ID());
            sistecoInterfaceOut.setSeqNo(15);
            sistecoInterfaceOut.saveEx();

        }
        else if (type == ModelValidator.TYPE_AFTER_DELETE){

            // Marca Update
            MZSistecoInterfaceOut sistecoInterfaceOut = new MZSistecoInterfaceOut(model.getCtx(), 0, model.get_TrxName());
            sistecoInterfaceOut.setCRUDType(X_Z_SistecoInterfaceOut.CRUDTYPE_DELETE);
            sistecoInterfaceOut.setAD_Table_ID(I_Z_ProductoUPC.Table_ID);
            sistecoInterfaceOut.setRecord_ID(model.get_ID());
            sistecoInterfaceOut.setSeqNo(13);
            sistecoInterfaceOut.saveEx();
        }

        return mensaje;
    }

    /***
     * Validaciones para el modelo de Precios de Productos
     * Xpande. Created by Gabriel Vila on 6/30/17.
     * @param model
     * @param type
     * @return
     * @throws Exception
     */
    public String modelChange(MProductPrice model, int type) throws Exception {

        String mensaje = null;

        // Retail. Interface salida POS
        if ((type == ModelValidator.TYPE_AFTER_NEW) || (type == ModelValidator.TYPE_AFTER_CHANGE)){

            // Solo listas de ventas con organización distinto de *
            MPriceListVersion priceListVersion = new MPriceListVersion(model.getCtx(), model.getM_PriceList_Version_ID(), model.get_TrxName());
            MPriceList priceList = priceListVersion.getPriceList();
            if (!priceList.isSOPriceList()) return mensaje;
            if (priceList.getAD_Org_ID() == 0) return mensaje;

            MProduct product = (MProduct)model.getM_Product();
            // Si el producto no se vende o no esta activo, no hago nada
            if ((!product.isSold()) || (!product.isActive())){
                return mensaje;
            }

            // Debo verificar que la organizacion asociada a esta lista de precios, trabaje con el POS de Sisteco, sino es asi no hago nada
            String sql = " select count(a.*) from z_posvendororg a " +
                         " inner join z_posvendor b on a.z_posvendor_id = b.z_posvendor_id and b.value='SISTECO' " +
                         " where ad_orgtrx_id =" + priceList.getAD_Org_ID();
            int contador = DB.getSQLValueEx(null, sql);
            if (contador <= 0){
                return mensaje;
            }

            // Si existe, obtengo marca de interface de este producto
            MZSistecoInterfaceOut sistecoInterfaceOut = MZSistecoInterfaceOut.getRecord(model.getCtx(), I_M_Product.Table_ID, product.get_ID(), model.get_TrxName());
            if ((sistecoInterfaceOut != null) && (sistecoInterfaceOut.get_ID() > 0)){
                // Proceso segun marca que ya tenía este producto antes de su actualización.
                // Si marca anterior es CREATE
                if (sistecoInterfaceOut.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE)){
                    // No hago nada y respeto primer marca
                    return mensaje;
                }
                else if (sistecoInterfaceOut.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_DELETE)){
                    // Si marca anterior es DELETEAR, es porque el producto se inactivo anteriormente.
                    // Si este producto sigue estando inactivo
                    if (!model.isActive()){
                        // No hago nada y respeto primer marca.
                        return mensaje;
                    }
                }
            }

            // Si no tengo marca de update, la creo ahora.
            if ((sistecoInterfaceOut == null) || (sistecoInterfaceOut.get_ID() <= 0)) {
                // No existe aun marca de UPDATE sobre este producto, la creo ahora.
                sistecoInterfaceOut = new MZSistecoInterfaceOut(model.getCtx(), 0, model.get_TrxName());
                sistecoInterfaceOut.setCRUDType(X_Z_SistecoInterfaceOut.CRUDTYPE_UPDATE);
                sistecoInterfaceOut.setAD_Table_ID(I_M_Product.Table_ID);
                sistecoInterfaceOut.setRecord_ID(product.get_ID());
                sistecoInterfaceOut.setSeqNo(30);
            }

            sistecoInterfaceOut.setIsPriceChanged(true);
            sistecoInterfaceOut.setM_PriceList_ID(priceList.get_ID());
            sistecoInterfaceOut.saveEx();
        }

        return mensaje;
    }

    /***
     * Validaciones para el modelo de Socios de Negocio
     * Xpande. Created by Gabriel Vila on 6/30/17.
     * @param model
     * @param type
     * @return
     * @throws Exception
     */
    public String modelChange(MBPartner model, int type) throws Exception {

        String mensaje = null;

        // Sisteco. Interface salida POS
        if ((type == ModelValidator.TYPE_AFTER_NEW) || (type == ModelValidator.TYPE_AFTER_CHANGE)){

            if (type == ModelValidator.TYPE_AFTER_NEW){

                // Si el producto no se vende o no esta activo al momento de crearse, no hago nada
                if ((!model.isCustomer()) || (!model.isActive())){
                    return mensaje;
                }

                // Marca de Creacion de Socio
                MZSistecoInterfaceOut sistecoInterfaceOut = new MZSistecoInterfaceOut(model.getCtx(), 0, model.get_TrxName());
                sistecoInterfaceOut.setCRUDType(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE);
                sistecoInterfaceOut.setSeqNo(10);
                sistecoInterfaceOut.setAD_Table_ID(I_C_BPartner.Table_ID);
                sistecoInterfaceOut.setRecord_ID(model.get_ID());
                sistecoInterfaceOut.saveEx();
            }
            else if (type == ModelValidator.TYPE_AFTER_CHANGE){

                // Pregunto por los campos cuyo cambio requiere informar a Sisteco
                if ((model.is_ValueChanged(X_C_BPartner.COLUMNNAME_Name)) || (model.is_ValueChanged(X_C_BPartner.COLUMNNAME_Name2))
                        || (model.is_ValueChanged(X_C_BPartner.COLUMNNAME_TaxID)) || (model.is_ValueChanged("EMail"))
                        || (model.is_ValueChanged(X_C_BPartner.COLUMNNAME_IsActive))){


                    // Si desactiva cliente, mando marca de delete
                    if (model.is_ValueChanged(X_C_BPartner.COLUMNNAME_IsActive)){
                        if (!model.isActive()){
                            // Marca Delete para Sisteco
                            MZSistecoInterfaceOut sistecoInterfaceOut = MZSistecoInterfaceOut.getRecord(model.getCtx(), I_C_BPartner.Table_ID, model.get_ID(), model.get_TrxName());
                            if ((sistecoInterfaceOut != null) && (sistecoInterfaceOut.get_ID() > 0)){
                                // Proceso segun marca que ya tenía este producto antes de su actualización.
                                // Si marca anterior es CREATE
                                if (sistecoInterfaceOut.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE)){
                                    // Elimino marca anterior de create, ya que finalmente este socio de negocio no va al POS
                                    sistecoInterfaceOut.deleteEx(true);
                                    return mensaje;
                                }
                                else if (sistecoInterfaceOut.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_DELETE)){
                                    // Si marca anterior es DELETEAR, es porque el producto se inactivo anteriormente.
                                    // Si este producto sigue estando inactivo
                                    if (!model.isActive()){
                                        // No hago nada y respeto primer marca.
                                        return mensaje;
                                    }
                                }
                            }
                            // Si no tengo marca de delete, la creo ahora.
                            if ((sistecoInterfaceOut == null) || (sistecoInterfaceOut.get_ID() <= 0)){
                                sistecoInterfaceOut = new MZSistecoInterfaceOut(model.getCtx(), 0, model.get_TrxName());
                                sistecoInterfaceOut.setCRUDType(X_Z_SistecoInterfaceOut.CRUDTYPE_DELETE);
                                sistecoInterfaceOut.setAD_Table_ID(I_C_BPartner.Table_ID);
                                sistecoInterfaceOut.setSeqNo(30);
                                sistecoInterfaceOut.setRecord_ID(model.get_ID());
                                sistecoInterfaceOut.saveEx();
                            }
                        }
                    }
                    else{
                        // Marca Update para Sisteco
                        MZSistecoInterfaceOut sistecoInterfaceOut = MZSistecoInterfaceOut.getRecord(model.getCtx(), I_C_BPartner.Table_ID, model.get_ID(), model.get_TrxName());
                        if ((sistecoInterfaceOut != null) && (sistecoInterfaceOut.get_ID() > 0)){
                            // Proceso segun marca que ya tenía este producto antes de su actualización.
                            // Si marca anterior es CREATE
                            if (sistecoInterfaceOut.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE)){
                                // No hago nada y respeto primer marca
                                return mensaje;
                            }
                            else if (sistecoInterfaceOut.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_DELETE)){
                                // Si marca anterior es DELETEAR, es porque el producto se inactivo anteriormente.
                                // Si este producto sigue estando inactivo
                                if (!model.isActive()){
                                    // No hago nada y respeto primer marca.
                                    return mensaje;
                                }
                            }
                        }
                        // Si no tengo marca de update, la creo ahora.
                        if ((sistecoInterfaceOut == null) || (sistecoInterfaceOut.get_ID() <= 0)){
                            sistecoInterfaceOut = new MZSistecoInterfaceOut(model.getCtx(), 0, model.get_TrxName());
                            sistecoInterfaceOut.setCRUDType(X_Z_SistecoInterfaceOut.CRUDTYPE_UPDATE);
                            sistecoInterfaceOut.setAD_Table_ID(I_C_BPartner.Table_ID);
                            sistecoInterfaceOut.setSeqNo(20);
                            sistecoInterfaceOut.setRecord_ID(model.get_ID());
                            sistecoInterfaceOut.saveEx();
                        }

                    }
                }
            }
        }

        return mensaje;
    }

}
