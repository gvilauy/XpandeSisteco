package org.xpande.sisteco.model;

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
}
