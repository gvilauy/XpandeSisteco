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


    /***
     * Obtiene y retorna modelo de parametrizacion de departamento de sisteco, segun nombre de departamento recibido.
     * Xpande. Created by Gabriel Vila on 10/9/17.
     * @param departamento
     * @return
     */
    public MZSistecoRegion getByDepartamento(String departamento){

        String whereClause = X_Z_SistecoRegion.COLUMNNAME_Z_SistecoConfig_ID + " =" + this.get_ID() +
                " AND " + X_Z_SistecoRegion.COLUMNNAME_RegionName + " ='" + departamento + "'";

        MZSistecoRegion model = new Query(getCtx(), I_Z_SistecoRegion.Table_Name, whereClause, get_TrxName()).first();

        return model;
    }


    /***
     * Obtiene y retorna lista de organizaciones asociadas a la configuracion del proveedor de POS Sisteco.
     * Xpande. Created by Gabriel Vila on 2/13/18.
     * @return
     */
    public List<MZSistecoConfigOrg> getOrganization(){

        String whereClause = X_Z_SistecoConfigOrg.COLUMNNAME_Z_SistecoConfig_ID + " =" + this.get_ID();

        List<MZSistecoConfigOrg> lines = new Query(getCtx(), I_Z_SistecoConfigOrg.Table_Name, whereClause, get_TrxName()).setOnlyActiveRecords(true).list();

        return lines;
    }


    /***
     * Obtiene y retorna lista de organizaciones asociadas a la configuracion del proveedor de POS Sisteco.
     * Xpande. Created by Gabriel Vila on 2/13/18.
     * @return
     */
    public List<MZSistecoConfigOrg> getOrganizationsByOrg(int adOrgTrxID){

        String whereClause = X_Z_SistecoConfigOrg.COLUMNNAME_Z_SistecoConfig_ID + " =" + this.get_ID();

        if (adOrgTrxID > 0){
            whereClause += " AND " + X_Z_SistecoConfigOrg.COLUMNNAME_AD_OrgTrx_ID + " =" + adOrgTrxID;
        }

        List<MZSistecoConfigOrg> lines = new Query(getCtx(), I_Z_SistecoConfigOrg.Table_Name, whereClause, get_TrxName()).setOnlyActiveRecords(true).list();

        return lines;
    }


    /***
     * Obtiene y retorna lista de usuarios para notificaciones de email de interfaces de sisteco.
     * Xpande. Created by Gabriel Vila on 1/9/19.
     * @return
     */
    public List<MZSistecoEmail> getEmailUsers(){

        String whereClause = X_Z_SistecoEmail.COLUMNNAME_Z_SistecoConfig_ID + " =" + this.get_ID();

        List<MZSistecoEmail> lines = new Query(getCtx(), I_Z_SistecoEmail.Table_Name, whereClause, get_TrxName()).setOnlyActiveRecords(true).list();

        return lines;
    }

}
