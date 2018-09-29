package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para informe de ventas por RUT y Ticker desglozadas por Impuesto de Sisteco.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 8/19/17.
 */
public class MZSistecoPazosTaxTKRUT extends X_Z_SistecoPazosTaxTKRUT {

    public MZSistecoPazosTaxTKRUT(Properties ctx, int Z_SistecoPazosTaxTKRUT_ID, String trxName) {
        super(ctx, Z_SistecoPazosTaxTKRUT_ID, trxName);
    }

    public MZSistecoPazosTaxTKRUT(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
