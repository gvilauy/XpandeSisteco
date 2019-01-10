package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para asociar usuarios para notificaciones por email de eventos de interface con Sisteco.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 1/9/19.
 */
public class MZSistecoEmail extends X_Z_SistecoEmail {

    public MZSistecoEmail(Properties ctx, int Z_SistecoEmail_ID, String trxName) {
        super(ctx, Z_SistecoEmail_ID, trxName);
    }

    public MZSistecoEmail(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
