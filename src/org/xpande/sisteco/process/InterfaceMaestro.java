package org.xpande.sisteco.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.eevolution.model.X_C_TaxGroup;
import org.xpande.core.utils.FileUtils;
import org.xpande.core.utils.PriceListUtils;
import org.xpande.sisteco.model.MZSistecoConfig;
import org.xpande.sisteco.model.MZSistecoInterfaceOut;
import org.xpande.sisteco.model.MZSistecoRegion;
import org.xpande.sisteco.utils.SistecoUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 8/14/18.
 */
public class InterfaceMaestro extends SvrProcess {

    private  MZSistecoConfig sistecoConfig = null;
    private int adOrgID = -1;

    // Archivos
    private File fileBatch = null;
    private File fileCountBatch = null;
    private File fileBatchError = null;

    // Contadores de lineas
    private int contadorLinBatch = 0;

    private String fechaHoy;

    private HashMap<Integer, Integer> hashProds = new HashMap<Integer, Integer>();


    @Override
    protected void prepare() {

        ProcessInfoParameter[] para = getParameter();

        for (int i = 0; i < para.length; i++){

            String name = para[i].getParameterName();

            if (name != null){
                if (para[i].getParameter() != null){
                    // Organización a procesar
                    if (name.trim().equalsIgnoreCase("AD_Org_ID")){
                        this.adOrgID = ((BigDecimal)para[i].getParameter()).intValueExact();
                    }
                }
            }
        }

    }

