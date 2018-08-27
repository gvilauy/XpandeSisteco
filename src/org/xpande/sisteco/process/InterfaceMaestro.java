package org.xpande.sisteco.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.SvrProcess;
import org.xpande.core.utils.FileUtils;
import org.xpande.sisteco.model.MZSistecoConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 8/14/18.
 */
public class InterfaceMaestro extends SvrProcess {

    private  MZSistecoConfig sistecoConfig = null;

    // Archivos
    private File fileBatch = null;
    private File fileCountBatch = null;
    private File fileOnline = null;
    private File fileCountOnline = null;
    private File fileBatchError = null;

    // Contadores de lineas
    private int contadorLinBatch = 0;
    private int contadorLinOnline = 0;

    private String fechaHoy;


    @Override
    protected void prepare() {

    }

    @Override
    protected String doIt() throws Exception {

        /*

        String message = "OK";

        BufferedWriter bufferedWriterBatch = null;
        BufferedWriter bufferedWriterOnline = null;

        try{
            // Obtengo configurador de sisteco
            this.sistecoConfig = MZSistecoConfig.getDefault(getCtx(), get_TrxName());

            // Creación de archivos de interface
            this.createFiles();

            // IO
            FileWriter fileWriterBatch = new FileWriter(this.fileBatch, true);
            FileWriter fileWriterOnline = new FileWriter(this.fileOnline, true);

            bufferedWriterBatch = new BufferedWriter(fileWriterBatch);
            bufferedWriterOnline = new BufferedWriter(fileWriterOnline);

            // Proceso lineas de interface de salida correspondiente a productos
            if ((zComunicacionPosID > 0) || (processProducts)){
                message = this.executeInterfaceOutProducts(adOrgID, zComunicacionPosID, processPrices, bufferedWriterBatch, bufferedWriterOnline);
                if (message != null) return message;
            }

            // Proces lineas de socios de negocio
            if (processPartners){
                message = this.executeInterfaceOutPartners(adOrgID, zComunicacionPosID, bufferedWriterBatch, bufferedWriterOnline, sistecoConfig);
                if (message != null) return message;
            }

            if (bufferedWriterBatch != null){
                bufferedWriterBatch.close();
            }

            if (bufferedWriterOnline != null){
                bufferedWriterOnline.close();
            }

            String pathArchivosDestino = sistecoConfig.getRutaInterfaceOut() + File.separator;

            // Si tengo lineas en archivos batch
            if (this.contadorLinBatch > 0){
                this.setBatchLines(this.contadorLinBatch);

                // Copio archivo batch a path destino
                File fileBatchDest = new File( pathArchivosDestino + sistecoConfig.getArchivoBatch());
                FileUtils.copyFile(this.fileBatch, fileBatchDest);

            }

            // Si tengo lineas en archivos online
            if (this.contadorLinOnline > 0){
                this.setOnlineLines(this.contadorLinOnline);
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            if (bufferedWriterBatch != null){
                try {
                    bufferedWriterBatch.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedWriterOnline != null){
                try {
                    bufferedWriterOnline.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        return message;
        */
        return null;
    }


    /***
     * Creación de archivos de interface de salida requeridos por Sisteco.
     * Xpande. Created by Gabriel Vila on 7/24/17.
     */
    private void createFiles() {

        try{

            String[] hora = (new Timestamp(System.currentTimeMillis()).toString().split(sistecoConfig.getSeparadorArchivoOut()));
            String fecha =hora[0].replace("-", "").replace(" ", "_") + hora[1];

            this.fechaHoy = fecha;

            String pathArchivos = sistecoConfig.getRutaInterfaceOutHist() + File.separator + this.fechaHoy;

            fileBatch = new File( pathArchivos + sistecoConfig.getArchivoBatch());
            fileOnline = new File( pathArchivos + sistecoConfig.getArchivoOnline());
            fileCountBatch = new File( pathArchivos + sistecoConfig.getArchivoCountBatch());
            fileCountOnline = new File( pathArchivos + sistecoConfig.getArchivoCountOnline());

            fileBatchError = new File(sistecoConfig.getRutaInterfaceOutHist() + File.separator + "ArchDeErrores" + File.separator +
                    this.fechaHoy + sistecoConfig.getArchivoBatchError());

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

    }


}
