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

/** Generated Model for Z_Sisteco_TK_TaxError
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_Sisteco_TK_TaxError extends PO implements I_Z_Sisteco_TK_TaxError, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180415L;

    /** Standard Constructor */
    public X_Z_Sisteco_TK_TaxError (Properties ctx, int Z_Sisteco_TK_TaxError_ID, String trxName)
    {
      super (ctx, Z_Sisteco_TK_TaxError_ID, trxName);
      /** if (Z_Sisteco_TK_TaxError_ID == 0)
        {
			setC_TaxCategory_ID (0);
			setM_Product_ID (0);
			setST_CodigoIVA (null);
			setZ_SistecoInterfacePazos_ID (0);
			setZ_Sisteco_TK_TaxError_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_Sisteco_TK_TaxError (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_Sisteco_TK_TaxError[")
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

	/** Set Z_Sisteco_TK_TaxError ID.
		@param Z_Sisteco_TK_TaxError_ID Z_Sisteco_TK_TaxError ID	  */
	public void setZ_Sisteco_TK_TaxError_ID (int Z_Sisteco_TK_TaxError_ID)
	{
		if (Z_Sisteco_TK_TaxError_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_Sisteco_TK_TaxError_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_Sisteco_TK_TaxError_ID, Integer.valueOf(Z_Sisteco_TK_TaxError_ID));
	}

	/** Get Z_Sisteco_TK_TaxError ID.
		@return Z_Sisteco_TK_TaxError ID	  */
	public int getZ_Sisteco_TK_TaxError_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_Sisteco_TK_TaxError_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}