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

/** Generated Model for Z_SistecoAtributoProdUOM
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoAtributoProdUOM extends PO implements I_Z_SistecoAtributoProdUOM, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170721L;

    /** Standard Constructor */
    public X_Z_SistecoAtributoProdUOM (Properties ctx, int Z_SistecoAtributoProdUOM_ID, String trxName)
    {
      super (ctx, Z_SistecoAtributoProdUOM_ID, trxName);
      /** if (Z_SistecoAtributoProdUOM_ID == 0)
        {
			setC_UOM_ID (0);
			setIsSelected (false);
// N
			setZ_SistecoAtributoProd_ID (0);
			setZ_SistecoAtributoProdUOM_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoAtributoProdUOM (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_SistecoAtributoProdUOM[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_UOM getC_UOM() throws RuntimeException
    {
		return (I_C_UOM)MTable.get(getCtx(), I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_Z_SistecoAtributoProd getZ_SistecoAtributoProd() throws RuntimeException
    {
		return (I_Z_SistecoAtributoProd)MTable.get(getCtx(), I_Z_SistecoAtributoProd.Table_Name)
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

	/** Set Z_SistecoAtributoProdUOM ID.
		@param Z_SistecoAtributoProdUOM_ID Z_SistecoAtributoProdUOM ID	  */
	public void setZ_SistecoAtributoProdUOM_ID (int Z_SistecoAtributoProdUOM_ID)
	{
		if (Z_SistecoAtributoProdUOM_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoAtributoProdUOM_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoAtributoProdUOM_ID, Integer.valueOf(Z_SistecoAtributoProdUOM_ID));
	}

	/** Get Z_SistecoAtributoProdUOM ID.
		@return Z_SistecoAtributoProdUOM ID	  */
	public int getZ_SistecoAtributoProdUOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_SistecoAtributoProdUOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}