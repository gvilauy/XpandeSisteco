package org.xpande.sisteco.process;

import org.compiere.process.SvrProcess;
import org.xpande.sisteco.model.MZSistecoInterfacePazos;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande.
 * Xpande. Created by gabriel on 5/26/17.
 */
public class InterfacePazosManual extends SvrProcess{

    private MZSistecoInterfacePazos model = null;

    @Override
    protected void prepare() {
        this.model = new MZSistecoInterfacePazos(getCtx(), this.getRecord_ID(), get_TrxName());
    }

    @Override
    protected String doIt() throws Exception {

        model.setIsBatchProcessed(false);
        model.execute(false);

        return "OK";
    }

}
