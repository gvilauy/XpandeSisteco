package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para desplegar informacion de desgloce de ventas por impuesto en interface pazos de Sisteco.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 8/14/17.
 */
public class MZSistecoPazosTax extends X_Z_SistecoPazosTax {

    public MZSistecoPazosTax(Properties ctx, int Z_SistecoPazosTax_ID, String trxName) {
        super(ctx, Z_SistecoPazosTax_ID, trxName);
    }

    public MZSistecoPazosTax(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
