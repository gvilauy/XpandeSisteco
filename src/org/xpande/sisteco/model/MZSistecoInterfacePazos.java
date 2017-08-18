package org.xpande.sisteco.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.impexp.ImpFormat;
import org.compiere.impexp.MImpFormat;
import org.compiere.model.MEXPFormat;
import org.compiere.model.MSequence;
import org.compiere.model.MTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

    // Contadores
    private int contadorCabezales = 0;
    private int contadorLineas = 0;

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

            // Log de productos que vinieron en la interface pero no estan definidos en Adempiere
            this.logNotMatchedProducts();

            // Sumarizo información cargada
            this.setTotals();

            // Obtengo y guardo desgloce de ventas por Impuesto
            this.setDesgloceImpuestos();

            // Tiempo final de proceso
            this.setEndDate(new Timestamp(System.currentTimeMillis()));

            this.setProcessed(true);
            this.saveEx();

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
    }


    /***
     * Obtiene y guarda detalle de ventas desglozadas por Impuesto.
     * Xpande. Created by Gabriel Vila on 8/14/17.
     */
    private void setDesgloceImpuestos() {

        String sql1 = "", sql2 = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql1 = " select datetrx, c_taxcategory_id, nomcateg, sum(impuestos) as impuestos, sum(neto) as base" +
                    " from ( " +
                    " select hdr.datetrx::date as datetrx, prod.c_taxcategory_id, categ.name, coalesce(rubtax.c_taxcategory_to_id, prod.c_taxcategory_id) as idcateg, " +
                    " coalesce(categrubro.name, categ.name) as nomcateg, sum(a.st_ivadescuentototal) as impuestos, sum(a.st_preciodescuentototal) as neto " +
                    " from z_sisteco_tk_lvta a " +
                    " inner join z_sisteco_tk_cvta hdr on a.z_sisteco_tk_cvta_id = hdr.z_sisteco_tk_cvta_id " +
                    " left outer join m_product prod on a.m_product_id = prod.m_product_id " +
                    " left outer join c_taxcategory categ on prod.c_taxcategory_id = categ.c_taxcategory_id" +
                    " left outer join z_productorubrotax rubtax on (prod.z_productorubro_id = rubtax.z_productorubro_id and prod.c_taxcategory_id = rubtax.c_taxcategory_id) " +
                    " left outer join c_taxcategory categrubro on categrubro.c_taxcategory_id = rubtax.c_taxcategory_to_id " +
                    " where hdr.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and hdr.st_estadoticket ='F'" +
                    " and a.st_lineacancelada = 0" +
                    " group by hdr.datetrx::date, prod.c_taxcategory_id, categ.name, idcateg, nomcateg ";

                    sql2 = " select hdr.datetrx::date as datetrx, prod.c_taxcategory_id, categ.name, coalesce(rubtax.c_taxcategory_to_id, prod.c_taxcategory_id) as idcateg," +
                    " coalesce(categrubro.name, categ.name) as nomcateg, sum(a.st_iva) as impuestos, sum(a.st_precio) as neto" +
                    " from z_sisteco_tk_ldev a " +
                    " inner join z_sisteco_tk_cvta hdr on a.z_sisteco_tk_cvta_id = hdr.z_sisteco_tk_cvta_id" +
                    " left outer join m_product prod on a.m_product_id = prod.m_product_id " +
                    " left outer join c_taxcategory categ on prod.c_taxcategory_id = categ.c_taxcategory_id " +
                    " left outer join z_productorubrotax rubtax on (prod.z_productorubro_id = rubtax.z_productorubro_id and prod.c_taxcategory_id = rubtax.c_taxcategory_id) " +
                    " left outer join c_taxcategory categrubro on categrubro.c_taxcategory_id = rubtax.c_taxcategory_to_id " +
                    " where hdr.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and hdr.st_estadoticket ='F' " +
                    " and a.st_lineacancelada = 0 " +
                    " group by hdr.datetrx::date, prod.c_taxcategory_id, categ.name, idcateg, nomcateg " +
                    " order by datetrx, c_taxcategory_id ) as info " +
                    " group by datetrx, c_taxcategory_id, nomcateg " +
                    " order by datetrx, nomcateg ";

            pstmt = DB.prepareStatement(sql1 + " union " + sql2, get_TrxName());

            System.out.println(sql1 + " union " + sql2);

        	rs = pstmt.executeQuery();

        	while(rs.next()){
                MZSistecoPazosTax pazosTax = new MZSistecoPazosTax(getCtx(), 0, get_TrxName());
                pazosTax.setZ_SistecoInterfacePazos_ID(this.get_ID());
                pazosTax.setC_TaxCategory_ID(rs.getInt("c_taxcategory_id"));
                pazosTax.setDateTrx(rs.getTimestamp("datetrx"));
                pazosTax.setName(rs.getString("nomcateg"));
                pazosTax.setTaxAmt(rs.getBigDecimal("impuestos"));
                pazosTax.setTaxBaseAmt(rs.getBigDecimal("base"));
                pazosTax.saveEx();
        	}
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
        	rs = null; pstmt = null;
        }
   }

   private void setVentasRUT(){

        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = "SELECT a.ad_client_id, a.ad_org_id, b.st_documentoreceptor, upper(b.st_nombrereceptor) as st_nombrereceptor," +
                    " sum(c.st_totaltcktsinpagoserv - c.st_ivatotaltcktsinpagoserv) AS AmtSubtotal," +
                    " sum(c.st_ivatotaltcktsinpagoserv) AS TaxAmt, sum(c.st_totaltcktsinpagoserv) AS TotalAmt," +
                    " sum(a.st_totalapagar) AS PayAmt, date_trunc('day'::text, b.datetrx) AS datetrx " +
                    " FROM z_sisteco_tk_cvta a " +
                    " JOIN z_sisteco_tk_cfecab b ON a.z_sisteco_tk_cvta_id = b.z_sisteco_tk_cvta_id " +
                    " JOIN z_sisteco_tk_totalticket c ON a.z_sisteco_tk_cvta_id = c.z_sisteco_tk_cvta_id " +
                    " WHERE a.st_estadoticket= 'F' AND a.st_tipolinea='1' AND b.st_tipodocumentoreceptor::text = '2' " +
                    " and a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " GROUP BY a.ad_client_id, a.ad_org_id, b.st_documentoreceptor, b.st_nombrereceptor, (date_trunc('day'::text, b.datetrx))";

        	pstmt = DB.prepareStatement(sql, get_TrxName());
        	rs = pstmt.executeQuery();

        	while(rs.next()){
                // VER VISTA ZV_Sisteco_VtasRUT
        	}
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
        	rs = null; pstmt = null;
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
        String fechaCabezalTicket = null;

        try {

            // Obtengo formatos de importación desde configuración del proceso de Interface
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
                        fechaCabezalTicket = new SimpleDateFormat("yyyyMMdd").format(cabezalTicket.getDateTrx());
                    }
                    this.contadorCabezales++;
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
                                " datetrx = (select to_timestamp('" + fechaCabezalTicket + " ' || st_timestamplinea, 'YYYYMMDDHH24MISS')) " +
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
                        lineaError.setZ_SistecoInterfacePazos_ID(this.get_ID());
                        lineaError.saveEx();
                    }
                    this.contadorLineas++;
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

            action = " update Z_Sisteco_TK_LVta set m_product_id = pupc.m_product_id " +
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

            action = " update Z_Sisteco_TK_LDev set m_product_id = pupc.m_product_id " +
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

    /***
     * Se guarda el detalle de productos que vienen en la interface pero no tienen su contrapartida en Adempiere.
     * Xpande. Created by Gabriel Vila on 6/5/17.
     */
    private void logNotMatchedProducts() {

        String insert = "", sql = "";

        try{

            MSequence sequence = MSequence.get(getCtx(), I_Z_Sisteco_TK_ProductoError.Table_Name, null);
            insert = " insert into z_sisteco_tk_productoerror (z_sisteco_tk_productoerror_id, ad_client_id, ad_org_id, " +
                    "created, createdby, updated, updatedby, isactive, z_sistecointerfacepazos_id, z_sisteco_tk_cvta_id, " +
                    "tablename, columnname, value) ";


            // Log de productos no encontrados en lineas de venta
            sql = " select nextid(" + sequence.get_ID() + ",'N'), " + this.getAD_Client_ID() + ", " + this.getAD_Org_ID() + ", " +
                    "now(), " + Env.getAD_User_ID(getCtx()) + ", now(), " + Env.getAD_User_ID(getCtx()) + ",'Y', " +
                    this.get_ID() + ", z_sisteco_tk_cvta_id, 'z_sisteco_tk_lvta', 'st_codigoarticulo', st_codigoarticulo " +
                    " from z_sisteco_tk_lvta " +
                    " where z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and m_product_id is null ";
            DB.executeUpdateEx(insert + sql, get_TrxName());

            // Log de productos no encontrados en lineas de devoluciones de items
            sql = " select nextid(" + sequence.get_ID() + ",'N'), " + this.getAD_Client_ID() + ", " + this.getAD_Org_ID() + ", " +
                    "now(), " + Env.getAD_User_ID(getCtx()) + ", now(), " + Env.getAD_User_ID(getCtx()) + ",'Y', " +
                    this.get_ID() + ", z_sisteco_tk_cvta_id, 'z_sisteco_tk_ldev', 'st_codigoartsubf', st_codigoartsubf " +
                    " from z_sisteco_tk_ldev " +
                    " where z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and m_product_id is null ";
            DB.executeUpdateEx(insert + sql, get_TrxName());

            // Log de productos no encontrados en lineas de cancelaciones de items
            sql = " select nextid(" + sequence.get_ID() + ",'N'), " + this.getAD_Client_ID() + ", " + this.getAD_Org_ID() + ", " +
                    "now(), " + Env.getAD_User_ID(getCtx()) + ", now(), " + Env.getAD_User_ID(getCtx()) + ",'Y', " +
                    this.get_ID() + ", z_sisteco_tk_cvta_id, 'z_sisteco_tk_lcancela', 'st_codigoartsubf', st_codigoartsubf " +
                    " from z_sisteco_tk_lcancela " +
                    " where z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and m_product_id is null ";
            DB.executeUpdateEx(insert + sql, get_TrxName());

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
    }


    /***
     * Setea totales resultado de la interface de un archivo Pazos de Sisteco.
     * Xpande. Created by Gabriel Vila on 6/6/17.
     */
    private void setTotals() {

        try{

            this.setST_ContadorCabezales(this.contadorCabezales);
            this.setST_ContadorLineas(this.contadorLineas);
            this.setST_ContadorTotal(this.contadorCabezales + this.contadorLineas);
            this.saveEx();
            
            MZSistecoPazosTotal stTotales = new MZSistecoPazosTotal(getCtx(), 0, get_TrxName());
            stTotales.setST_TotalVtaEfectivo(this.getTotalVtaEfectivo());
            stTotales.setST_TotalVtaEfectivoUSD(this.getTotalVtaEfectivoUSD());
            stTotales.setST_TotalVtaCheque(this.getTotalVtaCheque());
            stTotales.setST_TotalVtaChequeUSD(this.getTotalVtaChequeUSD());
            stTotales.setST_TotalVtaTarjeta(this.getTotalVtaTarjeta());
            stTotales.setST_TotalVtaTarjetaUSD(this.getTotalVtaTarjetaUSD());
            stTotales.setST_TotalVtaTarjetaManual(this.getTotalVtaTarjetaManual());
            stTotales.setST_TotalVtaTarjetaCuota(this.getTotalVtaTarjetaCuota());
            stTotales.setST_TotalDevEnvases(this.getTotalDevEnvases());
            stTotales.setST_TotalEdenred(this.getTotalEdenred());
            stTotales.setST_TotalFacturas(this.getTotalFacturas());
            stTotales.setST_TotalFondeo(this.getTotalFondeo());
            stTotales.setST_TotalFondeoUSD(this.getTotalFondeoUSD());
            stTotales.setST_TotalRetiros(this.getTotalRetiros());
            stTotales.setST_TotalServicios(this.getTotalServicios());
            stTotales.setST_TotalVtaClientes(this.getTotalVtaClientes());
            stTotales.setST_TotalVtaCredito(this.getTotalVtaCredito());
            stTotales.setST_TotalVtaCreditoUSD(this.getTotalVtaCreditoUSD());
            stTotales.setST_TotalVtaLuncheon(this.getTotalVtaLuncheon());
            stTotales.setST_TotalVtaSodexo(this.getTotalVtaSodexo());

            stTotales.setZ_SistecoInterfacePazos_ID(this.get_ID());
            stTotales.saveEx();

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

    }

    /***
     * Obtiene y retorna total de ventas con Sodexo.
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaSodexo() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0) - coalesce(a.st_cambio,0)),0) as monto " +
                    " from z_sisteco_tk_vtaefectivo a " +
                    " inner join z_sisteco_tk_cvta cab on a.z_sisteco_tk_cvta_id = cab.z_sisteco_tk_cvta_id " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'" +
                    " and a.st_codigomediopago='10'" +
                    " and cab.st_estadoticket ='F'" +
                    " and cab.st_tipolinea in ('1','3')";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total de ventas con Luncheon
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaLuncheon() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0)),0) as monto " +
                    " from z_sisteco_tk_vtaluncheon a " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total de ventas credito en dolares.
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaCreditoUSD() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0)),0) as monto  " +
                    " from z_sisteco_tk_vtaefectivo a " +
                    " inner join z_sisteco_tk_cvta cab on a.z_sisteco_tk_cvta_id = cab.z_sisteco_tk_cvta_id " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='2'" +
                    " and a.st_codigomediopago='9'" +
                    " and cab.st_estadoticket ='F'" +
                    " and cab.st_tipolinea in ('1','3')";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total de ventas credito en pesos.
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaCredito() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0) - coalesce(a.st_cambio,0)),0) as monto  " +
                    " from z_sisteco_tk_vtaefectivo a " +
                    " inner join z_sisteco_tk_cvta cab on a.z_sisteco_tk_cvta_id = cab.z_sisteco_tk_cvta_id " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'" +
                    " and a.st_codigomediopago='9'" +
                    " and cab.st_estadoticket ='F'" +
                    " and cab.st_tipolinea in ('1','3')";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total de ventas en cuenta corriente
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaClientes() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0) - coalesce(a.st_cambio,0)),0) as monto " +
                    " from z_sisteco_tk_vtactacte a " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total de servicios (pagos menos devoluciones)
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalServicios() {

        BigDecimal value = Env.ZERO;

        try{

            BigDecimal pagoServicios = this.getTotalPagoServicios();
            BigDecimal devolucionServicios = this.getTotalDevolucionServicios();

            value = pagoServicios.subtract(devolucionServicios);

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return value;

    }

    /***
     * Obtiene y retorna total de pagos de servicios
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalPagoServicios() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_monto,0)),0) as monto " +
                    " from z_sisteco_tk_pagoservicio a " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total de devoluciones de pagos de servicios
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalDevolucionServicios() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_monto,0)),0) as monto " +
                    " from z_sisteco_tk_devpagoserv a " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total de retiros en efectivo en moneda pesos
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalRetiros() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_montoretiro,0)),0) as monto " +
                    " from z_sisteco_tk_lretiro a " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'" +
                    " and a.st_codigomediopago ='1'";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total de fondeos en dolares
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalFondeoUSD() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_montofondeo,0)),0) as monto " +
                    " from z_sisteco_tk_lfondeo a " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='2'";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total de fondeos en pesos
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalFondeo() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_montofondeo,0)),0) as monto " +
                    " from z_sisteco_tk_lfondeo a " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }


    /***
     * Obtiene y retorna total de ventas en facturas
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalFacturas() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalfactura,0)),0) as monto  " +
                    " from z_sisteco_tk_lfactura a " +
                    " inner join z_sisteco_tk_cvta cab on a.z_sisteco_tk_cvta_id = cab.z_sisteco_tk_cvta_id " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and cab.st_tipolinea ='11'";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total con Edenred
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalEdenred() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_importepago,0)),0) as monto " +
                    " from z_sisteco_tk_vouchertacre a " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='00'" +
                    " and a.st_tipovaucher ='1'";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total de devoluciones de envases
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalDevEnvases() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0) - coalesce(a.st_cambio,0)),0) as monto  " +
                    " from z_sisteco_tk_vtaefectivo a " +
                    " inner join z_sisteco_tk_cvta cab on a.z_sisteco_tk_cvta_id = cab.z_sisteco_tk_cvta_id " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'" +
                    " and a.st_codigomediopago='13'" +
                    " and cab.st_estadoticket ='F'" +
                    " and cab.st_tipolinea in ('1','3')";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total de ventas tarjeta credito pesos que no sea primer cuota
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaTarjetaCuota() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0)),0) as monto " +
                    " from z_sisteco_tk_vtatarjeta a " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'" +
                    " and a.st_cuotastarjetacredito !='1'";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }


    /***
     * Obtiene y retorna total de ventas tarjeta manual
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaTarjetaManual() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0) - coalesce(a.st_cambio,0)),0) as monto  " +
                    " from z_sisteco_tk_vtaefectivo a " +
                    " inner join z_sisteco_tk_cvta cab on a.z_sisteco_tk_cvta_id = cab.z_sisteco_tk_cvta_id " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'" +
                    " and a.st_codigomediopago='14'" +
                    " and cab.st_estadoticket ='F'" +
                    " and cab.st_tipolinea in ('1','3')";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }


    /***
     * Obtiene y retorna total de ventas tarjeta primer cuota en dolares
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaTarjetaUSD() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0)),0) as monto " +
                    " from z_sisteco_tk_vtatarjeta a " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='2'" +
                    " and a.st_cuotastarjetacredito ='1'";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }


    /***
     * Obtiene y retorna total de ventas tarjeta primer cuota en pesos
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaTarjeta() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0)),0) as monto " +
                    " from z_sisteco_tk_vtatarjeta a " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'" +
                    " and a.st_cuotastarjetacredito ='1'";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }


    /***
     * Obtiene y retorna total de ventas con cheques en dolares
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaChequeUSD() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0)),0) as monto  " +
                    " from z_sisteco_tk_vtaefectivo a " +
                    " inner join z_sisteco_tk_cvta cab on a.z_sisteco_tk_cvta_id = cab.z_sisteco_tk_cvta_id " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='2'" +
                    " and a.st_codigomediopago='3'" +
                    " and cab.st_estadoticket ='F'" +
                    " and cab.st_tipolinea in ('1','3')";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }

    /***
     * Obtiene y retorna total de ventas con cheques en pesos
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaCheque() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0) - coalesce(a.st_cambio,0)),0) as monto  " +
                    " from z_sisteco_tk_vtaefectivo a " +
                    " inner join z_sisteco_tk_cvta cab on a.z_sisteco_tk_cvta_id = cab.z_sisteco_tk_cvta_id " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'" +
                    " and a.st_codigomediopago='3'" +
                    " and cab.st_estadoticket ='F'" +
                    " and cab.st_tipolinea in ('1','3')";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }


    /***
     * Obtiene y retorna total de ventas con efectivo en dolares
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaEfectivoUSD() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0)),0) as monto  " +
                    " from z_sisteco_tk_vtaefectivo a " +
                    " inner join z_sisteco_tk_cvta cab on a.z_sisteco_tk_cvta_id = cab.z_sisteco_tk_cvta_id " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='2'" +
                    " and a.st_codigomediopago='1'" +
                    " and cab.st_estadoticket ='F'" +
                    " and cab.st_tipolinea in ('1','3')";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
        	rs = null; pstmt = null;
        }

        return value;

    }


    /***
     * Obtiene y retorna total de ventas con efectivo en dolares
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaEfectivo() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_totalentregado,0) - coalesce(a.st_cambio,0)),0) as monto  " +
                    " from z_sisteco_tk_vtaefectivo a " +
                    " inner join z_sisteco_tk_cvta cab on a.z_sisteco_tk_cvta_id = cab.z_sisteco_tk_cvta_id " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='1'" +
                    " and a.st_codigomediopago='1'" +
                    " and cab.st_estadoticket ='F'" +
                    " and cab.st_tipolinea in ('1','3')";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;

                BigDecimal valueCambio = this.getTotalVtaEfectivoCambio();
                value = value.subtract(valueCambio);
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;

    }


    /***
     * Obtiene y retorna total de cambio con efectivo
     * Xpande. Created by Gabriel Vila on 6/6/17.
     * @return Monto obtenido
     */
    private BigDecimal getTotalVtaEfectivoCambio() {

        BigDecimal value = Env.ZERO;
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select coalesce(sum(coalesce(a.st_cambio,0)),0) as monto  " +
                    " from z_sisteco_tk_vtaefectivo a " +
                    " inner join z_sisteco_tk_cvta cab on a.z_sisteco_tk_cvta_id = cab.z_sisteco_tk_cvta_id " +
                    " where  a.z_sistecointerfacepazos_id =" + this.get_ID() +
                    " and a.st_codigomoneda ='2'" +
                    " and a.st_codigomediopago='1'" +
                    " and cab.st_estadoticket ='F'" +
                    " and cab.st_tipolinea in ('1','3')";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getBigDecimal("monto");
                if (value == null) value = Env.ZERO;
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return value;
    }


}
