package org.xpande.sisteco.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.impexp.ImpFormat;
import org.compiere.util.CLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 5/27/17.
 */
public class MZSistecoInterfacePazos extends X_Z_SistecoInterfacePazos {

    //Logger
    private CLogger log = CLogger.getCLogger (getClass());

    public MZSistecoInterfacePazos(Properties ctx, int Z_SistecoInterfacePazos_ID, String trxName) {
        super(ctx, Z_SistecoInterfacePazos_ID, trxName);
    }

    public MZSistecoInterfacePazos(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /***
     * Xpande. Created by Gabriel Vila on 5/27/17.
     * Ejecuta interface Pazos de Sisteco considerando el archivo seleccionado.
     * @param isBatchProcessed : True si el proceso se corre de manera Batch, False si lo corre el usuario de manera manual.
     */
    public void execute(boolean isBatchProcessed) {

        FileReader fReader = null;
        BufferedReader bReader = null;

        try {

            ImpFormat formatoImpLineaVenta = ImpFormat.load ("Sisteco_Pazos_LineaVenta");

            this.setIsBatchProcessed(isBatchProcessed);
            this.setStartDate(new Timestamp(System.currentTimeMillis()));

            // Abro archivo
            File archivoPazos = new File(this.getFileName());
            fReader = new FileReader(archivoPazos);
            bReader = new BufferedReader(fReader);

            // Leo lineas del archivo
            String lineaArchivo = bReader.readLine();

            while (lineaArchivo != null){

                if (!this.IsLineaArchivoCabezal(lineaArchivo)){

                    String lineSplit[] = lineaArchivo.split("\\|");

                    if (lineSplit[2].toString().trim().equalsIgnoreCase("1")){
                        formatoImpLineaVenta.updateDB(getCtx(), lineaArchivo, get_TrxName());
                    }
                }

                lineaArchivo = bReader.readLine();
            }


            this.setEndDate(new Timestamp(System.currentTimeMillis()));
            this.saveEx();
        }
        catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            throw new AdempiereException(e);
        }
        finally {
            if (bReader != null){
                try{
                    bReader.close();
                    if (fReader != null){
                        fReader.close();
                    }
                }
                catch (Exception e){
                    log.log(Level.SEVERE, e.getMessage());
                }
            }
        }
    }

    /***
     * Xpande. Created by Gabriel Vila on 5/28/17.
     * Si la linea recibida corresponde a un cabezal, retorna True, sino retorna False.
     * @param lineaArchivo
     * @return
     */
    private boolean IsLineaArchivoCabezal(String lineaArchivo){

        try{
            if (lineaArchivo != null){
                if (lineaArchivo.substring(0, 1).equalsIgnoreCase("C")){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return false;
    }

}
