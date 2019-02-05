package org.xpande.sisteco.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para auditoria de comunicación al pos de productos - organizacion.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 10/25/18.
 */
public class MZSistecoProdOrgCom extends X_Z_SistecoProdOrgCom {

    public MZSistecoProdOrgCom(Properties ctx, int Z_SistecoProdOrgCom_ID, String trxName) {
        super(ctx, Z_SistecoProdOrgCom_ID, trxName);
    }

    public MZSistecoProdOrgCom(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }


    /***
     * Verfica si en una determinada organización, un determinado producto fue comunicado al pos.
     * Xpande. Created by Gabriel Vila on 10/25/18.
     * @param ctx
     * @param mProductID
     * @param adOrgTrxID
     * @param trxName
     * @return
     */
    public static boolean isComunicadoPOS(Properties ctx, int mProductID, int adOrgTrxID, String trxName){

        boolean result = false;

        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select z_sistecoprodorgcom_id " +
                    "from z_sistecoprodorgcom " +
                    "where ad_orgtrx_id =" + adOrgTrxID +
                    "and m_product_id =" + mProductID +
                    "and crudtype IN('C','U') ";

        	pstmt = DB.prepareStatement(sql, trxName);
        	rs = pstmt.executeQuery();

        	if (rs.next()){
                result = true;
        	}
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
        	rs = null; pstmt = null;
        }

        return result;
    }

}
