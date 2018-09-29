package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para guardar errores en lineas leídas del archivo Pazos de Sisteco.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 6/1/17.
 */
public class MZSistecoTKLineaError extends X_Z_Sisteco_TK_LineaError {

    public MZSistecoTKLineaError(Properties ctx, int Z_Sisteco_TK_LineaError_ID, String trxName) {
        super(ctx, Z_Sisteco_TK_LineaError_ID, trxName);
    }

    public MZSistecoTKLineaError(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
