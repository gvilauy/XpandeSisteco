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

/** Generated Model for Z_SistecoPazosTax
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoPazosTax extends PO implements I_Z_SistecoPazosTax, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191119L;

    /** Standard Constructor */
    public X_Z_SistecoPazosTax (Properties ctx, int Z_SistecoPazosTax_ID, String trxName)
    {
      super (ctx, Z_SistecoPazosTax_ID, trxName);
      /** if (Z_SistecoPazosTax_ID == 0)
        {
			setDateTrx (new Timestamp( System.currentTimeMillis() ));
			setTaxAmt (Env.ZERO);
			setTaxBaseAmt (Env.ZERO);
			setZ_SistecoInterfacePazos_ID (0);
			setZ_SistecoPazosTax_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoPazosTax (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_SistecoPazosTax[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_C_Tax getC_Tax() throws RuntimeException
    {
		return (I_C_Tax)MTable.get(getCtx(), I_C_Tax.Table_Name)
			.getPO(getC_Tax_ID(), get_TrxName());	}

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1) 
			set_Value (COLUMNNAME_C_Tax_ID, null);
		else 
			set_Value (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
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

	/** Set Tax base Amount.
		@param TaxBaseAmt 
		Base for calculating the tax amount
	  */
	public void setTaxBaseAmt (BigDecimal TaxBaseAmt)
	{
		set_Value (COLUMNNAME_TaxBaseAmt, TaxBaseAmt);
	}

	/** Get Tax base Amount.
		@return Base for calculating the tax amount
	  */
	public BigDecimal getTaxBaseAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxBaseAmt);
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

	/** Set Z_SistecoPazosTax ID.
		@param Z_SistecoPazosTax_ID Z_SistecoPazosTax ID	  */
	public void setZ_SistecoPazosTax_ID (int Z_SistecoPazosTax_ID)
	{
		if (Z_SistecoPazosTax_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoPazosTax_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoPazosTax_ID, Integer.valueOf(Z_SistecoPazosTax_ID));
	}

	/** Get Z_SistecoPazosTax ID.
		@return Z_SistecoPazosTax ID	  */
	public int getZ_SistecoPazosTax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_SistecoPazosTax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}