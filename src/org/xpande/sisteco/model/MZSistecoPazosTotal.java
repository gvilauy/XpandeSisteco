package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para guardar totales resultado del proceso de interface contra un archivo Pazos de Sisteco.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 6/6/17.
 */
public class MZSistecoPazosTotal extends X_Z_SistecoPazosTotal {

    public MZSistecoPazosTotal(Properties ctx, int Z_SistecoPazosTotal_ID, String trxName) {
        super(ctx, Z_SistecoPazosTotal_ID, trxName);
    }

    public MZSistecoPazosTotal(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

}
