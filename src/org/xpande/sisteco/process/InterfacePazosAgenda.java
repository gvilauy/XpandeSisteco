package org.xpande.sisteco.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.xpande.sisteco.model.MZSistecoConfig;
import org.xpande.sisteco.model.MZSistecoInterfacePazos;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Proceso para lectura del archivo pazos de sisteco. Lanzado automaticamente por Schedule del sistema.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande.
 * Xpande. Created by gabriel on 5/26/17.
 */
public class InterfacePazosAgenda extends SvrProcess{

    private Timestamp fechaProceso = null;

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
                }
            }
        }

    }

    @Override
    protected String doIt() throws Exception {

        MZSistecoConfig sistecoConfig = MZSistecoConfig.getDefault(getCtx(), null);

        MZSistecoInterfacePazos interfacePazos = new MZSistecoInterfacePazos(getCtx(), 0, get_TrxName());
        interfacePazos.set_ValueOfColumn("AD_Client_ID", sistecoConfig.getAD_Client_ID());
        interfacePazos.setAD_Org_ID(sistecoConfig.getAD_Org_ID());
        interfacePazos.saveEx();

        String message = interfacePazos.execute(true, this.fechaProceso);

        if (message != null){
            return "@Error@ " + message;
        }

        return "OK";

    }

}
