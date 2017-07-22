package org.xpande.sisteco.model;

import org.compiere.model.Query;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 6/1/17.
 * Modelo para configuración del proceso de Interface con Sisteco
 */
public class MZSistecoConfig extends X_Z_SistecoConfig {

    public MZSistecoConfig(Properties ctx, int Z_SistecoConfig_ID, String trxName) {
        super(ctx, Z_SistecoConfig_ID, trxName);
    }

    public MZSistecoConfig(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /***
     * Xpande. Created by Gabriel Vila on 6/1/17.
     * Obtiene modelo único de configuración del proceso de Interface contra Sisteco.
     * @param ctx
     * @param trxName
     * @return
     */
    public static MZSistecoConfig getDefault(Properties ctx, String trxName){

        MZSistecoConfig model = new Query(ctx, I_Z_SistecoConfig.Table_Name, "", trxName).first();

        return model;
    }

    /***
     * Xpande. Created by Gabriel Vila on 6/1/17.
     * Obtiene y retorna lista de modelos para tipos de lineas de archivo Pazos de Sisteco.
     * @return
     */
    public List<MZSistecoTipoLineaPazos> getTipoLineasPazos(){

        String whereClause = X_Z_SistecoTipoLineaPazos.COLUMNNAME_Z_SistecoConfig_ID + "=" + this.get_ID();

        List<MZSistecoTipoLineaPazos> lines = new Query(getCtx(), I_Z_SistecoTipoLineaPazos.Table_Name, whereClause, get_TrxName()).list();

        return lines;

    }

    /***
     * Obtiene y retorna lista de atributos de sisteco para sus productos, ordenados por secuencia.
     * Xpande. Created by Gabriel Vila on 7/21/17.
     * @return
     */
    public List<MZSistecoAtributoProd> getAtributosProducto(){

        String whereClause = X_Z_SistecoAtributoProd.COLUMNNAME_Z_SistecoConfig_ID + " =" + this.get_ID();

        List<MZSistecoAtributoProd> lines = new Query(getCtx(), I_Z_SistecoAtributoProd.Table_Name, whereClause, get_TrxName()).setOnlyActiveRecords(true)
                .setOrderBy(" SeqNo").list();

        return lines;
    }
}
