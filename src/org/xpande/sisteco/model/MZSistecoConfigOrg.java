package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo de organizaciones asociadas a la configuración de proveedor de POS Sisteco.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 2/13/18.
 */
public class MZSistecoConfigOrg extends X_Z_SistecoConfigOrg {

    public MZSistecoConfigOrg(Properties ctx, int Z_SistecoConfigOrg_ID, String trxName) {
        super(ctx, Z_SistecoConfigOrg_ID, trxName);
    }

    public MZSistecoConfigOrg(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
