package org.xpande.sisteco.utils;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.xpande.sisteco.model.I_Z_SistecoInterfaceOut;
import org.xpande.sisteco.model.MZSistecoConfig;
import org.xpande.sisteco.model.MZSistecoInterfaceOut;
import org.xpande.sisteco.model.X_Z_SistecoInterfaceOut;

import java.io.File;
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


    public String execute(){

        String message = null;

        try{
            // Obtengo configurador de sisteco
            this.sistecoConfig = MZSistecoConfig.getDefault(ctx, trxName);

            // Creación de archivos de interface
            this.createFiles();

            // Obtengo y recorro lineas de interface aun no ejecutadas
            List<MZSistecoInterfaceOut> interfaceOuts = this.getLinesProdsNotExecuted();
            for (MZSistecoInterfaceOut interfaceOut: interfaceOuts){

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
     * Obtiene y retorna lineas de interface de salida no ejecutadas al momento.
     * Xpande. Created by Gabriel Vila on 7/24/17.
     * @return
     */
    private List<MZSistecoInterfaceOut> getLinesProdsNotExecuted(){

        String whereClause = X_Z_SistecoInterfaceOut.COLUMNNAME_IsExecuted + " ='N'";

        List<MZSistecoInterfaceOut> lines = new Query(ctx, I_Z_SistecoInterfaceOut.Table_Name, whereClause, trxName).list();

        return lines;

    }
}
