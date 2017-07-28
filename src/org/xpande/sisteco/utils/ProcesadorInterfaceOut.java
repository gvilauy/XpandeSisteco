package org.xpande.sisteco.utils;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_M_Product;
import org.compiere.model.Query;
import org.xpande.sisteco.model.I_Z_SistecoInterfaceOut;
import org.xpande.sisteco.model.MZSistecoConfig;
import org.xpande.sisteco.model.MZSistecoInterfaceOut;
import org.xpande.sisteco.model.X_Z_SistecoInterfaceOut;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import static org.adempiere.util.DateUtil.getDateString;

/**
 * Clase para el proceso de interface de salida de datos desde el sistema hacia Sisteco.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 7/22/17.
 */
public class ProcesadorInterfaceOut {

    private Properties ctx = null;
    private String trxName = null;

    // Configurador de Sisteco
    private MZSistecoConfig sistecoConfig = null;

    // Archivos
    private File fileBatch = null;
    private File fileCountBatch = null;
    private File fileOnline = null;
    private File fileCountOnline = null;
    private File fileBatchError = null;

    private String fchToday;
    private String fchTodayUpdate = "";
    private static final String SEPARATOR_L = ":";//caracter separador de las lineas de los archivos a escribir

    /***
     * Constructor
     * @param ctx
     * @param trxName
     */
    public ProcesadorInterfaceOut(Properties ctx, String trxName) {
        this.ctx = ctx;
        this.trxName = trxName;
    }


    /***
     * Ejecuta proceso de interface de salida para Sisteco.
     * Xpande. Created by Gabriel Vila on 7/27/17.
     * @param processProducts
     * @param processPartners
     * @return
     */
    public String executeInterfaceOut(int adOrgID, boolean processProducts, boolean processPartners){

        String message = null;

        try{

            if (!processProducts && !processPartners) return message;

            // Obtengo configurador de sisteco
            this.sistecoConfig = MZSistecoConfig.getDefault(ctx, trxName);

            // Creación de archivos de interface
            this.createFiles();

            // Proceso lineas de interface de salida correspondiente a productos
            if (processProducts){
                message = this.executeInterfaceOutProducts(adOrgID);
                if (message != null) return message;
            }

            // Proces lineas de socios de negocio
            if (processPartners){
                //message = this.executeInterfaceOutPartners();
                if (message != null) return message;
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return message;
    }


    /***
     * Procesa interface de salida de productos para Sisteco.
     * Xpande. Created by Gabriel Vila on 7/27/17.
     * @param adOrgID
     * @return
     */
    private String executeInterfaceOutProducts(int adOrgID) {

        String message = null;

        BufferedWriter bufferedWriterBatch = null;
        BufferedWriter bufferedWriterOnline = null;


        try{

            FileWriter fileWriterBatch = new FileWriter(this.fileBatch, true);
            FileWriter fileWriterOnline = new FileWriter(this.fileOnline, true);

            bufferedWriterBatch = new BufferedWriter(fileWriterBatch);
            bufferedWriterOnline = new BufferedWriter(fileWriterOnline);

            // Obtengo y recorro lineas de interface aun no ejecutadas para productos
            List<MZSistecoInterfaceOut> interfaceOuts = this.getLinesProdsNotExecuted();
            for (MZSistecoInterfaceOut interfaceOut: interfaceOuts){
                List<String> lineasArchivo = interfaceOut.getLineasArchivoProducto(adOrgID, this.sistecoConfig.getSeparadorArchivoOut());
                for (String lineaArchivo: lineasArchivo){

                    bufferedWriterBatch.append(lineaArchivo);
                    bufferedWriterOnline.append(lineaArchivo);

                    bufferedWriterBatch.newLine();
                    bufferedWriterOnline.newLine();

                }
                if (lineasArchivo.size() > 0){
                    // Marco linea como ejecutada
                    //interfaceOut.setIsExecuted(true);
                    //interfaceOut.saveEx();
                }
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
                    bufferedWriterBatch.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return message;
    }


    /***
     * Creación de archivos de interface de salida requeridos por Sisteco.
     * Xpande. Created by Gabriel Vila on 7/24/17.
     */
    private void createFiles() {

        try{

            String[] hra = (new Timestamp(System.currentTimeMillis()).toString().split(sistecoConfig.getSeparadorArchivoOut()));
            String fecha =hra[0].replace("-", "").replace(" ", "_")+hra[1];
            fchTodayUpdate = getDateString();
            fchToday = fecha;

            String pathArchivos = sistecoConfig.getRutaInterfaceOut() + File.separator + fchToday;

            fileBatch = new File( pathArchivos + sistecoConfig.getArchivoBatch());
            fileOnline = new File( pathArchivos + sistecoConfig.getArchivoOnline());
            fileCountBatch = new File( pathArchivos + sistecoConfig.getArchivoCountBatch());
            fileCountOnline = new File( pathArchivos + sistecoConfig.getArchivoCountOnline());

            fileBatchError = new File(sistecoConfig.getRutaInterfaceOut() + File.separator + "ArchDeErrores" + File.separator + fchToday + sistecoConfig.getArchivoBatchError());

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

    }


    /***
     * Obtiene y retorna lineas de interface de salida para productos no ejecutadas al momento
     * Xpande. Created by Gabriel Vila on 7/24/17.
     * @return
     */
    private List<MZSistecoInterfaceOut> getLinesProdsNotExecuted(){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_IsExecuted + " ='N' " +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_Table_ID + " =" + I_M_Product.Table_ID;

        List<MZSistecoInterfaceOut> lines = new Query(ctx, I_Z_SistecoInterfaceOut.Table_Name, whereClause, trxName).setOrderBy(" SeqNo, Created  ").list();

        return lines;

    }

    /***
     * Obtiene y retorna lineas de interface de salida para socios de negocio no ejecutadas al momento.
     * Xpande. Created by Gabriel Vila on 7/24/17.
     * @return
     */
    private List<MZSistecoInterfaceOut> getLinesBPartnerNotExecuted(){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_IsExecuted + " ='N' " +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_Table_ID + " =" + I_C_BPartner.Table_ID;

        List<MZSistecoInterfaceOut> lines = new Query(ctx, I_Z_SistecoInterfaceOut.Table_Name, whereClause, trxName).setOrderBy(" SeqNo, Created  ").list();

        return lines;

    }
}
