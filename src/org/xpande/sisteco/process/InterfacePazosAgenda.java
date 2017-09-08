package org.xpande.sisteco.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.xpande.sisteco.model.MZSistecoInterfacePazos;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Proceso para lectura del archivo pazos de sisteco. Lanzado automaticamente por Schedule del sistema.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande.
 * Xpande. Created by gabriel on 5/26/17.
 */
public class InterfacePazosAgenda extends SvrProcess{

    private MZSistecoInterfacePazos model = null;
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

        this.model = new MZSistecoInterfacePazos(getCtx(), this.getRecord_ID(), get_TrxName());
    }

    @Override
    protected String doIt() throws Exception {

        String message = model.execute(true, this.fechaProceso);

        if (message != null){
            return "@Error@ " + message;
        }

        return "OK";

    }

}
