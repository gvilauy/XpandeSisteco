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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for Z_Sisteco_TK_LDev
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_Sisteco_TK_LDev extends PO implements I_Z_Sisteco_TK_LDev, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191118L;

    /** Standard Constructor */
    public X_Z_Sisteco_TK_LDev (Properties ctx, int Z_Sisteco_TK_LDev_ID, String trxName)
    {
      super (ctx, Z_Sisteco_TK_LDev_ID, trxName);
      /** if (Z_Sisteco_TK_LDev_ID == 0)
        {
			setI_IsImported (false);
// N
			setProcessed (false);
// N
			setZ_Sisteco_TK_LDev_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_Sisteco_TK_LDev (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_Sisteco_TK_LDev[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		set_Value (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_Product getM_Product() throws RuntimeException
    {
		return (I_M_Product)MTable.get(getCtx(), I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set ST_Cantidad.
		@param ST_Cantidad 
		 
	  */
	public void setST_Cantidad (BigDecimal ST_Cantidad)
	{
		set_Value (COLUMNNAME_ST_Cantidad, ST_Cantidad);
	}

	/** Get ST_Cantidad.
		@return  
	  */
	public BigDecimal getST_Cantidad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_Cantidad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_CodigoArticuloOriginal.
		@param ST_CodigoArticuloOriginal ST_CodigoArticuloOriginal	  */
	public void setST_CodigoArticuloOriginal (String ST_CodigoArticuloOriginal)
	{
		set_Value (COLUMNNAME_ST_CodigoArticuloOriginal, ST_CodigoArticuloOriginal);
	}

	/** Get ST_CodigoArticuloOriginal.
		@return ST_CodigoArticuloOriginal	  */
	public String getST_CodigoArticuloOriginal () 
	{
		return (String)get_Value(COLUMNNAME_ST_CodigoArticuloOriginal);
	}

	/** Set ST_CodigoArtSubf.
		@param ST_CodigoArtSubf ST_CodigoArtSubf	  */
	public void setST_CodigoArtSubf (String ST_CodigoArtSubf)
	{
		set_Value (COLUMNNAME_ST_CodigoArtSubf, ST_CodigoArtSubf);
	}

	/** Get ST_CodigoArtSubf.
		@return ST_CodigoArtSubf	  */
	public String getST_CodigoArtSubf () 
	{
		return (String)get_Value(COLUMNNAME_ST_CodigoArtSubf);
	}

	/** Set ST_CodigoIVA.
		@param ST_CodigoIVA ST_CodigoIVA	  */
	public void setST_CodigoIVA (String ST_CodigoIVA)
	{
		set_Value (COLUMNNAME_ST_CodigoIVA, ST_CodigoIVA);
	}

	/** Get ST_CodigoIVA.
		@return ST_CodigoIVA	  */
	public String getST_CodigoIVA () 
	{
		return (String)get_Value(COLUMNNAME_ST_CodigoIVA);
	}

	/** Set ST_CodigoSupervisora.
		@param ST_CodigoSupervisora ST_CodigoSupervisora	  */
	public void setST_CodigoSupervisora (String ST_CodigoSupervisora)
	{
		set_Value (COLUMNNAME_ST_CodigoSupervisora, ST_CodigoSupervisora);
	}

	/** Get ST_CodigoSupervisora.
		@return ST_CodigoSupervisora	  */
	public String getST_CodigoSupervisora () 
	{
		return (String)get_Value(COLUMNNAME_ST_CodigoSupervisora);
	}

	/** Set ST_CodigoVendedor.
		@param ST_CodigoVendedor ST_CodigoVendedor	  */
	public void setST_CodigoVendedor (String ST_CodigoVendedor)
	{
		set_Value (COLUMNNAME_ST_CodigoVendedor, ST_CodigoVendedor);
	}

	/** Get ST_CodigoVendedor.
		@return ST_CodigoVendedor	  */
	public String getST_CodigoVendedor () 
	{
		return (String)get_Value(COLUMNNAME_ST_CodigoVendedor);
	}

	/** Set ST_IndicadorArtSubf.
		@param ST_IndicadorArtSubf ST_IndicadorArtSubf	  */
	public void setST_IndicadorArtSubf (String ST_IndicadorArtSubf)
	{
		set_Value (COLUMNNAME_ST_IndicadorArtSubf, ST_IndicadorArtSubf);
	}

	/** Get ST_IndicadorArtSubf.
		@return ST_IndicadorArtSubf	  */
	public String getST_IndicadorArtSubf () 
	{
		return (String)get_Value(COLUMNNAME_ST_IndicadorArtSubf);
	}

	/** Set ST_IVA.
		@param ST_IVA ST_IVA	  */
	public void setST_IVA (BigDecimal ST_IVA)
	{
		set_Value (COLUMNNAME_ST_IVA, ST_IVA);
	}

	/** Get ST_IVA.
		@return ST_IVA	  */
	public BigDecimal getST_IVA () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_IVA);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_IVADescuento.
		@param ST_IVADescuento ST_IVADescuento	  */
	public void setST_IVADescuento (BigDecimal ST_IVADescuento)
	{
		set_Value (COLUMNNAME_ST_IVADescuento, ST_IVADescuento);
	}

	/** Get ST_IVADescuento.
		@return ST_IVADescuento	  */
	public BigDecimal getST_IVADescuento () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_IVADescuento);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_IvaPrecioInicial.
		@param ST_IvaPrecioInicial 
		Atributo para interface de Sisteco
	  */
	public void setST_IvaPrecioInicial (BigDecimal ST_IvaPrecioInicial)
	{
		set_Value (COLUMNNAME_ST_IvaPrecioInicial, ST_IvaPrecioInicial);
	}

	/** Get ST_IvaPrecioInicial.
		@return Atributo para interface de Sisteco
	  */
	public BigDecimal getST_IvaPrecioInicial () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_IvaPrecioInicial);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_LineaCancelada.
		@param ST_LineaCancelada ST_LineaCancelada	  */
	public void setST_LineaCancelada (int ST_LineaCancelada)
	{
		set_Value (COLUMNNAME_ST_LineaCancelada, Integer.valueOf(ST_LineaCancelada));
	}

	/** Get ST_LineaCancelada.
		@return ST_LineaCancelada	  */
	public int getST_LineaCancelada () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ST_LineaCancelada);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ST_ModoIngreso.
		@param ST_ModoIngreso 
		 
	  */
	public void setST_ModoIngreso (String ST_ModoIngreso)
	{
		set_Value (COLUMNNAME_ST_ModoIngreso, ST_ModoIngreso);
	}

	/** Get ST_ModoIngreso.
		@return  
	  */
	public String getST_ModoIngreso () 
	{
		return (String)get_Value(COLUMNNAME_ST_ModoIngreso);
	}

	/** Set ST_NumeroLinea.
		@param ST_NumeroLinea 
		 
	  */
	public void setST_NumeroLinea (String ST_NumeroLinea)
	{
		set_Value (COLUMNNAME_ST_NumeroLinea, ST_NumeroLinea);
	}

	/** Get ST_NumeroLinea.
		@return  
	  */
	public String getST_NumeroLinea () 
	{
		return (String)get_Value(COLUMNNAME_ST_NumeroLinea);
	}

	/** Set ST_PositionFile.
		@param ST_PositionFile ST_PositionFile	  */
	public void setST_PositionFile (String ST_PositionFile)
	{
		set_Value (COLUMNNAME_ST_PositionFile, ST_PositionFile);
	}

	/** Get ST_PositionFile.
		@return ST_PositionFile	  */
	public String getST_PositionFile () 
	{
		return (String)get_Value(COLUMNNAME_ST_PositionFile);
	}

	/** Set ST_Precio.
		@param ST_Precio ST_Precio	  */
	public void setST_Precio (BigDecimal ST_Precio)
	{
		set_Value (COLUMNNAME_ST_Precio, ST_Precio);
	}

	/** Get ST_Precio.
		@return ST_Precio	  */
	public BigDecimal getST_Precio () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_Precio);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_PrecioDescuento.
		@param ST_PrecioDescuento ST_PrecioDescuento	  */
	public void setST_PrecioDescuento (BigDecimal ST_PrecioDescuento)
	{
		set_Value (COLUMNNAME_ST_PrecioDescuento, ST_PrecioDescuento);
	}

	/** Get ST_PrecioDescuento.
		@return ST_PrecioDescuento	  */
	public BigDecimal getST_PrecioDescuento () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_PrecioDescuento);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_PrecioInicial.
		@param ST_PrecioInicial 
		Atributo de Sisteco para Interface
	  */
	public void setST_PrecioInicial (BigDecimal ST_PrecioInicial)
	{
		set_Value (COLUMNNAME_ST_PrecioInicial, ST_PrecioInicial);
	}

	/** Get ST_PrecioInicial.
		@return Atributo de Sisteco para Interface
	  */
	public BigDecimal getST_PrecioInicial () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_PrecioInicial);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TimestampLinea.
		@param ST_TimestampLinea 
		 
	  */
	public void setST_TimestampLinea (String ST_TimestampLinea)
	{
		set_Value (COLUMNNAME_ST_TimestampLinea, ST_TimestampLinea);
	}

	/** Get ST_TimestampLinea.
		@return  
	  */
	public String getST_TimestampLinea () 
	{
		return (String)get_Value(COLUMNNAME_ST_TimestampLinea);
	}

	/** Set ST_TipoLinea.
		@param ST_TipoLinea ST_TipoLinea	  */
	public void setST_TipoLinea (String ST_TipoLinea)
	{
		set_Value (COLUMNNAME_ST_TipoLinea, ST_TipoLinea);
	}

	/** Get ST_TipoLinea.
		@return ST_TipoLinea	  */
	public String getST_TipoLinea () 
	{
		return (String)get_Value(COLUMNNAME_ST_TipoLinea);
	}

	public I_Z_SistecoInterfacePazos getZ_SistecoInterfacePazos() throws RuntimeException
    {
		return (I_Z_SistecoInterfacePazos)MTable.get(getCtx(), I_Z_SistecoInterfacePazos.Table_Name)
			.getPO(getZ_SistecoInterfacePazos_ID(), get_TrxName());	}

	/** Set Z_SistecoInterfacePazos ID.
		@param Z_SistecoInterfacePazos_ID Z_SistecoInterfacePazos ID	  */
	public void setZ_SistecoInterfacePazos_ID (int Z_SistecoInterfacePazos_ID)
	{
		if (Z_SistecoInterfacePazos_ID < 1) 
			set_Value (COLUMNNAME_Z_SistecoInterfacePazos_ID, null);
		else 
			set_Value (COLUMNNAME_Z_SistecoInterfacePazos_ID, Integer.valueOf(Z_SistecoInterfacePazos_ID));
	}

	/** Get Z_SistecoInterfacePazos ID.
		@return Z_SistecoInterfacePazos ID	  */
	public int getZ_SistecoInterfacePazos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_SistecoInterfacePazos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_Z_SistecoTipoLineaPazos getZ_SistecoTipoLineaPazos() throws RuntimeException
    {
		return (I_Z_SistecoTipoLineaPazos)MTable.get(getCtx(), I_Z_SistecoTipoLineaPazos.Table_Name)
			.getPO(getZ_SistecoTipoLineaPazos_ID(), get_TrxName());	}

	/** Set Z_SistecoTipoLineaPazos ID.
		@param Z_SistecoTipoLineaPazos_ID Z_SistecoTipoLineaPazos ID	  */
	public void setZ_SistecoTipoLineaPazos_ID (int Z_SistecoTipoLineaPazos_ID)
	{
		if (Z_SistecoTipoLineaPazos_ID < 1) 
			set_Value (COLUMNNAME_Z_SistecoTipoLineaPazos_ID, null);
		else 
			set_Value (COLUMNNAME_Z_SistecoTipoLineaPazos_ID, Integer.valueOf(Z_SistecoTipoLineaPazos_ID));
	}

	/** Get Z_SistecoTipoLineaPazos ID.
		@return Z_SistecoTipoLineaPazos ID	  */
	public int getZ_SistecoTipoLineaPazos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_SistecoTipoLineaPazos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_Z_Sisteco_TK_CVta getZ_Sisteco_TK_CVta() throws RuntimeException
    {
		return (I_Z_Sisteco_TK_CVta)MTable.get(getCtx(), I_Z_Sisteco_TK_CVta.Table_Name)
			.getPO(getZ_Sisteco_TK_CVta_ID(), get_TrxName());	}

	/** Set Z_Sisteco_TK_CVta ID.
		@param Z_Sisteco_TK_CVta_ID Z_Sisteco_TK_CVta ID	  */
	public void setZ_Sisteco_TK_CVta_ID (int Z_Sisteco_TK_CVta_ID)
	{
		if (Z_Sisteco_TK_CVta_ID < 1) 
			set_Value (COLUMNNAME_Z_Sisteco_TK_CVta_ID, null);
		else 
			set_Value (COLUMNNAME_Z_Sisteco_TK_CVta_ID, Integer.valueOf(Z_Sisteco_TK_CVta_ID));
	}

	/** Get Z_Sisteco_TK_CVta ID.
		@return Z_Sisteco_TK_CVta ID	  */
	public int getZ_Sisteco_TK_CVta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_Sisteco_TK_CVta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Z_Sisteco_TK_LDev ID.
		@param Z_Sisteco_TK_LDev_ID Z_Sisteco_TK_LDev ID	  */
	public void setZ_Sisteco_TK_LDev_ID (int Z_Sisteco_TK_LDev_ID)
	{
		if (Z_Sisteco_TK_LDev_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_Sisteco_TK_LDev_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_Sisteco_TK_LDev_ID, Integer.valueOf(Z_Sisteco_TK_LDev_ID));
	}

	/** Get Z_Sisteco_TK_LDev ID.
		@return Z_Sisteco_TK_LDev ID	  */
	public int getZ_Sisteco_TK_LDev_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_Sisteco_TK_LDev_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}