    @Override
    protected String doIt() throws Exception {

        String message = null;

        BufferedWriter bufferedWriterBatch = null;
        BufferedWriter bufferedWriterCount = null;

        try{
            // Obtengo configurador de sisteco
            this.sistecoConfig = MZSistecoConfig.getDefault(getCtx(), get_TrxName());

            String separadorCampos = this.sistecoConfig.getSeparadorArchivoOut();

            // Creación de archivos de interface
            this.createFiles();

            // Abro Archivos
            FileWriter fileWriterBatch = new FileWriter(this.fileBatch, true);
            FileWriter fileWriterCount = new FileWriter(this.fileCountBatch, true);

            bufferedWriterBatch = new BufferedWriter(fileWriterBatch);
            bufferedWriterCount = new BufferedWriter(fileWriterCount);

            // Proceso y genero lineas en archivos

            // Productos
            message = this.executeProducts(bufferedWriterBatch, bufferedWriterCount, separadorCampos);
            if (message != null) return message;

            // Tandems
            message = this.executeTandems(bufferedWriterBatch, bufferedWriterCount, separadorCampos);
            if (message != null) return message;

            // Codigos de Barra
            message = this.executeUPCs(bufferedWriterBatch, bufferedWriterCount, separadorCampos);
            if (message != null) return message;

            // Clientes
            message = this.executePartners(bufferedWriterBatch, bufferedWriterCount, separadorCampos);
            if (message != null) return message;

            // Cierro archivos
            if (bufferedWriterBatch != null){
                bufferedWriterBatch.close();
            }

            if (bufferedWriterCount != null){
                bufferedWriterCount.close();
            }

            String pathArchivosDestino = sistecoConfig.getRutaInterfaceOut() + File.separator;

            // Si tengo lineas en archivos batch
            if (this.contadorLinBatch > 0){

                this.setBatchLines(this.contadorLinBatch);

                // Copio archivo batch a path destino
                File fileBatchDest = new File( pathArchivosDestino + sistecoConfig.getArchivoBatchMast());
                FileUtils.copyFile(this.fileBatch, fileBatchDest);
            }

            /*
            // Si tengo lineas en archivos online
            if (this.contadorLinOnline > 0){
                this.setOnlineLines(this.contadorLinOnline);
            }
            */

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
            if (bufferedWriterCount != null){
                try {
                    bufferedWriterCount.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "OK";
    }


    /***
     * Ejecuta interface de Productos para el archivo Maestro de Sisteco.
     * Xpande. Created by Gabriel Vila on 9/14/18.
     * @param bufferedWriterBatch
     * @param bufferedWriterCount
     * @param separadorCampos
     * @return
     */
    private String executeProducts(BufferedWriter bufferedWriterBatch, BufferedWriter bufferedWriterCount, String separadorCampos) {

        String message = null;

        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{

            Timestamp fechaHoy = TimeUtil.trunc(new Timestamp(System.currentTimeMillis()), TimeUtil.TRUNC_DAY);

            this.hashProds = new HashMap<Integer, Integer>();

            sql = " select p.m_product_id, p.z_productosubfamilia_id, sf.codigopos " +
                    " from m_product p " +
                    " left outer join z_productosubfamilia sf on p.z_productosubfamilia_id = sf.z_productosubfamilia_id " +
                    " where p.isactive ='Y' and p.issold ='Y' " +
                    " order by p.name ";

        	pstmt = DB.prepareStatement(sql, null);
        	rs = pstmt.executeQuery();

        	while(rs.next()){

                MProduct product = new MProduct(getCtx(), rs.getInt("m_product_id"), null);
                MPriceList priceList = PriceListUtils.getPriceListByOrg(getCtx(), this.getAD_Client_ID(), adOrgID, 142, true, null, null);
                if ((priceList == null) || (priceList.get_ID() <= 0)){
                    priceList = PriceListUtils.getPriceListByOrg(getCtx(), this.getAD_Client_ID(), adOrgID, 100, true, null, null);
                }

                if ((priceList == null) || (priceList.get_ID() <= 0)){
                    //message = "No se obtuvo precio de venta para producto : " + product.getValue() + " - " + product.getName();
                    //return message;
                    continue;
                }

                String lineaArchivo = "I" + separadorCampos;
                lineaArchivo += "ARTICULOS" + separadorCampos;
                lineaArchivo += product.getValue() + separadorCampos;
                lineaArchivo += product.getDescription().replace(separadorCampos,"_") + separadorCampos;
                lineaArchivo += "0" + separadorCampos; // codigo entorno no utilizado

                String codigoSubfamilia = "9999";
                if (rs.getString("codigopos") != null){
                    codigoSubfamilia = rs.getString("codigopos").trim();
                }

                lineaArchivo += codigoSubfamilia + separadorCampos; // codigo subfamilia no utilizado

                // Moneda
                String monedaSisteco = "1";  // Pesos por defecto
                if (priceList.getC_Currency_ID() == 100){
                    monedaSisteco = "2";
                }

                lineaArchivo += monedaSisteco + separadorCampos;

                // Codigo IVA
                lineaArchivo += ((MTaxCategory) product.getC_TaxCategory()).getCommodityCode() + separadorCampos;

                // Precio de venta
                MPriceListVersion priceListVersion = priceList.getPriceListVersion(null);
                MProductPrice productPrice = MProductPrice.get(getCtx(), priceListVersion.get_ID(), product.get_ID(), null);

                // Si no tengo precio de venta, aviso y salgo
                if (productPrice == null){
                    //message = "No se obtuvo precio de venta para producto : " + product.getValue() + " - " + product.getName();
                    //return message;
                    continue;
                }

                BigDecimal priceSO = productPrice.getPriceStd();

                // Verifico si en este momento no hay una oferta no comunicada al pos para este producto.
                // Como estoy armando el archivo maestro, si esta oferta esta vigente en fechas, debo mandar este precio de venta.
                MZSistecoInterfaceOut interfaceOut = MZSistecoInterfaceOut.getRecord(getCtx(), product.get_Table_ID(), product.get_ID(), this.adOrgID, null);
                if ((interfaceOut != null) && (interfaceOut.get_ID() > 0)){
                    if (interfaceOut.isWithOfferSO()){
                        if (interfaceOut.getStartDate().before(fechaHoy)){
                            if (interfaceOut.getEndDate().after(fechaHoy)){
                                priceSO = interfaceOut.getPriceSO();
                            }
                        }
                    }
                }

                String precioSisteco = "0", parteDecimalPrecio ="00";
                BigDecimal decimalPrecioSO = priceSO.subtract(priceSO.setScale(0, RoundingMode.FLOOR)).movePointRight(priceSO.scale());
                if (decimalPrecioSO != null){
                    if (decimalPrecioSO.compareTo(Env.ZERO) != 0){
                        if (decimalPrecioSO.toString().length() >= 3){
                            parteDecimalPrecio = decimalPrecioSO.toString().substring(0, 2);
                        }
                        else if (decimalPrecioSO.toString().length() == 2){
                            parteDecimalPrecio = decimalPrecioSO.toString();
                        }
                        else if (decimalPrecioSO.toString().length() == 1){
                            parteDecimalPrecio = decimalPrecioSO.toString() + "0";
                        }
                    }
                }
                precioSisteco = String.valueOf(priceSO.intValue()) + parteDecimalPrecio;
                lineaArchivo += precioSisteco + separadorCampos;

                // Valor Hexadecimal del producto
                String valorHexadecimal = SistecoUtils.getHexadecimalAtributos(getCtx(), product, null);
                lineaArchivo += valorHexadecimal + separadorCampos;

                // Unidades por pack
                lineaArchivo += String.valueOf(product.getUnitsPerPack()) + separadorCampos;

                // Unidad de medida
                String unidadSisteco = "1";
                if (product.get_ValueAsBoolean("EsProductoBalanza")){
                    unidadSisteco = "2";
                }
                lineaArchivo += unidadSisteco;

                bufferedWriterBatch.append(lineaArchivo);
                bufferedWriterCount.append(lineaArchivo);

                bufferedWriterBatch.newLine();
                bufferedWriterCount.newLine();

                this.contadorLinBatch++;
                //this.contadorLinCount++;

                this.hashProds.put(product.get_ID(), product.get_ID());

            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
        	rs = null; pstmt = null;
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

            fileBatch = new File( pathArchivos + sistecoConfig.getArchivoBatchMast());
            fileCountBatch = new File( pathArchivos + sistecoConfig.getArchivoCountBatchMast());

            fileBatchError = new File(sistecoConfig.getRutaInterfaceOutHist() + File.separator + "ArchDeErrores" + File.separator +
                    this.fechaHoy + sistecoConfig.getArchivoBatchErrorMast());

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

    }


    /***
     * Ejecuta interface de Productos Tandem para el archivo Maestro de Sisteco.
     * Xpande. Created by Gabriel Vila on 9/14/18.
     * @param bufferedWriterBatch
     * @param bufferedWriterCount
     * @param separadorCampos
     * @return
     */
    private String executeTandems(BufferedWriter bufferedWriterBatch, BufferedWriter bufferedWriterCount, String separadorCampos) {

        String message = null;

        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select a.m_product_id, a.value as cod_art, b.value as cod_tandem " +
                    " from m_product a " +
                    " inner join m_product b on a.m_product_tandem_id = b.m_product_id " +
                    " where a.isactive = 'Y' " +
                    " and b.isactive = 'Y'";

        	pstmt = DB.prepareStatement(sql, get_TrxName());
        	rs = pstmt.executeQuery();

        	while(rs.next()){

                int mProductID = rs.getInt("m_product_id");

                if (this.hashProds.containsKey(mProductID)){
                    String lineaArchivo = "I" + separadorCampos;
                    lineaArchivo += "TANDEM" + separadorCampos;
                    lineaArchivo += rs.getString("cod_art").trim() + separadorCampos;
                    lineaArchivo += rs.getString("cod_tandem").trim();

                    bufferedWriterBatch.append(lineaArchivo);
                    bufferedWriterCount.append(lineaArchivo);

                    bufferedWriterBatch.newLine();
                    bufferedWriterCount.newLine();

                    this.contadorLinBatch++;
                    //this.contadorLinCount++;
                }
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
        	rs = null; pstmt = null;
        }

        return message;
    }


    /***
     * Ejecuta interface de Códigos de Barra de productos para el archivo Maestro de Sisteco.
     * Xpande. Created by Gabriel Vila on 9/14/18.
     * @param bufferedWriterBatch
     * @param bufferedWriterCount
     * @param separadorCampos
     * @return
     */
    private String executeUPCs(BufferedWriter bufferedWriterBatch, BufferedWriter bufferedWriterCount, String separadorCampos) {

        String message = null;

        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select distinct a.m_product_id, a.value, b.upc " +
                    " from m_product a " +
                    " inner join z_productoupc b on a.m_product_id = b.m_product_id " +
                    " where a.isactive = 'Y' " +
                    " and a.issold='Y' " +
                    " and b.isactive ='Y' " +
                    " order by a.value";

            pstmt = DB.prepareStatement(sql, null);
            rs = pstmt.executeQuery();

            while(rs.next()){

                int mProductID = rs.getInt("m_product_id");
                if (this.hashProds.containsKey(mProductID)){

                    String lineaArchivo = "I" + separadorCampos;
                    lineaArchivo += "ARTICULOS_EQUIVALENTES" + separadorCampos;
                    lineaArchivo += rs.getString("upc").trim() + separadorCampos;
                    lineaArchivo += rs.getString("value").trim();

                    bufferedWriterBatch.append(lineaArchivo);
                    bufferedWriterCount.append(lineaArchivo);

                    bufferedWriterBatch.newLine();
                    bufferedWriterCount.newLine();

                    this.contadorLinBatch++;
                    //this.contadorLinCount++;
                }
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return message;
    }


    /***
     * Ejecuta interface de Clientes para el archivo Maestro de Sisteco.
     * Xpande. Created by Gabriel Vila on 9/14/18.
     * @param bufferedWriterBatch
     * @param bufferedWriterCount
     * @param separadorCampos
     * @return
     */
    private String executePartners(BufferedWriter bufferedWriterBatch, BufferedWriter bufferedWriterCount, String separadorCampos) {

        String message = null;

        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select c_bpartner_id  " +
                    " from c_bpartner " +
                    " where isactive ='Y' " +
                    " and iscustomer ='Y' ";

            pstmt = DB.prepareStatement(sql, get_TrxName());
            rs = pstmt.executeQuery();

            while(rs.next()){

                // Instancio modelo de socio y localización.
                MBPartner partner = new MBPartner(getCtx(), rs.getInt("c_bpartner_id"), get_TrxName());
                MBPartnerLocation[] partnerLocations = partner.getLocations(true);
                MBPartnerLocation partnerLocation = null;
                MLocation location = null;
                if (partnerLocations.length > 0){
                    partnerLocation = partnerLocations[0];
                    if (partnerLocation.getC_Location_ID() > 0){
                        location = (MLocation) partnerLocation.getC_Location();
                    }
                }

                String lineaArchivo = "I" + separadorCampos;

                lineaArchivo += "CLIENTES" + separadorCampos;

                // Codigo y razon social
                lineaArchivo += partner.getValue().trim() + separadorCampos;
                lineaArchivo += partner.getName().trim() + separadorCampos;

                String direccion = "", telefono = "", email = "";
                String codigoPais = "", ciudad = "", departamento = "", codigoPostal = "";
                String esquina1 = "", esquina2 = "", codigoDptoSisteco = "10";
                if (location != null){
                    if (location.getAddress1() != null){
                        direccion = location.getAddress1().trim().replace(separadorCampos, "_");
                    }
                    if (location.getCity() != null){
                        ciudad = location.getCity().trim().replace(separadorCampos, "_");
                    }
                    if (location.getRegionName() != null){
                        departamento = location.getRegionName().trim().replace(separadorCampos, "_");
                        if (departamento != null){
                            MZSistecoRegion sistecoRegion = sistecoConfig.getByDepartamento(departamento);
                            if ((sistecoRegion != null) && (sistecoRegion.get_ID() > 0)){
                                codigoDptoSisteco = sistecoRegion.getST_CodigoDepartamento().trim();
                            }
                        }
                    }
                    else{
                        return "Falta definir Departamento para el Socio de Negocio : " + partner.getValue();
                    }
                    if (location.getPostal() != null){
                        codigoPostal = location.getPostal().trim().replace(separadorCampos, "_");
                    }
                    if (location.getCountry() != null){
                        codigoPais = ((MCountry) location.getCountry()).getCountryCode().trim();
                    }
                    else{
                        return "Falta definir Pais para el Socio de Negocio : " + partner.getValue();
                    }
                }

                if (partnerLocation.getPhone() != null){
                    telefono = partnerLocation.getPhone().trim().replace(separadorCampos, "_");
                }

                if (partner.get_Value("EMail") != null){
                    email = ((String) partner.get_Value("EMail")).trim().replace(separadorCampos, "_");
                }

                String tipoDocumento = "", nroDocumento = "", cedula = "";
                nroDocumento = partner.getTaxID();
                if ((nroDocumento == null) || (nroDocumento.trim().equalsIgnoreCase(""))){
                    return "Falta definir Numero de Identificación para el Socio de Negocio : " + partner.getValue();
                }

                X_C_TaxGroup taxGroup = (X_C_TaxGroup) partner.getC_TaxGroup();
                if (taxGroup.getValue().equalsIgnoreCase("RUT")){
                    tipoDocumento = "2";
                }
                else if (taxGroup.getValue().equalsIgnoreCase("CI")){
                    tipoDocumento = "3";
                    nroDocumento = "";
                    cedula = partner.getTaxID();
                }
                else{
                    tipoDocumento = "4"; // Otros
                }

                // Valor Hexadecimal del socio de negocio
                String valorHexadecimal = SistecoUtils.getHexadecimalAtributosPartner(getCtx(), partner, get_TrxName());

                // Concateno campos y armo linea de archivo
                lineaArchivo += direccion + separadorCampos;
                lineaArchivo += esquina1 + separadorCampos;
                lineaArchivo += esquina2 + separadorCampos;
                lineaArchivo += telefono + separadorCampos;
                lineaArchivo += nroDocumento + separadorCampos;
                lineaArchivo += cedula + separadorCampos;
                lineaArchivo += tipoDocumento + separadorCampos;
                //lineaArchivo += departamento + separadorCampos;
                lineaArchivo += codigoDptoSisteco + separadorCampos;
                lineaArchivo += ciudad + separadorCampos;
                lineaArchivo += codigoPostal + separadorCampos;
                lineaArchivo += email + separadorCampos;
                lineaArchivo += codigoPais + separadorCampos;
                lineaArchivo += valorHexadecimal;


                bufferedWriterBatch.append(lineaArchivo);
                bufferedWriterCount.append(lineaArchivo);

                bufferedWriterBatch.newLine();
                bufferedWriterCount.newLine();

                this.contadorLinBatch++;
                //this.contadorLinCount++;

            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

        return message;
    }


    /***
     * Guarda cantidad de lineas batch generadas en archivo de lineas batch.
     * Xpande. Created by Gabriel Vila on 9/14/18.
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


}
