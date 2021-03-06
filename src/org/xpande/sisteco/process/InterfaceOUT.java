package org.xpande.sisteco.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.xpande.sisteco.utils.ProcesadorInterfaceOut;

import java.math.BigDecimal;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 7/27/17.
 */
public class InterfaceOUT extends SvrProcess {

    private ProcesadorInterfaceOut procesadorInterfaceOut = null;
    private int adOrgID = 1000000;
    private int zComunicacionPosID = 0;

    @Override
    protected void prepare() {

        ProcessInfoParameter[] para = getParameter();

        for (int i = 0; i < para.length; i++){

            String name = para[i].getParameterName();

            if (name != null){
                if (para[i].getParameter() != null){
                    if (name.trim().equalsIgnoreCase("AD_Org_ID")){
                        this.adOrgID = ((BigDecimal)para[i].getParameter()).intValueExact();
                    }
                    else if (name.trim().equalsIgnoreCase("Z_ComunicacionPOS_ID")){
                        this.zComunicacionPosID = ((BigDecimal)para[i].getParameter()).intValueExact();
                    }
                }
            }
        }

        this.procesadorInterfaceOut = new ProcesadorInterfaceOut(getCtx(), get_TrxName());
    }

    @Override
    protected String doIt() throws Exception {

        String message = null;

        // Si recibo ID de comunicacion al POS, entonces RE-PROCESO interface ya ejecutada anteriormente y asociada con el ID recibido de comunicacion al POS.
        if (this.zComunicacionPosID > 0){
            message = this.procesadorInterfaceOut.executeInterfaceOut(this.adOrgID, this.zComunicacionPosID,true, true, true, true);
        }
        else{
            message = this.procesadorInterfaceOut.executeInterfaceOut(this.adOrgID, 0,true, true, true);
        }

        if (message != null){
            return "@Error@ " + message;
        }

        return "OK";
    }
}
