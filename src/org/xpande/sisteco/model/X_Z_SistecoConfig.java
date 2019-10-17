/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.xpande.sisteco.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for Z_SistecoConfig
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoConfig extends PO implements I_Z_SistecoConfig, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191017L;

    /** Standard Constructor */
    public X_Z_SistecoConfig (Properties ctx, int Z_SistecoConfig_ID, String trxName)
    {
      super (ctx, Z_SistecoConfig_ID, trxName);
      /** if (Z_SistecoConfig_ID == 0)
        {
			setBatchOnlyPartner (false);
// N
			setCreateBatchFile (true);
// Y
			setCreateOnlineFile (true);
// Y
			setIsEmployee (false);
// N
			setZ_SistecoConfig_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoConfig (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_Z_SistecoConfig[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set ArchivoBatch.
		@param ArchivoBatch 
		Nombre del archivo Batch de interface de salida de Sisteco
	  */
	public void setArchivoBatch (String ArchivoBatch)
	{
		set_Value (COLUMNNAME_ArchivoBatch, ArchivoBatch);
	}

	/** Get ArchivoBatch.
		@return Nombre del archivo Batch de interface de salida de Sisteco
	  */
	public String getArchivoBatch () 
	{
		return (String)get_Value(COLUMNNAME_ArchivoBatch);
	}

	/** Set ArchivoBatchError.
		@param ArchivoBatchError 
		Nombre de archivo Batch de errores de interface de salida de Sisteco
	  */
	public void setArchivoBatchError (String ArchivoBatchError)
	{
		set_Value (COLUMNNAME_ArchivoBatchError, ArchivoBatchError);
	}

	/** Get ArchivoBatchError.
		@return Nombre de archivo Batch de errores de interface de salida de Sisteco
	  */
	public String getArchivoBatchError () 
	{
		return (String)get_Value(COLUMNNAME_ArchivoBatchError);
	}

	/** Set ArchivoBatchErrorMast.
		@param ArchivoBatchErrorMast 
		Nombre de archivo Batch de errores de interface de salida de Sisteco para Maestro
	  */
	public void setArchivoBatchErrorMast (String ArchivoBatchErrorMast)
	{
		set_Value (COLUMNNAME_ArchivoBatchErrorMast, ArchivoBatchErrorMast);
	}

	/** Get ArchivoBatchErrorMast.
		@return Nombre de archivo Batch de errores de interface de salida de Sisteco para Maestro
	  */
	public String getArchivoBatchErrorMast () 
	{
		return (String)get_Value(COLUMNNAME_ArchivoBatchErrorMast);
	}

	/** Set ArchivoBatchMast.
		@param ArchivoBatchMast 
		Nombre del archivo Batch de interface de salida de Sisteco para Maestro
	  */
	public void setArchivoBatchMast (String ArchivoBatchMast)
	{
		set_Value (COLUMNNAME_ArchivoBatchMast, ArchivoBatchMast);
	}

	/** Get ArchivoBatchMast.
		@return Nombre del archivo Batch de interface de salida de Sisteco para Maestro
	  */
	public String getArchivoBatchMast () 
	{
		return (String)get_Value(COLUMNNAME_ArchivoBatchMast);
	}

	/** Set ArchivoCountBatch.
		@param ArchivoCountBatch 
		Nombre del archivo CountBatch de interface de salida de Sisteco
	  */
	public void setArchivoCountBatch (String ArchivoCountBatch)
	{
		set_Value (COLUMNNAME_ArchivoCountBatch, ArchivoCountBatch);
	}

	/** Get ArchivoCountBatch.
		@return Nombre del archivo CountBatch de interface de salida de Sisteco
	  */
	public String getArchivoCountBatch () 
	{
		return (String)get_Value(COLUMNNAME_ArchivoCountBatch);
	}

	/** Set ArchivoCountBatchMast.
		@param ArchivoCountBatchMast 
		Nombre del archivo CountBatch de interface de salida de Sisteco para Maestro
	  */
	public void setArchivoCountBatchMast (String ArchivoCountBatchMast)
	{
		set_Value (COLUMNNAME_ArchivoCountBatchMast, ArchivoCountBatchMast);
	}

	/** Get ArchivoCountBatchMast.
		@return Nombre del archivo CountBatch de interface de salida de Sisteco para Maestro
	  */
	public String getArchivoCountBatchMast () 
	{
		return (String)get_Value(COLUMNNAME_ArchivoCountBatchMast);
	}

	/** Set ArchivoCountOnline.
		@param ArchivoCountOnline 
		Nombre del archivo CountOnline de interface de salida de Sisteco
	  */
	public void setArchivoCountOnline (String ArchivoCountOnline)
	{
		set_Value (COLUMNNAME_ArchivoCountOnline, ArchivoCountOnline);
	}

	/** Get ArchivoCountOnline.
		@return Nombre del archivo CountOnline de interface de salida de Sisteco
	  */
	public String getArchivoCountOnline () 
	{
		return (String)get_Value(COLUMNNAME_ArchivoCountOnline);
	}

	/** Set ArchivoOnline.
		@param ArchivoOnline 
		Nombre del archivo Online de interface de salida de Sisteco
	  */
	public void setArchivoOnline (String ArchivoOnline)
	{
		set_Value (COLUMNNAME_ArchivoOnline, ArchivoOnline);
	}

	/** Get ArchivoOnline.
		@return Nombre del archivo Online de interface de salida de Sisteco
	  */
	public String getArchivoOnline () 
	{
		return (String)get_Value(COLUMNNAME_ArchivoOnline);
	}

	/** Set BatchOnlyPartner.
		@param BatchOnlyPartner 
		Si solo se guardan socios de negocio en archivos bath de interface con Sisteco
	  */
	public void setBatchOnlyPartner (boolean BatchOnlyPartner)
	{
		set_Value (COLUMNNAME_BatchOnlyPartner, Boolean.valueOf(BatchOnlyPartner));
	}

	/** Get BatchOnlyPartner.
		@return Si solo se guardan socios de negocio en archivos bath de interface con Sisteco
	  */
	public boolean isBatchOnlyPartner () 
	{
		Object oo = get_Value(COLUMNNAME_BatchOnlyPartner);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set CreateBatchFile.
		@param CreateBatchFile 
		Si se debe crear o no archivos batch en interface con Sisteco
	  */
	public void setCreateBatchFile (boolean CreateBatchFile)
	{
		set_Value (COLUMNNAME_CreateBatchFile, Boolean.valueOf(CreateBatchFile));
	}

	/** Get CreateBatchFile.
		@return Si se debe crear o no archivos batch en interface con Sisteco
	  */
	public boolean isCreateBatchFile () 
	{
		Object oo = get_Value(COLUMNNAME_CreateBatchFile);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set CreateOnlineFile.
		@param CreateOnlineFile 
		Si se debe crear o no archivos online en interface con Sisteco
	  */
	public void setCreateOnlineFile (boolean CreateOnlineFile)
	{
		set_Value (COLUMNNAME_CreateOnlineFile, Boolean.valueOf(CreateOnlineFile));
	}

	/** Get CreateOnlineFile.
		@return Si se debe crear o no archivos online en interface con Sisteco
	  */
	public boolean isCreateOnlineFile () 
	{
		Object oo = get_Value(COLUMNNAME_CreateOnlineFile);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set DefaultDocPosARC_ID.
		@param DefaultDocPosARC_ID 
		ID de documento para migración de notas de crédito de venta crédito desde POS
	  */
	public void setDefaultDocPosARC_ID (int DefaultDocPosARC_ID)
	{
		if (DefaultDocPosARC_ID < 1) 
			set_Value (COLUMNNAME_DefaultDocPosARC_ID, null);
		else 
			set_Value (COLUMNNAME_DefaultDocPosARC_ID, Integer.valueOf(DefaultDocPosARC_ID));
	}

	/** Get DefaultDocPosARC_ID.
		@return ID de documento para migración de notas de crédito de venta crédito desde POS
	  */
	public int getDefaultDocPosARC_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DefaultDocPosARC_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DefaultDocPosARI_ID.
		@param DefaultDocPosARI_ID 
		ID de documento para migración de facturas de venta crédito desde POS
	  */
	public void setDefaultDocPosARI_ID (int DefaultDocPosARI_ID)
	{
		if (DefaultDocPosARI_ID < 1) 
			set_Value (COLUMNNAME_DefaultDocPosARI_ID, null);
		else 
			set_Value (COLUMNNAME_DefaultDocPosARI_ID, Integer.valueOf(DefaultDocPosARI_ID));
	}

	/** Get DefaultDocPosARI_ID.
		@return ID de documento para migración de facturas de venta crédito desde POS
	  */
	public int getDefaultDocPosARI_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DefaultDocPosARI_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Employee.
		@param IsEmployee 
		Indicates if  this Business Partner is an employee
	  */
	public void setIsEmployee (boolean IsEmployee)
	{
		set_Value (COLUMNNAME_IsEmployee, Boolean.valueOf(IsEmployee));
	}

	/** Get Employee.
		@return Indicates if  this Business Partner is an employee
	  */
	public boolean isEmployee () 
	{
		Object oo = get_Value(COLUMNNAME_IsEmployee);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set PrefijoArchivoPazos.
		@param PrefijoArchivoPazos PrefijoArchivoPazos	  */
	public void setPrefijoArchivoPazos (String PrefijoArchivoPazos)
	{
		set_Value (COLUMNNAME_PrefijoArchivoPazos, PrefijoArchivoPazos);
	}

	/** Get PrefijoArchivoPazos.
		@return PrefijoArchivoPazos	  */
	public String getPrefijoArchivoPazos () 
	{
		return (String)get_Value(COLUMNNAME_PrefijoArchivoPazos);
	}

	/** Set ProdVtasCredPOS_ID.
		@param ProdVtasCredPOS_ID 
		Producto para Migración de Ventas Crédito desde POS
	  */
	public void setProdVtasCredPOS_ID (int ProdVtasCredPOS_ID)
	{
		if (ProdVtasCredPOS_ID < 1) 
			set_Value (COLUMNNAME_ProdVtasCredPOS_ID, null);
		else 
			set_Value (COLUMNNAME_ProdVtasCredPOS_ID, Integer.valueOf(ProdVtasCredPOS_ID));
	}

	/** Get ProdVtasCredPOS_ID.
		@return Producto para Migración de Ventas Crédito desde POS
	  */
	public int getProdVtasCredPOS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ProdVtasCredPOS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set RutaDestinoSistema.
		@param RutaDestinoSistema RutaDestinoSistema	  */
	public void setRutaDestinoSistema (String RutaDestinoSistema)
	{
		set_Value (COLUMNNAME_RutaDestinoSistema, RutaDestinoSistema);
	}

	/** Get RutaDestinoSistema.
		@return RutaDestinoSistema	  */
	public String getRutaDestinoSistema () 
	{
		return (String)get_Value(COLUMNNAME_RutaDestinoSistema);
	}

	/** Set RutaHistoricoPazos.
		@param RutaHistoricoPazos RutaHistoricoPazos	  */
	public void setRutaHistoricoPazos (String RutaHistoricoPazos)
	{
		set_Value (COLUMNNAME_RutaHistoricoPazos, RutaHistoricoPazos);
	}

	/** Get RutaHistoricoPazos.
		@return RutaHistoricoPazos	  */
	public String getRutaHistoricoPazos () 
	{
		return (String)get_Value(COLUMNNAME_RutaHistoricoPazos);
	}

	/** Set RutaInterfaceMastHist.
		@param RutaInterfaceMastHist 
		Ruta para archivos de histórico de maestros de interface con POS Sisteco
	  */
	public void setRutaInterfaceMastHist (String RutaInterfaceMastHist)
	{
		set_Value (COLUMNNAME_RutaInterfaceMastHist, RutaInterfaceMastHist);
	}

	/** Get RutaInterfaceMastHist.
		@return Ruta para archivos de histórico de maestros de interface con POS Sisteco
	  */
	public String getRutaInterfaceMastHist () 
	{
		return (String)get_Value(COLUMNNAME_RutaInterfaceMastHist);
	}

	/** Set RutaInterfaceOut.
		@param RutaInterfaceOut 
		Ruta donde se crean los archivos de interface de salida para Sisteco
	  */
	public void setRutaInterfaceOut (String RutaInterfaceOut)
	{
		set_Value (COLUMNNAME_RutaInterfaceOut, RutaInterfaceOut);
	}

	/** Get RutaInterfaceOut.
		@return Ruta donde se crean los archivos de interface de salida para Sisteco
	  */
	public String getRutaInterfaceOut () 
	{
		return (String)get_Value(COLUMNNAME_RutaInterfaceOut);
	}

	/** Set RutaInterfaceOutHist.
		@param RutaInterfaceOutHist 
		Ruta donde dejar archivos de historico de interface de salida a Sisteco
	  */
	public void setRutaInterfaceOutHist (String RutaInterfaceOutHist)
	{
		set_Value (COLUMNNAME_RutaInterfaceOutHist, RutaInterfaceOutHist);
	}

	/** Get RutaInterfaceOutHist.
		@return Ruta donde dejar archivos de historico de interface de salida a Sisteco
	  */
	public String getRutaInterfaceOutHist () 
	{
		return (String)get_Value(COLUMNNAME_RutaInterfaceOutHist);
	}

	/** Set RutaOrigenPazos.
		@param RutaOrigenPazos RutaOrigenPazos	  */
	public void setRutaOrigenPazos (String RutaOrigenPazos)
	{
		set_Value (COLUMNNAME_RutaOrigenPazos, RutaOrigenPazos);
	}

	/** Get RutaOrigenPazos.
		@return RutaOrigenPazos	  */
	public String getRutaOrigenPazos () 
	{
		return (String)get_Value(COLUMNNAME_RutaOrigenPazos);
	}

	/** Set SeparadorArchivoOut.
		@param SeparadorArchivoOut 
		Separador de campos del archivo de interface de salida de Sisteco
	  */
	public void setSeparadorArchivoOut (String SeparadorArchivoOut)
	{
		set_Value (COLUMNNAME_SeparadorArchivoOut, SeparadorArchivoOut);
	}

	/** Get SeparadorArchivoOut.
		@return Separador de campos del archivo de interface de salida de Sisteco
	  */
	public String getSeparadorArchivoOut () 
	{
		return (String)get_Value(COLUMNNAME_SeparadorArchivoOut);
	}

	/** Set Z_PosVendor ID.
		@param Z_PosVendor_ID Z_PosVendor ID	  */
	public void setZ_PosVendor_ID (int Z_PosVendor_ID)
	{
		if (Z_PosVendor_ID < 1) 
			set_Value (COLUMNNAME_Z_PosVendor_ID, null);
		else 
			set_Value (COLUMNNAME_Z_PosVendor_ID, Integer.valueOf(Z_PosVendor_ID));
	}

	/** Get Z_PosVendor ID.
		@return Z_PosVendor ID	  */
	public int getZ_PosVendor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_PosVendor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Z_SistecoConfig ID.
		@param Z_SistecoConfig_ID Z_SistecoConfig ID	  */
	public void setZ_SistecoConfig_ID (int Z_SistecoConfig_ID)
	{
		if (Z_SistecoConfig_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoConfig_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoConfig_ID, Integer.valueOf(Z_SistecoConfig_ID));
	}

	/** Get Z_SistecoConfig ID.
		@return Z_SistecoConfig ID	  */
	public int getZ_SistecoConfig_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_SistecoConfig_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}