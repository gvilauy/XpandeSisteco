package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo de asociación de un atributo de producto de sisteco a un determinado producto.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 7/21/17.
 */
public class MZProductoAtribSisteco extends X_Z_ProductoAtribSisteco {

    public MZProductoAtribSisteco(Properties ctx, int Z_ProductoAtribSisteco_ID, String trxName) {
        super(ctx, Z_ProductoAtribSisteco_ID, trxName);
    }

    public MZProductoAtribSisteco(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
