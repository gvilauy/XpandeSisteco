package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para parametrización de departamentos de Sisteco en proceso de interface Pazos.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 10/9/17.
 */
public class MZSistecoRegion extends X_Z_SistecoRegion {

    public MZSistecoRegion(Properties ctx, int Z_SistecoRegion_ID, String trxName) {
        super(ctx, Z_SistecoRegion_ID, trxName);
    }

    public MZSistecoRegion(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }


}
