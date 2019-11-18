package org.xpande.sisteco.model;

import org.compiere.model.MTax;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para asociación de tasas de impuesto entre Sisteco y ADempiere.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 11/18/19.
 */
public class MZSistecoImpuesto extends X_Z_SistecoImpuesto {

    public MZSistecoImpuesto(Properties ctx, int Z_SistecoImpuesto_ID, String trxName) {
        super(ctx, Z_SistecoImpuesto_ID, trxName);
    }

    public MZSistecoImpuesto(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    @Override
    protected boolean beforeSave(boolean newRecord) {

        // Seteo categoría de impuesto según tasa de impuesto seleccionada
        if ((newRecord) || (is_ValueChanged(X_Z_SistecoImpuesto.COLUMNNAME_C_Tax_ID))){
            MTax tax = new MTax(getCtx(), this.getC_Tax_ID(), null);
            this.setC_TaxCategory_ID(tax.getC_TaxCategory_ID());
        }

        return true;
    }
}
