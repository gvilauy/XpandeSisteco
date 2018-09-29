package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para indicar seteo de atributo de producto, segun unidad de medida del producto.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 7/21/17.
 */
public class MZSistecoAtributoProdUOM extends X_Z_SistecoAtributoProdUOM {

    public MZSistecoAtributoProdUOM(Properties ctx, int Z_SistecoAtributoProdUOM_ID, String trxName) {
        super(ctx, Z_SistecoAtributoProdUOM_ID, trxName);
    }

    public MZSistecoAtributoProdUOM(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
