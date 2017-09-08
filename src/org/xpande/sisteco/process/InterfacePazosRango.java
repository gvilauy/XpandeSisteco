package org.xpande.sisteco.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Trx;
import org.xpande.sisteco.model.MZSistecoConfig;
import org.xpande.sisteco.model.MZSistecoInterfacePazos;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.stream.Stream;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 9/8/17.
 */
public class InterfacePazosRango extends SvrProcess {

    private Timestamp startDate = null;
    private Timestamp endDate = null;

    @Override
    protected void prepare() {

        ProcessInfoParameter[] para = getParameter();

        for (int i = 0; i < para.length; i++){

            String name = para[i].getParameterName();

            if (name != null){
                if (para[i].getParameter() != null){
                    if (name.trim().equalsIgnoreCase("DateTrx")){
                        this.startDate = (Timestamp)para[i].getParameter();
                        this.endDate = (Timestamp)para[i].getParameter_To();
                    }
                }
            }
        }

    }

    @Override
    protected String doIt() throws Exception {

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.setTime(this.startDate);
        end.setTime(this.endDate);

        MZSistecoConfig sistecoConfig = MZSistecoConfig.getDefault(getCtx(), null);

        // Loop recorriendo rango de fechas ingresadas
        while( !start.after(end)){

            Timestamp fechaProceso = new Timestamp(start.getTime().getTime());

            Trx trans = null;
            try{

                String trxName = Trx.createTrxName();
                trans = Trx.get(trxName, true);

                MZSistecoInterfacePazos interfacePazos = new MZSistecoInterfacePazos(getCtx(), 0, trxName);
                interfacePazos.set_ValueOfColumn("AD_Client_ID", sistecoConfig.getAD_Client_ID());
                interfacePazos.setAD_Org_ID(sistecoConfig.getAD_Org_ID());
                interfacePazos.saveEx();

                String message = interfacePazos.execute(true, fechaProceso);

                trans.close();
            }
            catch (Exception e){
                if (trans != null){
                    trans.rollback();
                }
                throw new AdempiereException(e);
            }

            start.add(Calendar.DATE, 1);
        }

        return "OK";
    }
}
