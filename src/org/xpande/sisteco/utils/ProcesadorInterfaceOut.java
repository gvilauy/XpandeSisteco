package org.xpande.sisteco.utils;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_M_Product;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.xpande.core.model.I_Z_ProductoUPC;
import org.xpande.core.utils.FileUtils;
import org.xpande.sisteco.model.*;
import sun.misc.resources.Messages_pt_BR;

import java.io.*;
import java.sql.Timestamp;
import java.util.HashMap;
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

    // Contadores de lineas
    private int contadorLinBatch = 0;
    private int contadorLinOnline = 0;

    private String fechaHoy;

    // True = reprocesa una interface ya ejecutada anteriormente y asociada a un ID de comunicacion de POS.
    private boolean reProcessByComPos = false;

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
     * En caso de venir un ID correspondiente un proceso de comunicacin de datos al pos, entonces verifico que datos debo enviar o no.
     * En caso de no venir dicho ID, considero los flags que indican si debo procesar lo correspondiente a productos y socios.
     * Xpande. Created by Gabriel Vila on 7/27/17.
     * @param adOrgID
     * @param zComunicacionPosID
     * @param processPrices
     * @param processProducts
     * @param processPartners
     * @return
     */
    public String executeInterfaceOut(int adOrgID, int zComunicacionPosID, boolean processPrices, boolean processProducts, boolean processPartners){

        String message = null;

        BufferedWriter bufferedWriterBatch = null;
        BufferedWriter bufferedWriterOnline = null;

        try{

            // Si no tengo id de proceso de comunicacion
            if (zComunicacionPosID <= 0){
                // Si no recibo flags de procesar productos o socios, no hago nada
                if (!processProducts && !processPartners){
                    return message;
                }
            }
            else{
                // Siempre comunico socios si recibo ID de proceso de comunicacion de datos al pos
                processPartners = true;
            }

            // Obtengo configurador de sisteco
            this.sistecoConfig = MZSistecoConfig.getDefault(ctx, trxName);

            // Valido que no haya un archivo de interface esperando ser procesado por Sisteco.
            // Esto es para no sobreescribir un archivo generado y aun no procesado por Sisteco, con un archivo nuevo.
            message = this.validateFiles();
            if (message != null){
                return message;
            }

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

                // Copio archivo batch y  count batch, a path destino
                File fileBatchDest = new File( pathArchivosDestino + sistecoConfig.getArchivoBatch());
                FileUtils.copyFile(this.fileBatch, fileBatchDest);

                File fileCountBatchDest = new File( pathArchivosDestino + sistecoConfig.getArchivoCountBatch());
                FileUtils.copyFile(this.fileCountBatch, fileCountBatchDest);

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
    }


    /***
     * Valida que no hay aun archivo de interface generado y no procesado aun por Sisteco.
     * Esto es para evitar sobreescribir dicho archivo con uno nuevo y de esa manera perder información no enviada al pos.
     * Xpande. Created by Gabriel Vila on 1/22/18.
     * @return
     */
    private String validateFiles() {

        String message = null;

        try{

            String pathArchivosDestino = sistecoConfig.getRutaInterfaceOut() + File.separator;

            File fileBatchDest = new File( pathArchivosDestino + sistecoConfig.getArchivoBatch());

            if (fileBatchDest.exists()){
                return "Existe un archivo de interface generado y no procesado aún por Sisteco.\n" + "Debe procesarlo antes de generar uno nuevo.";
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return message;
    }


    /***
     * Procesa interface de salida de clientes para Sisteco.
     * Xpande. Created by Gabriel Vila on 10/9/17.
     * @param adOrgID
     * @param zComunicacionPosID
     * @param bufferedWriterBatch
     * @param bufferedWriterOnline
     * @param sistecoConfig
     * @return
     */
    private String executeInterfaceOutPartners(int adOrgID, int zComunicacionPosID, BufferedWriter bufferedWriterBatch, BufferedWriter bufferedWriterOnline, MZSistecoConfig sistecoConfig) {

        String message = null;

        try{

            // Obtengo y recorro lineas de interface de socios de negocio, segun si es proceso o reproceso de interface
            List<MZSistecoInterfaceOut> interfaceOuts = null;
            if (!this.reProcessByComPos){
                // Lineas de interface aun no ejecutadas
                interfaceOuts = this.getLinesBPartnerNotExecuted(adOrgID);
            }
            else{
                // Lineas de interface ya ejecutadas anteriormente y asociadas a un ID de comunicacion al POS.
                interfaceOuts = this.getLinesBPartnerByComPos(adOrgID, zComunicacionPosID);
            }

            for (MZSistecoInterfaceOut interfaceOut: interfaceOuts){

                List<String> lineasArchivo = interfaceOut.getLineasArchivoBPartner(adOrgID, this.sistecoConfig.getSeparadorArchivoOut(), sistecoConfig);
                for (String lineaArchivo: lineasArchivo){

                    bufferedWriterBatch.append(lineaArchivo);
                    bufferedWriterOnline.append(lineaArchivo);

                    bufferedWriterBatch.newLine();
                    bufferedWriterOnline.newLine();

                    this.contadorLinBatch++;
                    this.contadorLinOnline++;

                }
                if (lineasArchivo.size() > 0){
                    // Marco linea como ejecutada
                    interfaceOut.setIsExecuted(true);
                    interfaceOut.setDateExecuted(new Timestamp(System.currentTimeMillis()));

                    if (zComunicacionPosID > 0){
                        interfaceOut.setZ_ComunicacionPOS_ID(zComunicacionPosID);
                    }

                    interfaceOut.saveEx();
                }
            }

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
     * Guarda linea de error en archivo de errores.
     * Xpande. Created by Gabriel Vila on 7/29/17.
     * @param lineaError
     */
    private void setBatchErrorLine(String lineaError) {

        BufferedWriter bufferedWriterBatch = null;

        try{

            FileWriter fileWriterBatch = new FileWriter(this.fileBatchError, true);
            bufferedWriterBatch = new BufferedWriter(fileWriterBatch);

            bufferedWriterBatch.append(lineaError);
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
     * @param zComunicacionPosID
     * @param processPrices
     * @param bufferedWriterBatch
     * @param bufferedWriterOnline @return
     */
    private String executeInterfaceOutProducts(int adOrgID, int zComunicacionPosID, boolean processPrices, BufferedWriter bufferedWriterBatch, BufferedWriter bufferedWriterOnline) {

        String message = null;
        String action = "";

        HashMap<Integer, Integer> hashProds = new HashMap<Integer, Integer>();

        try{

            Timestamp fechaHoy = TimeUtil.trunc(new Timestamp(System.currentTimeMillis()), TimeUtil.TRUNC_DAY);

            // Obtengo y recorro lineas de interface de productos, segun si es proceso o reproceso de interface
            List<MZSistecoInterfaceOut> interfaceOuts = null;
            if (!this.reProcessByComPos){
                // Lineas de interface aun no ejecutadas
                interfaceOuts = this.getLinesProdsNotExecuted(adOrgID, zComunicacionPosID, processPrices);
            }
            else{
                // Lineas de interface ya ejecutadas anteriormente para reprocesar, asociadas a un ID de Comunicación al POS.
                interfaceOuts = this.getLinesProdsByComPos(adOrgID, zComunicacionPosID, processPrices);
            }

            int contador = 0;
            for (MZSistecoInterfaceOut interfaceOut: interfaceOuts){

                contador++;
                System.out.println(contador + " - " + interfaceOuts.size());

                MProduct product = new MProduct(this.ctx, interfaceOut.getRecord_ID(), this.trxName);
                if ((product == null) || (product.get_ID() <= 0)){
                    continue;
                }

                // Me aseguro que el producto tenga atributos asociados, sino se los creo ahora.
                // Esto es momentaneo para crear los atributos de los productos migrados.
                String sql = " select count(*) from z_productoatribsisteco where m_product_id =" + interfaceOut.getRecord_ID();
                int contadorAtribs = DB.getSQLValue(this.trxName, sql);
                if (contadorAtribs <= 0){
                    // Asociación de atributos que requiere Sisteco al nuevo producto
                    SistecoUtils.setProductAttributes(this.ctx, product, this.trxName);

                    // Obtengo y guardo valor Hexadecimal segun seteos de atributos para el producto recibido.
                    // Solo si el producto tiene unidad de medida o tandem asociado.
                    if ((product.getC_UOM_ID() > 0) || (product.get_ValueAsInt("M_Product_Tandem_ID") > 0)){
                        String valorHexadecimal = SistecoUtils.getHexadecimalAtributos(this.ctx, product, this.trxName);
                        action = " update m_product set atributoshexa ='" + valorHexadecimal + "' " +
                                 " where m_product_id =" + product.get_ID();
                        DB.executeUpdateEx(action, this.trxName);
                    }
                }

                // Si es marca de oferta y su vigencia no es acorde a la fecha actual, no proceso esta marca
                if (interfaceOut.isWithOfferSO()){
                    if ((interfaceOut.getStartDate().after(fechaHoy)) || (interfaceOut.getEndDate().before(fechaHoy))){
                        continue;
                    }
                }

                // Si la marca para este producto es de CREAR, guardo id de producto en hash para luego ver consideración o no
                // de códigos de barras.
                if (interfaceOut.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE)){
                    if (!hashProds.containsKey(product.get_ID())){
                        hashProds.put(product.get_ID(), product.get_ID());
                    }
                }

                List<String> lineasArchivo = interfaceOut.getLineasArchivoProducto(adOrgID, this.sistecoConfig.getSeparadorArchivoOut(), product);
                for (String lineaArchivo: lineasArchivo){

                    bufferedWriterBatch.append(lineaArchivo);
                    bufferedWriterOnline.append(lineaArchivo);

                    bufferedWriterBatch.newLine();
                    bufferedWriterOnline.newLine();

                    this.contadorLinBatch++;
                    this.contadorLinOnline++;

                    /*
                    // Marco el producto como comunicado al POS
                    if (interfaceOut.getCRUDType().equalsIgnoreCase(X_Z_SistecoInterfaceOut.CRUDTYPE_CREATE)){
                        action = " update m_product set ComunicadoPOS ='Y' where m_product_id =" + product.get_ID();
                        DB.executeUpdateEx(action, this.trxName);
                    }
                    */
                }

                // Si procese lineas para este producto.
                if (lineasArchivo.size() > 0){

                    // Marco linea como ejecutada
                    interfaceOut.setIsExecuted(true);
                    interfaceOut.setDateExecuted(new Timestamp(System.currentTimeMillis()));
                    if (zComunicacionPosID > 0){
                        interfaceOut.setZ_ComunicacionPOS_ID(zComunicacionPosID);
                    }
                    interfaceOut.saveEx();

                    // Guardo auditoria de Comunicacion para organización - producto.
                    MZSistecoProdOrgCom sistecoProdOrgCom = new MZSistecoProdOrgCom(this.ctx, 0, this.trxName);
                    sistecoProdOrgCom.setAD_OrgTrx_ID(adOrgID);
                    sistecoProdOrgCom.setCRUDType(interfaceOut.getCRUDType());
                    sistecoProdOrgCom.setDateDoc(new Timestamp(System.currentTimeMillis()));
                    if (this.fileBatch != null){
                        sistecoProdOrgCom.setFileName(this.fileBatch.getName());
                    }
                    sistecoProdOrgCom.setM_Product_ID(product.get_ID());
                    sistecoProdOrgCom.setPriceSO(interfaceOut.getPriceSO());

                    if (zComunicacionPosID > 0){
                        sistecoProdOrgCom.setZ_ComunicacionPOS_ID(zComunicacionPosID);
                    }
                    sistecoProdOrgCom.setZ_SistecoInterfaceOut_ID(interfaceOut.get_ID());
                    sistecoProdOrgCom.saveEx();
                }
            }

            // Obtengo y recorro lineas de interface de productos, segun si es proceso o reproceso de interface
            if (!this.reProcessByComPos){
                // Lineas de interface aun no ejecutadas
                interfaceOuts = this.getLinesUPCNotExecuted(adOrgID);
            }
            else{
                // Lineas de interface ya ejecutadas anteriormente y asociadas a un ID de comunicacion de POS.
                interfaceOuts = this.getLinesUPCByComPos(adOrgID, zComunicacionPosID);
            }

            for (MZSistecoInterfaceOut interfaceOut: interfaceOuts){
                List<String> lineasArchivo = interfaceOut.getLineasArchivoUPC(adOrgID, this.sistecoConfig.getSeparadorArchivoOut(), processPrices, hashProds);
                for (String lineaArchivo: lineasArchivo){

                    bufferedWriterBatch.append(lineaArchivo);
                    bufferedWriterOnline.append(lineaArchivo);

                    bufferedWriterBatch.newLine();
                    bufferedWriterOnline.newLine();

                    this.contadorLinBatch++;
                    this.contadorLinOnline++;

                }
                if (lineasArchivo.size() > 0){
                    // Marco linea como ejecutada
                    interfaceOut.setIsExecuted(true);
                    interfaceOut.setDateExecuted(new Timestamp(System.currentTimeMillis()));
                    if (zComunicacionPosID > 0){
                        interfaceOut.setZ_ComunicacionPOS_ID(zComunicacionPosID);
                    }
                    interfaceOut.saveEx();
                }
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        return message;
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


    /***
     * Obtiene y retorna lineas de interface de salida para productos no ejecutadas al momento.
     * En caso de recibir un id de proceso de comunicacion de datos al pos, debo filtrar segun proceso o no precios.
     * Xpande. Created by Gabriel Vila on 7/24/17.
     * @return
     * @param adOrgID
     * @param zComunicacionPosID
     * @param processPrices
     */
    private List<MZSistecoInterfaceOut> getLinesProdsNotExecuted(int adOrgID, int zComunicacionPosID, boolean processPrices){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_IsExecuted + " ='N' " +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_Table_ID + " =" + I_M_Product.Table_ID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_OrgTrx_ID + " =" + adOrgID;

        Timestamp fechaHoy = TimeUtil.trunc(new Timestamp(System.currentTimeMillis()), TimeUtil.TRUNC_DAY);

        // Si recibo ID de proceso de comunicacion de datos al pos
        if (zComunicacionPosID > 0){
            // Si en este proceso No se quiere comunicar precios de productos
            if (!processPrices){
                // No proceso ninguna marca de crear o actualizar productos. Solo considero las marcas de eliminar y aquellas marcas de
                // actualización pero que no sean por cambios de precio
                whereClause += " AND ((" + X_Z_SistecoInterfaceOut.COLUMNNAME_CRUDType + " ='" + X_Z_SistecoInterfaceOut.CRUDTYPE_DELETE + "') " +
                        " OR (" + X_Z_SistecoInterfaceOut.COLUMNNAME_CRUDType + " ='" + X_Z_SistecoInterfaceOut.CRUDTYPE_UPDATE + "'" +
                        " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_IsPriceChanged + " ='N')) ";
            }
            else {
                // Solo debo conisderar marcas de aquellos productos contenidos en el proceso de comunicacion de datos al pos.
                whereClause += " AND ((" + X_Z_SistecoInterfaceOut.COLUMNNAME_Record_ID + " IN " +
                        " (select m_product_id from z_confirmacionetiquetaprod " +
                        " where WithOfferSO ='N' and z_confirmacionetiquetadoc_id in " +
                        " (select z_confirmacionetiquetadoc_id from z_confirmacionetiquetadoc " +
                        " where comunicadopos='N' and isselected='Y' and isconfirmed='Y' " +
                        " and ((DateToPos is null) or (DateToPos <='" + fechaHoy + "')) " +
                        " and z_confirmacionetiqueta_id in " +
                        " (select z_confirmacionetiqueta_id from z_confirmacionetiqueta where z_comunicacionpos_id =" + zComunicacionPosID + ")))) ";

                whereClause += " OR (((" + X_Z_SistecoInterfaceOut.COLUMNNAME_CRUDType + " ='" + X_Z_SistecoInterfaceOut.CRUDTYPE_DELETE + "') " +
                        " OR (" + X_Z_SistecoInterfaceOut.COLUMNNAME_CRUDType + " ='" + X_Z_SistecoInterfaceOut.CRUDTYPE_UPDATE + "'" +
                        " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_IsPriceChanged + " ='N')))) ";
            }
        }

        List<MZSistecoInterfaceOut> lines = new Query(ctx, I_Z_SistecoInterfaceOut.Table_Name, whereClause, trxName).setOrderBy(" SeqNo, Created  ").list();

        return lines;

    }


    /***
     * Obtiene y retorna lineas de interface correspondientes a productos segun un determinado ID de comunicacio a POS.
     * No importa si ya fueron ejecutadas anteriormente o no.
     * Xpande. Created by Gabriel Vila on 3/27/18.     
     * @param adOrgID
     * @param zComunicacionPosID
     * @param processPrices
     * @return
     */
    private List<MZSistecoInterfaceOut> getLinesProdsByComPos(int adOrgID, int zComunicacionPosID, boolean processPrices){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_Z_ComunicacionPOS_ID + " =" + zComunicacionPosID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_Table_ID + " =" + I_M_Product.Table_ID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_OrgTrx_ID + " =" + adOrgID;

        List<MZSistecoInterfaceOut> lines = new Query(ctx, I_Z_SistecoInterfaceOut.Table_Name, whereClause, trxName).setOrderBy(" SeqNo, Created  ").list();

        return lines;

    }
    

    /***
     * Obtiene y retorna lineas de interface de salida para códigos de barras no ejecutadas al momento.
     * Xpande. Created by Gabriel Vila on 7/24/17.
     * @param adOrgID
     * @return
     */
    private List<MZSistecoInterfaceOut> getLinesUPCNotExecuted(int adOrgID){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_IsExecuted + " ='N' " +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_Table_ID + " =" + I_Z_ProductoUPC.Table_ID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_OrgTrx_ID + " =" + adOrgID;

        List<MZSistecoInterfaceOut> lines = new Query(ctx, I_Z_SistecoInterfaceOut.Table_Name, whereClause, trxName).setOrderBy(" SeqNo, Created  ").list();

        return lines;

    }


    /***
     * Obtiene y retorna lineas de interface de salida para codigos de barra según un determinado ID de comunicacion al POS.
     * Xpande. Created by Gabriel Vila on 3/27/18.
     * @param adOrgID
     * @param zComunicacionPosID
     * @return
     */
    private List<MZSistecoInterfaceOut> getLinesUPCByComPos(int adOrgID, int zComunicacionPosID){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_Z_ComunicacionPOS_ID + " =" + zComunicacionPosID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_Table_ID + " =" + I_Z_ProductoUPC.Table_ID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_OrgTrx_ID + " =" + adOrgID;

        List<MZSistecoInterfaceOut> lines = new Query(ctx, I_Z_SistecoInterfaceOut.Table_Name, whereClause, trxName).setOrderBy(" SeqNo, Created  ").list();

        return lines;

    }

    /***
     * Obtiene y retorna lineas de interface de salida para socios de negocio no ejecutadas al momento.
     * Xpande. Created by Gabriel Vila on 7/24/17.
     * @param adOrgID
     * @return
     */
    private List<MZSistecoInterfaceOut> getLinesBPartnerNotExecuted(int adOrgID){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_IsExecuted + " ='N' " +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_Table_ID + " =" + I_C_BPartner.Table_ID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_OrgTrx_ID + " =" + adOrgID;

        List<MZSistecoInterfaceOut> lines = new Query(ctx, I_Z_SistecoInterfaceOut.Table_Name, whereClause, trxName).setOrderBy(" SeqNo, Created  ").list();

        return lines;

    }


    /***
     * Obtiene y retorna lineas de interface para socios de negocio según ID determinado de comunicacion al pos.
     * Xpande. Created by Gabriel Vila on 3/27/18.
     * @param adOrgID
     * @param zComunicacionPosID
     * @return
     */
    private List<MZSistecoInterfaceOut> getLinesBPartnerByComPos(int adOrgID, int zComunicacionPosID){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_Z_ComunicacionPOS_ID + " =" + zComunicacionPosID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_Table_ID + " =" + I_C_BPartner.Table_ID +
                " AND " + X_Z_SistecoInterfaceOut.COLUMNNAME_AD_OrgTrx_ID + " =" + adOrgID;

        List<MZSistecoInterfaceOut> lines = new Query(ctx, I_Z_SistecoInterfaceOut.Table_Name, whereClause, trxName).setOrderBy(" SeqNo, Created  ").list();

        return lines;

    }


    /***
     * Obtiene y retorna lista de marcas procesadas para un determinado proceso de Comunicacion de datos al POS.
     * Xpande. Created by Gabriel Vila on 10/13/17.
     * @param zComunicacionPosID
     * @return
     */
    public List<MZSistecoInterfaceOut> getMarcasProcesadas(int zComunicacionPosID) {

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_Z_ComunicacionPOS_ID + " =" + zComunicacionPosID;

        List<MZSistecoInterfaceOut> lines = new Query(this.ctx, I_Z_SistecoInterfaceOut.Table_Name, whereClause, this.trxName).list();

        return lines;

    }

    /***
     * Invoca al metodo de ejecucion de interface pero recibe parametro que indica si es un reproceso de interface ya ejecutada
     * anteriormente y asociada a una comunicación de POS.
     * Xpande. Created by Gabriel Vila on 3/27/18.
     * @param adOrgID
     * @param zComunicacionPosID
     * @param processPrices
     * @param processProducts
     * @param processPartners
     * @param reProcessByComPos
     * @return
     */
    public String executeInterfaceOut(int adOrgID, int zComunicacionPosID, boolean processPrices, boolean processProducts,
                                      boolean processPartners, boolean reProcessByComPos){

        this.reProcessByComPos = reProcessByComPos;

        return this.executeInterfaceOut(adOrgID, zComunicacionPosID, processPrices, processProducts, processPartners);
    }

}
