package org.xpande.sisteco.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.impexp.ImpFormat;
import org.compiere.model.MTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

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

            ImpFormat formatoImpLineaVenta = ImpFormat.load("Sisteco_Pazos_LineaVenta");
            ImpFormat formatoImpCabezal = ImpFormat.load("Sisteco_Pazos_CabezalTicket");

            this.setIsBatchProcessed(isBatchProcessed);
            this.setStartDate(new Timestamp(System.currentTimeMillis()));

            // Abro archivo
            File archivoPazos = new File(this.getFileName());
            fReader = new FileReader(archivoPazos);
            bReader = new BufferedReader(fReader);

            int contLineas = 0;
            int zSistecoTkCVtaID = 0;
            String action = "";

            // Leo lineas del archivo
            String lineaArchivo = bReader.readLine();

            while (lineaArchivo != null){

                contLineas++;

                String nodos[] = lineaArchivo.split("\\|");

                if (this.IsLineaArchivoCabezal(lineaArchivo)){

                    zSistecoTkCVtaID = formatoImpCabezal.updateDB(lineaArchivo, getCtx(), get_TrxName());

                    if (zSistecoTkCVtaID <= 0){
                        throw new AdempiereException("Error al insertar linea cabezal " + contLineas + " : " + lineaArchivo);
                    }
                    else{

                        // Seteo ID del proceso de interface en el cabezal
                        action = " update " +  I_Z_Sisteco_TK_CVta.Table_Name +
                                " set " + X_Z_Sisteco_TK_CVta.COLUMNNAME_Z_SistecoInterfacePazos_ID + " = " + this.get_ID() +
                                " where " + X_Z_Sisteco_TK_CVta.COLUMNNAME_Z_Sisteco_TK_CVta_ID + " = " + zSistecoTkCVtaID;
                        DB.executeUpdateEx(action, get_TrxName());

                    }
                }
                else{

                    ImpFormat formatoImpLinea = formatoImpLineaVenta;
                    MTable tablaFormato = new MTable(getCtx(), formatoImpLinea.getAD_Table_ID(), null);

                    if (nodos[2].toString().trim().equalsIgnoreCase("1")){

                        int ID = formatoImpLineaVenta.updateDB(lineaArchivo, getCtx(), get_TrxName());

                        if (ID <= 0){
                            throw new AdempiereException("Falla en linea " + contLineas + " : " + lineaArchivo);
                        }

                        // Seteo ID de cabezal en tabla de linea
                        action = " update " +  tablaFormato.getTableName() +
                                " set " + X_Z_Sisteco_TK_CVta.COLUMNNAME_Z_Sisteco_TK_CVta_ID + " = " + zSistecoTkCVtaID +
                                " where " + tablaFormato.getTableName() + "_ID = " + ID;
                        DB.executeUpdateEx(action, get_TrxName());
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
