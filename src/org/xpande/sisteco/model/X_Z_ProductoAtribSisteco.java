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

/** Generated Model for Z_ProductoAtribSisteco
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_ProductoAtribSisteco extends PO implements I_Z_ProductoAtribSisteco, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170721L;

    /** Standard Constructor */
    public X_Z_ProductoAtribSisteco (Properties ctx, int Z_ProductoAtribSisteco_ID, String trxName)
    {
      super (ctx, Z_ProductoAtribSisteco_ID, trxName);
      /** if (Z_ProductoAtribSisteco_ID == 0)
        {
			setIsSelected (false);
// N
			setM_Product_ID (0);
			setSeqNo (0);
			setZ_ProductoAtribSisteco_ID (0);
			setZ_SistecoAtributoProd_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_ProductoAtribSisteco (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_ProductoAtribSisteco[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Selected.
		@param IsSelected Selected	  */
	public void setIsSelected (boolean IsSelected)
	{
		set_Value (COLUMNNAME_IsSelected, Boolean.valueOf(IsSelected));
	}

	/** Get Selected.
		@return Selected	  */
	public boolean isSelected () 
	{
		Object oo = get_Value(COLUMNNAME_IsSelected);
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

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Z_ProductoAtribSisteco ID.
		@param Z_ProductoAtribSisteco_ID Z_ProductoAtribSisteco ID	  */
	public void setZ_ProductoAtribSisteco_ID (int Z_ProductoAtribSisteco_ID)
	{
		if (Z_ProductoAtribSisteco_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_ProductoAtribSisteco_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_ProductoAtribSisteco_ID, Integer.valueOf(Z_ProductoAtribSisteco_ID));
	}

	/** Get Z_ProductoAtribSisteco ID.
		@return Z_ProductoAtribSisteco ID	  */
	public int getZ_ProductoAtribSisteco_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_ProductoAtribSisteco_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.xpande.sisteco.model.I_Z_SistecoAtributoProd getZ_SistecoAtributoProd() throws RuntimeException
    {
		return (org.xpande.sisteco.model.I_Z_SistecoAtributoProd)MTable.get(getCtx(), org.xpande.sisteco.model.I_Z_SistecoAtributoProd.Table_Name)
			.getPO(getZ_SistecoAtributoProd_ID(), get_TrxName());	}

	/** Set Z_SistecoAtributoProd ID.
		@param Z_SistecoAtributoProd_ID Z_SistecoAtributoProd ID	  */
	public void setZ_SistecoAtributoProd_ID (int Z_SistecoAtributoProd_ID)
	{
		if (Z_SistecoAtributoProd_ID < 1) 
			set_Value (COLUMNNAME_Z_SistecoAtributoProd_ID, null);
		else 
			set_Value (COLUMNNAME_Z_SistecoAtributoProd_ID, Integer.valueOf(Z_SistecoAtributoProd_ID));
	}

	/** Get Z_SistecoAtributoProd ID.
		@return Z_SistecoAtributoProd ID	  */
	public int getZ_SistecoAtributoProd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_SistecoAtributoProd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}