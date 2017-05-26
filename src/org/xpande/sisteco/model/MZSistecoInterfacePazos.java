package org.xpande.sisteco.model;

import org.adempiere.exceptions.AdempiereException;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 5/27/17.
 */
public class MZSistecoInterfacePazos extends X_Z_SistecoInterfacePazos {

    public MZSistecoInterfacePazos(Properties ctx, int Z_SistecoInterfacePazos_ID, String trxName) {
        super(ctx, Z_SistecoInterfacePazos_ID, trxName);
    }

    public MZSistecoInterfacePazos(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /***
     * Xpande. Created by Gabriel Vila on 5/27/17.
     * Ejecuta interface Pazos de Sisteco considerando el archivo seleccionado.
     * @param isBatchProcessed
     */
    public void execute(boolean isBatchProcessed) {

        try {

            this.setIsBatchProcessed(isBatchProcessed);
            this.setStartDate(new Timestamp(System.currentTimeMillis()));



            this.setEndDate(new Timestamp(System.currentTimeMillis()));
            this.saveEx();
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
    }
}
