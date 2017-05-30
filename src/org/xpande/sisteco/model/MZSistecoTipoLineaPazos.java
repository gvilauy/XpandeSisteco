package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 6/1/17.
 * Modelo de tipos de linea de un arrchivo Pazos de Sisteco.
 */
public class MZSistecoTipoLineaPazos extends X_Z_SistecoTipoLineaPazos {

    public MZSistecoTipoLineaPazos(Properties ctx, int Z_SistecoTipoLineaPazos_ID, String trxName) {
        super(ctx, Z_SistecoTipoLineaPazos_ID, trxName);
    }

    public MZSistecoTipoLineaPazos(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
