package org.xpande.sisteco.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.impexp.ImpFormat;
import org.compiere.impexp.MImpFormat;
import org.compiere.model.MEXPFormat;
import org.compiere.model.MTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 5/27/17.
 */
public class MZSistecoInterfacePazos extends X_Z_SistecoInterfacePazos {

    //Logger
    private CLogger log = CLogger.getCLogger (getClass());

    // Modelo de configuración del proceso de Interface
    private MZSistecoConfig sistecoConfig = null;

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

        try{

            this.setIsBatchProcessed(isBatchProcessed);

            // Timpo de inicio de proceso
            this.setStartDate(new Timestamp(System.currentTimeMillis()));

            // Limpio descripcion anterior
            if ((this.getDescription() != null) && (!this.getDescription().trim().equalsIgnoreCase(""))){
                DB.executeUpdateEx( "update z_sistecointerfacepazos set description ='' where z_sistecointerfacepazos_id =" + this.get_ID(), null);
            }

            // Instancio modelo de configuración del proceso de Interface
            if (this.sistecoConfig == null) this.sistecoConfig = MZSistecoConfig.getDefault(getCtx(), null);

            // Obengo información leyendo el archivo seleccionado
            this.getDataFromFile();

            // Actualizo campos necesarios
            this.updateData();

            // Sumarizo información cargada
            this.setTotals();

            // Tiempo final de proceso
            this.setEndDate(new Timestamp(System.currentTimeMillis()));

            this.saveEx();

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
    }

