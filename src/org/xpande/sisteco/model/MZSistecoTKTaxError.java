package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para log de productos con diferencia de impuestos entre Adempiere y Sisteco para una determinada interface con Pazos.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 4/15/18.
 */
public class MZSistecoTKTaxError extends X_Z_Sisteco_TK_TaxError {

    public MZSistecoTKTaxError(Properties ctx, int Z_Sisteco_TK_TaxError_ID, String trxName) {
        super(ctx, Z_Sisteco_TK_TaxError_ID, trxName);
    }

    public MZSistecoTKTaxError(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
