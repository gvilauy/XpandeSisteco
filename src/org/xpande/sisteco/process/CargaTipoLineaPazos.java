package org.xpande.sisteco.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.xpande.sisteco.model.MZSistecoConfig;
import org.xpande.sisteco.model.MZSistecoConfigOrg;
import org.xpande.sisteco.model.MZSistecoInterfacePazos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Proceso para cargar un determinado tipo de linea de un archivo de interface Pazos de Sisteco.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 4/22/20.
 */
public class CargaTipoLineaPazos extends SvrProcess {

    private Timestamp fechaProceso = null;
    private int adOrgID = 0;
    private String tipoLinea = null;

    @Override
    protected void prepare() {

        ProcessInfoParameter[] para = getParameter();

        for (int i = 0; i < para.length; i++){

            String name = para[i].getParameterName();

            if (name != null){
                if (para[i].getParameter() != null){
                    if (name.trim().equalsIgnoreCase("DateTrx")){
                        this.fechaProceso = (Timestamp) para[i].getParameter();
                    }
                    else if (name.trim().equalsIgnoreCase("AD_Org_ID")){
                        this.adOrgID = ((BigDecimal)para[i].getParameter()).intValueExact();
                    }
                    else if (name.trim().equalsIgnoreCase("ST_TipoLinea")){
                        this.tipoLinea = para[i].getParameter().toString().trim();
                    }
                }
            }
        }
    }

    @Override
    protected String doIt() throws Exception {

        MZSistecoConfig sistecoConfig = MZSistecoConfig.getDefault(getCtx(), null);

        // Si indico organización, proceso solo para esta, sino proceso para todas las que tenga asociadas a SISTECO
        List<MZSistecoConfigOrg> orgList = sistecoConfig.getOrganizationsByOrg(this.adOrgID);

        // Seteo info de contexto
        Env.setContext(getCtx(), "AD_Client_ID", sistecoConfig.getAD_Client_ID());

        for (MZSistecoConfigOrg configOrg: orgList){

            // Seteo info de contexto
            Env.setContext(getCtx(), "AD_Org_ID", configOrg.getAD_OrgTrx_ID());

            MZSistecoInterfacePazos interfacePazos = MZSistecoInterfacePazos.getByOrgDate(getCtx(), configOrg.getAD_OrgTrx_ID(), this.fechaProceso, get_TrxName());

            String message = interfacePazos.executeTipoLinea(this.tipoLinea);

            if (message != null){
                return "@Error@ " + message;
            }
        }

        return "OK";
    }

}
