package org.xpande.sisteco.utils;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_M_Product;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.xpande.core.model.I_Z_ProductoUPC;
import org.xpande.sisteco.model.I_Z_SistecoInterfaceOut;
import org.xpande.sisteco.model.MZSistecoConfig;
import org.xpande.sisteco.model.MZSistecoInterfaceOut;
import org.xpande.sisteco.model.X_Z_SistecoInterfaceOut;
import sun.misc.resources.Messages_pt_BR;

import java.io.*;
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

            // Cargo archivos contadores de lineas batch y online
            int contadorLineasBatch = this.getCountBatchLines();
            int contadorLineasOnline = this.getCountOnlineLines();

            if (contadorLineasBatch > 0){
                this.setBatchLines(contadorLineasBatch);
            }

            if (contadorLineasOnline > 0){
                this.setOnlineLines(contadorLineasBatch);
            }

            // Copiar archivos creados en path destino de Sisteco


        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return message;
    }

    /***
     * Guarda cantidad de lineas batch generadas en archivo de lineas batch.
     * Xpande. Created by Gabriel Vila on 7/29/17.
     * @param contadorLineasBatch
     */
    private void setBatchLines(int contadorLineasBatch) {

        BufferedWriter bufferedWriterBatch = null;

        try{

            FileWriter fileWriterBatch = new FileWriter(this.fileCountBatch, false);
            bufferedWriterBatch = new BufferedWriter(fileWriterBatch);

            bufferedWriterBatch.append(String.valueOf(contadorLineasBatch));
            bufferedWriterBatch.newLine();
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
        }
    }


    /***
     * Guarda cantidad de lineas batch generadas en archivo de lineas batch.
     * Xpande. Created by Gabriel Vila on 7/29/17.
     * @param contadorLineasOnline
     */
    private void setOnlineLines(int contadorLineasOnline) {

        BufferedWriter bufferedWriterOnline = null;

        try{

            FileWriter fileWriterOnline = new FileWriter(this.fileCountOnline, false);
            bufferedWriterOnline = new BufferedWriter(fileWriterOnline);

            bufferedWriterOnline.append(String.valueOf(contadorLineasOnline));
            bufferedWriterOnline.newLine();
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            if (bufferedWriterOnline != null){
                try {
                    bufferedWriterOnline.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /***
     * Obtiene y retorna cantidad de lineas generadas en el archivo Batch durante este proceso.
     * Xpande. Created by Gabriel Vila on 7/29/17.
     * @return
     */
    private int getCountBatchLines() {

        LineNumberReader reader = null;

        try{
            reader = new LineNumberReader(new FileReader(this.fileBatch));

            while ((reader.readLine()) != null);

            return reader.getLineNumber();

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     * Obtiene y retorna cantidad de lineas generadas en el archivo Online durante este proceso.
     * Xpande. Created by Gabriel Vila on 7/29/17.
     * @return
     */
    private int getCountOnlineLines() {

        LineNumberReader reader = null;

        try{
            reader = new LineNumberReader(new FileReader(this.fileOnline));

            while ((reader.readLine()) != null);

            return reader.getLineNumber();

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

                // Me aseguro que el producto tenga atributos asociados, sino se los creo ahora.
                // Esto es momentaneo para crear los atributos de los productos migrados.
                String sql = " select count(*) from z_productoatribsisteco where m_product_id =" + interfaceOut.getRecord_ID();
                int contadorAtribs = DB.getSQLValue(this.trxName, sql);
                if (contadorAtribs <= 0){
                    // Asociación de atributos que requiere Sisteco al nuevo producto
                    MProduct product = new MProduct(this.ctx, interfaceOut.getRecord_ID(), this.trxName);
                    SistecoUtils.setProductAttributes(this.ctx, product, this.trxName);

                    // Obtengo y guardo valor Hexadecimal segun seteos de atributos para el producto recibido.
                    // Solo si el producto tiene unidad de medida o tandem asociado.
                    if ((product.getC_UOM_ID() > 0) || (product.get_ValueAsInt("M_Product_Tandem_ID") > 0)){
                        String valorHexadecimal = SistecoUtils.getHexadecimalAtributos(this.ctx, product, this.trxName);
                        String action = " update m_product set atributoshexa ='" + valorHexadecimal + "' " +
                                " where m_product_id =" + product.get_ID();
                        DB.executeUpdateEx(action, this.trxName);
                    }
                }

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

            // Obtengo y recorro lineas de interface aun no ejecutadas para códigos de barra de productos
            interfaceOuts = this.getLinesUPCNotExecuted();
            for (MZSistecoInterfaceOut interfaceOut: interfaceOuts){
                List<String> lineasArchivo = interfaceOut.getLineasArchivoUPC(adOrgID, this.sistecoConfig.getSeparadorArchivoOut());
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

            fchToday = fecha;

            String pathArchivos = sistecoConfig.getRutaInterfaceOutHist() + File.separator + fchToday;

            fileBatch = new File( pathArchivos + sistecoConfig.getArchivoBatch());
            fileOnline = new File( pathArchivos + sistecoConfig.getArchivoOnline());
            fileCountBatch = new File( pathArchivos + sistecoConfig.getArchivoCountBatch());
            fileCountOnline = new File( pathArchivos + sistecoConfig.getArchivoCountOnline());

            fileBatchError = new File(sistecoConfig.getRutaInterfaceOutHist() + File.separator + "ArchDeErrores" + File.separator + fchToday + sistecoConfig.getArchivoBatchError());

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
     * Obtiene y retorna lineas de interface de salida para códigos de barras no ejecutadas al momento
     * Xpande. Created by Gabriel Vila on 7/24/17.
     * @return
     */
    private List<MZSistecoInterfaceOut> getLinesUPCNotExecuted(){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_IsExecuted + " ='N' " +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_Table_ID + " =" + I_Z_ProductoUPC.Table_ID;

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
