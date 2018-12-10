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

/** Generated Model for Z_SistecoPazosTaxTKRUT
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoPazosTaxTKRUT extends PO implements I_Z_SistecoPazosTaxTKRUT, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181210L;

    /** Standard Constructor */
    public X_Z_SistecoPazosTaxTKRUT (Properties ctx, int Z_SistecoPazosTaxTKRUT_ID, String trxName)
    {
      super (ctx, Z_SistecoPazosTaxTKRUT_ID, trxName);
      /** if (Z_SistecoPazosTaxTKRUT_ID == 0)
        {
			setZ_SistecoInterfacePazos_ID (0);
			setZ_SistecoPazosTaxTKRUT_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoPazosTaxTKRUT (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_SistecoPazosTaxTKRUT[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set AmtSubtotal.
		@param AmtSubtotal 
		Subtotales para no mostrar impuestos incluídos
	  */
	public void setAmtSubtotal (BigDecimal AmtSubtotal)
	{
		set_Value (COLUMNNAME_AmtSubtotal, AmtSubtotal);
	}

	/** Get AmtSubtotal.
		@return Subtotales para no mostrar impuestos incluídos
	  */
	public BigDecimal getAmtSubtotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtSubtotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_TaxCategory getC_TaxCategory() throws RuntimeException
    {
		return (I_C_TaxCategory)MTable.get(getCtx(), I_C_TaxCategory.Table_Name)
			.getPO(getC_TaxCategory_ID(), get_TrxName());	}

	/** Set Tax Category.
		@param C_TaxCategory_ID 
		Tax Category
	  */
	public void setC_TaxCategory_ID (int C_TaxCategory_ID)
	{
		if (C_TaxCategory_ID < 1) 
			set_Value (COLUMNNAME_C_TaxCategory_ID, null);
		else 
			set_Value (COLUMNNAME_C_TaxCategory_ID, Integer.valueOf(C_TaxCategory_ID));
	}

	/** Get Tax Category.
		@return Tax Category
	  */
	public int getC_TaxCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_TaxCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set ST_DocumentoReceptor.
		@param ST_DocumentoReceptor ST_DocumentoReceptor	  */
	public void setST_DocumentoReceptor (String ST_DocumentoReceptor)
	{
		set_Value (COLUMNNAME_ST_DocumentoReceptor, ST_DocumentoReceptor);
	}

	/** Get ST_DocumentoReceptor.
		@return ST_DocumentoReceptor	  */
	public String getST_DocumentoReceptor () 
	{
		return (String)get_Value(COLUMNNAME_ST_DocumentoReceptor);
	}

	/** Set ST_NombreReceptor.
		@param ST_NombreReceptor ST_NombreReceptor	  */
	public void setST_NombreReceptor (String ST_NombreReceptor)
	{
		set_Value (COLUMNNAME_ST_NombreReceptor, ST_NombreReceptor);
	}

	/** Get ST_NombreReceptor.
		@return ST_NombreReceptor	  */
	public String getST_NombreReceptor () 
	{
		return (String)get_Value(COLUMNNAME_ST_NombreReceptor);
	}

	/** Set ST_NumeroTicket.
		@param ST_NumeroTicket ST_NumeroTicket	  */
	public void setST_NumeroTicket (String ST_NumeroTicket)
	{
		set_Value (COLUMNNAME_ST_NumeroTicket, ST_NumeroTicket);
	}

	/** Get ST_NumeroTicket.
		@return ST_NumeroTicket	  */
	public String getST_NumeroTicket () 
	{
		return (String)get_Value(COLUMNNAME_ST_NumeroTicket);
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Z_SistecoPazosTaxTKRUT ID.
		@param Z_SistecoPazosTaxTKRUT_ID Z_SistecoPazosTaxTKRUT ID	  */
	public void setZ_SistecoPazosTaxTKRUT_ID (int Z_SistecoPazosTaxTKRUT_ID)
	{
		if (Z_SistecoPazosTaxTKRUT_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoPazosTaxTKRUT_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoPazosTaxTKRUT_ID, Integer.valueOf(Z_SistecoPazosTaxTKRUT_ID));
	}

	/** Get Z_SistecoPazosTaxTKRUT ID.
		@return Z_SistecoPazosTaxTKRUT ID	  */
	public int getZ_SistecoPazosTaxTKRUT_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_SistecoPazosTaxTKRUT_ID);
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
}