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

/** Generated Model for Z_Sisteco_TK_ProductoError
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_Sisteco_TK_ProductoError extends PO implements I_Z_Sisteco_TK_ProductoError, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170605L;

    /** Standard Constructor */
    public X_Z_Sisteco_TK_ProductoError (Properties ctx, int Z_Sisteco_TK_ProductoError_ID, String trxName)
    {
      super (ctx, Z_Sisteco_TK_ProductoError_ID, trxName);
      /** if (Z_Sisteco_TK_ProductoError_ID == 0)
        {
			setColumnName (null);
			setTableName (null);
			setValue (null);
			setZ_SistecoInterfacePazos_ID (0);
			setZ_Sisteco_TK_CVta_ID (0);
			setZ_Sisteco_TK_ProductoError_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_Sisteco_TK_ProductoError (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_Sisteco_TK_ProductoError[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set DB Column Name.
		@param ColumnName 
		Name of the column in the database
	  */
	public void setColumnName (String ColumnName)
	{
		set_Value (COLUMNNAME_ColumnName, ColumnName);
	}

	/** Get DB Column Name.
		@return Name of the column in the database
	  */
	public String getColumnName () 
	{
		return (String)get_Value(COLUMNNAME_ColumnName);
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

	/** Set DB Table Name.
		@param TableName 
		Name of the table in the database
	  */
	public void setTableName (String TableName)
	{
		set_Value (COLUMNNAME_TableName, TableName);
	}

	/** Get DB Table Name.
		@return Name of the table in the database
	  */
	public String getTableName () 
	{
		return (String)get_Value(COLUMNNAME_TableName);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
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

	/** Set Z_Sisteco_TK_ProductoError ID.
		@param Z_Sisteco_TK_ProductoError_ID Z_Sisteco_TK_ProductoError ID	  */
	public void setZ_Sisteco_TK_ProductoError_ID (int Z_Sisteco_TK_ProductoError_ID)
	{
		if (Z_Sisteco_TK_ProductoError_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_Sisteco_TK_ProductoError_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_Sisteco_TK_ProductoError_ID, Integer.valueOf(Z_Sisteco_TK_ProductoError_ID));
	}

	/** Get Z_Sisteco_TK_ProductoError ID.
		@return Z_Sisteco_TK_ProductoError ID	  */
	public int getZ_Sisteco_TK_ProductoError_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_Sisteco_TK_ProductoError_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}