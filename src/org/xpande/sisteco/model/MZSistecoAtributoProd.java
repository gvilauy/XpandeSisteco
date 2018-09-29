package org.xpande.sisteco.model;

import org.compiere.model.Query;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo de atributo requerido por Sisteco en sus productos.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 7/21/17.
 */
public class MZSistecoAtributoProd extends X_Z_SistecoAtributoProd {

    public MZSistecoAtributoProd(Properties ctx, int Z_SistecoAtributoProd_ID, String trxName) {
        super(ctx, Z_SistecoAtributoProd_ID, trxName);
    }

    public MZSistecoAtributoProd(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }


    /***
     * Obtiene y retorna modelo de seteo de atributo segun unidad de medida recibido.
     * Xpande. Created by Gabriel Vila on 7/21/17.
     * @param cUOMID
     * @return
     */
    public MZSistecoAtributoProdUOM getUOMSet(int cUOMID){

        String whereClause = X_Z_SistecoAtributoProdUOM.COLUMNNAME_Z_SistecoAtributoProd_ID + " =" + this.get_ID() +
                " AND " + X_Z_SistecoAtributoProdUOM.COLUMNNAME_C_UOM_ID + " =" + cUOMID;

        MZSistecoAtributoProdUOM model = new Query(getCtx(), I_Z_SistecoAtributoProdUOM.Table_Name, whereClause, get_TrxName()).first();

        return model;
    }

}
