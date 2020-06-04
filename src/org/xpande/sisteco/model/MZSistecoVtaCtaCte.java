package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 6/4/20.
 */
public class MZSistecoVtaCtaCte extends X_Z_SistecoVtaCtaCte{

    public MZSistecoVtaCtaCte(Properties ctx, int Z_SistecoVtaCtaCte_ID, String trxName) {
        super(ctx, Z_SistecoVtaCtaCte_ID, trxName);
    }

    public MZSistecoVtaCtaCte(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
