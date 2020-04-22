package org.xpande.sisteco.model;

import org.compiere.model.Query;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo de cabezal de ticket de archivo Pazos de Sisteco
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 6/1/17.
 */
public class MZSistecoTKCVta extends X_Z_Sisteco_TK_CVta {

    public MZSistecoTKCVta(Properties ctx, int Z_Sisteco_TK_CVta_ID, String trxName) {
        super(ctx, Z_Sisteco_TK_CVta_ID, trxName);
    }

    public MZSistecoTKCVta(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /***
     * Obtiene y retorna modelo segun ID de interface y numero de ticket recibidos.
     * Xpande. Created by Gabriel Vila on 4/22/20.
     * @param ctx
     * @param zSistecoInterfacePazosID
     * @param numeroTicket
     * @param trxName
     * @return
     */
    public static MZSistecoTKCVta getByInterfaceTicket(Properties ctx, int zSistecoInterfacePazosID, String numeroTicket, String trxName) {

        String whereClause = X_Z_Sisteco_TK_CVta.COLUMNNAME_Z_SistecoInterfacePazos_ID + " =" + zSistecoInterfacePazosID +
                " AND " + X_Z_Sisteco_TK_CVta.COLUMNNAME_ST_NumeroTicket + " ='" + numeroTicket + "'";

        MZSistecoTKCVta model = new Query(ctx, I_Z_Sisteco_TK_CVta.Table_Name, whereClause, trxName).first();

        return model;
    }

}
