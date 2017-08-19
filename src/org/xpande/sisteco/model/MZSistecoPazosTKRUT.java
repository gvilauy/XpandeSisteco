package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para información de ventas por rut-ticket en Sisteco.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 8/19/17.
 */
public class MZSistecoPazosTKRUT extends X_Z_SistecoPazosTKRUT{

    public MZSistecoPazosTKRUT(Properties ctx, int Z_SistecoPazosTKRUT_ID, String trxName) {
        super(ctx, Z_SistecoPazosTKRUT_ID, trxName);
    }

    public MZSistecoPazosTKRUT(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