    /***
     * Lee archivo a procesar y va guardando información en base de datos.
     * Xpande. Created by Gabriel Vila on 6/5/17.
     */
    private void getDataFromFile() {

        FileReader fReader = null;
        BufferedReader bReader = null;

        HashMap<String, ImpFormat> hashFormatosImp = new HashMap<String, ImpFormat>();
        String mensaje = "";
        MZSistecoTKCVta cabezalTicket = null;
        String lineaArchivo = null;

        try {

            // Obtengo formatos de importación desde configuración del proceso de Interface
            //ImpFormat formatoImpLineaVenta = ImpFormat.load("Sisteco_Pazos_LineaVenta");
            ImpFormat formatoImpCabezal = ImpFormat.load("Sisteco_Pazos_CabezalTicket");
            hashFormatosImp = this.getFormatosImp();

            // Abro archivo
            File archivoPazos = new File(this.getFileName());
            fReader = new FileReader(archivoPazos);
            bReader = new BufferedReader(fReader);

            int contLineas = 0;
            int zSistecoTkCVtaID = 0;
            String action = "";

            // Leo lineas del archivo
            lineaArchivo = bReader.readLine();

            while (lineaArchivo != null) {

                contLineas++;

                String nodos[] = lineaArchivo.split("\\|");

                if (this.IsLineaArchivoCabezal(lineaArchivo)) {

                    zSistecoTkCVtaID = formatoImpCabezal.updateDB(lineaArchivo, getCtx(), get_TrxName());

                    if (zSistecoTkCVtaID <= 0) {
                        mensaje = "Error al insertar linea cabezal " + contLineas + " : " + lineaArchivo;
                        throw new AdempiereException(mensaje);
                    } else {

                        // Seteo ID del proceso de interface en el cabezal
                        action = " update " + I_Z_Sisteco_TK_CVta.Table_Name +
                                 " set " + X_Z_Sisteco_TK_CVta.COLUMNNAME_Z_SistecoInterfacePazos_ID + " = " + this.get_ID() + ", " +
                                 " datetrx = (select to_timestamp(st_timestampticket, 'YYYYMMDDHH24MISS')) " +
                                 " where " + X_Z_Sisteco_TK_CVta.COLUMNNAME_Z_Sisteco_TK_CVta_ID + " = " + zSistecoTkCVtaID;
                        DB.executeUpdateEx(action, get_TrxName());

                        // Instancio modelo de este cabezal
                        cabezalTicket = new MZSistecoTKCVta(getCtx(), zSistecoTkCVtaID, get_TrxName());

                    }
                }
                // Es una tipo de linea que no es Cabezal
                else {

                    // Obtengo formato de importación según tipo de linea indicado en esta linea de archivo
                    String tipoLinea = nodos[2].toString().trim();
                    ImpFormat formatoImpLinea = hashFormatosImp.get(tipoLinea);
                    //ImpFormat formatoImpLinea = formatoImpLineaVenta;

                    if (formatoImpLinea != null) {

                        // Proceso formato de importación para esta linea en la tabla asociada al formato
                        MTable tablaFormato = new MTable(getCtx(), formatoImpLinea.getAD_Table_ID(), null);
                        int ID = formatoImpLinea.updateDB(lineaArchivo, getCtx(), get_TrxName());

                        if (ID <= 0) {
                            mensaje = "Falla en linea " + contLineas + " : " + lineaArchivo;
                            throw new AdempiereException(mensaje);
                        }

                        // Seteo ID de cabezal y demas campos en tabla de linea
                        action = " update " + tablaFormato.getTableName() +
                                " set " + X_Z_Sisteco_TK_CVta.COLUMNNAME_Z_Sisteco_TK_CVta_ID + " = " + zSistecoTkCVtaID + ", " +
                                " st_positionfile ='" + String.valueOf(contLineas) + "', " +
                                X_Z_Sisteco_TK_CVta.COLUMNNAME_Z_SistecoInterfacePazos_ID + " = " + this.get_ID() + ", " +
                                " datetrx = (select to_timestamp(to_char('" + cabezalTicket.getDateTrx() + "', 'YYYYMMDD') || st_timestamplinea, 'YYYYMMDDHH24MISS')) " +
                                " where " + tablaFormato.getTableName() + "_ID = " + ID;
                        DB.executeUpdateEx(action, get_TrxName());

                    }
                    else {
                        // Formato desconocido, guardo excepción
                        MZSistecoTKLineaError lineaError = new MZSistecoTKLineaError(getCtx(), 0, get_TrxName());
                        lineaError.setST_LineaArchivo(lineaArchivo);
                        lineaError.setST_TipoLinea(tipoLinea);
                        lineaError.setST_PositionFile(String.valueOf(contLineas));
                        lineaError.setZ_Sisteco_TK_CVta_ID(zSistecoTkCVtaID);
                        lineaError.setFileName(this.getFileName());
                        lineaError.saveEx();
                    }
                }

                lineaArchivo = bReader.readLine();
            }

        }
        catch (Exception e){
            if (lineaArchivo == null){
                lineaArchivo="";
            }
            DB.executeUpdate(" update " + this.get_TableName() +
                    " set description ='Error en linea: " + lineaArchivo + "\n" + e.getMessage() + "' " +
                    " where " + X_Z_SistecoInterfacePazos.COLUMNNAME_Z_SistecoInterfacePazos_ID + " =" + this.get_ID(), null);
            log.log(Level.SEVERE, e.getMessage() + "\nLinea Archivo :" + lineaArchivo);
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
     * Xpande. Created by Gabriel Vila on 6/1/17
     * Obtiene y retorna formatos de impresión desde la configuración del proceso de Interface
     * @return
     */
    private HashMap<String, ImpFormat> getFormatosImp() {

        HashMap<String, ImpFormat> hashValues = new HashMap<String, ImpFormat>();

        try{

            List<MZSistecoTipoLineaPazos> tipos = this.sistecoConfig.getTipoLineasPazos();

            for (MZSistecoTipoLineaPazos tipoLinea: tipos) {
                if (tipoLinea.getAD_ImpFormat_ID() > 0){

                    MImpFormat mimp = (MImpFormat) tipoLinea.getAD_ImpFormat();
                    ImpFormat impFormat = ImpFormat.load(mimp.getName().trim());

                    hashValues.put(tipoLinea.getValue().trim(), impFormat);
                }
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return hashValues;
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


    /***
     * Se actualizan aquellos campos necesarios en las tablas de sisteco en adempiere.
     * Xpande. Created by Gabriel Vila on 6/5/17.
     */
    private void updateData() {

        String action = "";

        try{

            // Actualizo producto en tabla de lineas de venta
            action = " update Z_Sisteco_TK_LVta set m_product_id = prod.m_product_id " +
                    " from m_product prod " +
                    " where Z_Sisteco_TK_LVta.st_codigoarticulo = prod.value " +
                    " and Z_Sisteco_TK_LVta.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_LVta.st_codigoarticulo is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            action = " update Z_Sisteco_TK_LVta set m_product_id = prod.m_product_id " +
                    " from Z_productoupc pupc " +
                    " where Z_Sisteco_TK_LVta.st_codigoarticulooriginal = pupc.upc " +
                    " and Z_Sisteco_TK_LVta.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_LVta.st_codigoarticulo is null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo producto en tabla de devoluciones de items
            action = " update Z_Sisteco_TK_LDev set m_product_id = prod.m_product_id " +
                    " from m_product prod " +
                    " where Z_Sisteco_TK_LDev.st_codigoartsubf = prod.value " +
                    " and Z_Sisteco_TK_LDev.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_LDev.st_codigoartsubf is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            action = " update Z_Sisteco_TK_LDev set m_product_id = prod.m_product_id " +
                    " from Z_productoupc pupc " +
                    " where Z_Sisteco_TK_LDev.st_codigoarticulooriginal = pupc.upc " +
                    " and Z_Sisteco_TK_LDev.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_LDev.st_codigoartsubf is null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo producto en tabla de cancelaciones de items
            action = " update Z_Sisteco_TK_LCancela set m_product_id = prod.m_product_id " +
                    " from m_product prod " +
                    " where Z_Sisteco_TK_LCancela.st_codigoartsubf = prod.value " +
                    " and Z_Sisteco_TK_LCancela.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_LCancela.st_codigoartsubf is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo medio de pago para lineas de retiro
            action = " update Z_Sisteco_TK_LRetiro set z_sistecomediopago_id = mp.z_sistecomediopago_id " +
                    " from z_sistecomediopago mp " +
                    " where Z_Sisteco_TK_LRetiro.st_codigomediopago = mp.value " +
                    " and Z_Sisteco_TK_LRetiro.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_LRetiro.st_codigomediopago is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo medio de pago para lineas de fondeo
            action = " update Z_Sisteco_TK_LFondeo set z_sistecomediopago_id = mp.z_sistecomediopago_id " +
                    " from z_sistecomediopago mp " +
                    " where Z_Sisteco_TK_LFondeo.st_codigomediopago = mp.value " +
                    " and Z_Sisteco_TK_LFondeo.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_LFondeo.st_codigomediopago is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo medio de pago para lineas de venta con tarjeta
            action = " update Z_Sisteco_TK_VtaTarjeta set z_sistecomediopago_id = mp.z_sistecomediopago_id " +
                    " from z_sistecomediopago mp " +
                    " where Z_Sisteco_TK_VtaTarjeta.st_codigomediopago = mp.value " +
                    " and Z_Sisteco_TK_VtaTarjeta.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_VtaTarjeta.st_codigomediopago is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo medio de pago para lineas de venta con luncheon
            action = " update Z_Sisteco_TK_VtaLuncheon set z_sistecomediopago_id = mp.z_sistecomediopago_id " +
                    " from z_sistecomediopago mp " +
                    " where Z_Sisteco_TK_VtaLuncheon.st_codigomediopago = mp.value " +
                    " and Z_Sisteco_TK_VtaLuncheon.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_VtaLuncheon.st_codigomediopago is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo medio de pago para lineas de venta con ticket alimentacion
            action = " update Z_Sisteco_TK_VtaAlim set z_sistecomediopago_id = mp.z_sistecomediopago_id " +
                    " from z_sistecomediopago mp " +
                    " where Z_Sisteco_TK_VtaAlim.st_codigomediopago = mp.value " +
                    " and Z_Sisteco_TK_VtaAlim.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_VtaAlim.st_codigomediopago is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo medio de pago para lineas de venta con efectivo
            action = " update Z_Sisteco_TK_VtaEfectivo set z_sistecomediopago_id = mp.z_sistecomediopago_id " +
                    " from z_sistecomediopago mp " +
                    " where Z_Sisteco_TK_VtaEfectivo.st_codigomediopago = mp.value " +
                    " and Z_Sisteco_TK_VtaEfectivo.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_VtaEfectivo.st_codigomediopago is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo medio de pago para lineas de venta cuenta corriente
            action = " update Z_Sisteco_TK_VtaCtaCte set z_sistecomediopago_id = mp.z_sistecomediopago_id " +
                    " from z_sistecomediopago mp " +
                    " where Z_Sisteco_TK_VtaCtaCte.st_codigomediopago = mp.value " +
                    " and Z_Sisteco_TK_VtaCtaCte.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_VtaCtaCte.st_codigomediopago is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo codigo de servicio para lineas de pago de servicio
            action = " update Z_Sisteco_TK_PagoServicio set z_sistecotiposerviciopazos_id = ts.z_sistecotiposerviciopazos_id " +
                    " from z_sistecotiposerviciopazos ts " +
                    " where Z_Sisteco_TK_PagoServicio.st_codigoservicio = ts.value " +
                    " and Z_Sisteco_TK_PagoServicio.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_PagoServicio.st_codigoservicio is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo medio de pago para lineas de pago con cheque
            action = " update Z_Sisteco_TK_PagoCheque set z_sistecomediopago_id = mp.z_sistecomediopago_id " +
                    " from z_sistecomediopago mp " +
                    " where Z_Sisteco_TK_PagoCheque.st_codigomediopago = mp.value " +
                    " and Z_Sisteco_TK_PagoCheque.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_PagoCheque.st_codigomediopago is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            action = " update Z_Sisteco_TK_PagoCheque set c_bpartner_id = bp.c_bpartner_id " +
                    " from c_bpartner bp " +
                    " where Z_Sisteco_TK_PagoCheque.st_idcliente = bp.value " +
                    " and Z_Sisteco_TK_PagoCheque.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_PagoCheque.st_idcliente is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo medio de pago para lineas de pago tacre
            action = " update Z_Sisteco_TK_PagoTacre set z_sistecomediopago_id = mp.z_sistecomediopago_id " +
                    " from z_sistecomediopago mp " +
                    " where Z_Sisteco_TK_PagoTacre.st_codigomediopago = mp.value " +
                    " and Z_Sisteco_TK_PagoTacre.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_PagoTacre.st_codigomediopago is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo medio de pago para lineas de redondeo
            action = " update Z_Sisteco_TK_LineaRedondeo set z_sistecomediopago_id = mp.z_sistecomediopago_id " +
                    " from z_sistecomediopago mp " +
                    " where Z_Sisteco_TK_LineaRedondeo.st_codigomediopago = mp.value " +
                    " and Z_Sisteco_TK_LineaRedondeo.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_LineaRedondeo.st_codigomediopago is not null ";
            DB.executeUpdateEx(action, get_TrxName());

            // Actualizo codigo de servicio para lineas de devolucion de pago de servicio
            action = " update Z_Sisteco_TK_DevPagoServ set z_sistecotiposerviciopazos_id = ts.z_sistecotiposerviciopazos_id " +
                    " from z_sistecotiposerviciopazos ts " +
                    " where Z_Sisteco_TK_DevPagoServ.st_codigoservicio = ts.value " +
                    " and Z_Sisteco_TK_DevPagoServ.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and Z_Sisteco_TK_DevPagoServ.st_codigoservicio is not null ";
            DB.executeUpdateEx(action, get_TrxName());

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

    }

    private void setTotals() {

        try{

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

    }

}
