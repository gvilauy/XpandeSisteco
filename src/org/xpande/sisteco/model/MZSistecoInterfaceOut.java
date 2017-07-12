package org.xpande.sisteco.model;

import org.compiere.model.Query;

import java.sql.ResultSet;
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

}
