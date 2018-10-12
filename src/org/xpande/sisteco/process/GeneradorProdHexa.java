package org.xpande.sisteco.process;

import javafx.beans.property.MapProperty;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProduct;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.xpande.sisteco.utils.SistecoUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Proceso que genera Atributos Hexa en productos que no lo tienen, para POS Sisteco.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 10/3/18.
 */
public class GeneradorProdHexa extends SvrProcess {

    @Override
    protected void prepare() {

    }

    @Override
    protected String doIt() throws Exception {

        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{

            /*
            sql = " select m_product_id " +
                    " from m_product " +
                    " where isactive ='Y' " +
                    " and issold ='Y' " +
                    " and atributoshexa is null ";
            */

            sql = " select m_product_id " +
                    " from m_product " +
                    " where isactive ='Y' " +
                    " and issold ='Y' ";

            pstmt = DB.prepareStatement(sql, null);
        	rs = pstmt.executeQuery();

        	/*
        	while(rs.next()){

                // Me aseguro que el producto tenga atributos asociados, sino se los creo ahora.
                sql = " select count(*) from z_productoatribsisteco where m_product_id =" + rs.getInt("m_product_id");
                int contadorAtribs = DB.getSQLValue(null, sql);
                if (contadorAtribs <= 0){

                    MProduct product = new MProduct(getCtx(), rs.getInt("m_product_id"), null);

                    // Asociación de atributos que requiere Sisteco al nuevo producto
                    SistecoUtils.setProductAttributes(getCtx(), product, null);

                    // Obtengo y guardo valor Hexadecimal segun seteos de atributos para el producto recibido.
                    // Solo si el producto tiene unidad de medida o tandem asociado.
                    if ((product.getC_UOM_ID() > 0) || (product.get_ValueAsInt("M_Product_Tandem_ID") > 0)){
                        String valorHexadecimal = SistecoUtils.getHexadecimalAtributos(getCtx(), product, get_TrxName());
                        String action = " update m_product set atributoshexa ='" + valorHexadecimal + "' " +
                                " where m_product_id =" + product.get_ID();
                        DB.executeUpdateEx(action, null);
                    }
                }
            }
            */

            String action = "";

            while(rs.next()){

                // Elimino atributos del producto y los genero nuevamente
                action = " delete from z_productoatribsisteco where m_product_id =" + rs.getInt("m_product_id");
                DB.executeUpdateEx(action, null);

                MProduct product = new MProduct(getCtx(), rs.getInt("m_product_id"), null);

                // Asociación de atributos que requiere Sisteco al nuevo producto
                SistecoUtils.setProductAttributes(getCtx(), product, null);

                // Obtengo y guardo valor Hexadecimal segun seteos de atributos para el producto recibido.
                // Solo si el producto tiene unidad de medida o tandem asociado.
                if ((product.getC_UOM_ID() > 0) || (product.get_ValueAsInt("M_Product_Tandem_ID") > 0)){
                    String valorHexadecimal = SistecoUtils.getHexadecimalAtributos(getCtx(), product, get_TrxName());
                    action = " update m_product set atributoshexa ='" + valorHexadecimal + "' " +
                            " where m_product_id =" + product.get_ID();
                    DB.executeUpdateEx(action, null);
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
