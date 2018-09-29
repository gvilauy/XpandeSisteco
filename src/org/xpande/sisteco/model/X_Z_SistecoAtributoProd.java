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
import org.compiere.util.KeyNamePair;

/** Generated Model for Z_SistecoAtributoProd
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoAtributoProd extends PO implements I_Z_SistecoAtributoProd, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170721L;

    /** Standard Constructor */
    public X_Z_SistecoAtributoProd (Properties ctx, int Z_SistecoAtributoProd_ID, String trxName)
    {
      super (ctx, Z_SistecoAtributoProd_ID, trxName);
      /** if (Z_SistecoAtributoProd_ID == 0)
        {
			setIsAtributoTandem (false);
// N
			setIsSelected (false);
// N
			setName (null);
			setSeqNo (0);
			setValue (null);
			setZ_SistecoAtributoProd_ID (0);
			setZ_SistecoConfig_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoAtributoProd (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_SistecoAtributoProd[")
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

	/** Set IsAtributoTandem.
		@param IsAtributoTandem 
		En un atributo Tandem para Proveedor de POS Sisteco
	  */
	public void setIsAtributoTandem (boolean IsAtributoTandem)
	{
		set_Value (COLUMNNAME_IsAtributoTandem, Boolean.valueOf(IsAtributoTandem));
	}

	/** Get IsAtributoTandem.
		@return En un atributo Tandem para Proveedor de POS Sisteco
	  */
	public boolean isAtributoTandem () 
	{
		Object oo = get_Value(COLUMNNAME_IsAtributoTandem);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
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

	/** Set Z_SistecoAtributoProd ID.
		@param Z_SistecoAtributoProd_ID Z_SistecoAtributoProd ID	  */
	public void setZ_SistecoAtributoProd_ID (int Z_SistecoAtributoProd_ID)
	{
		if (Z_SistecoAtributoProd_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoAtributoProd_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoAtributoProd_ID, Integer.valueOf(Z_SistecoAtributoProd_ID));
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

	public I_Z_SistecoConfig getZ_SistecoConfig() throws RuntimeException
    {
		return (I_Z_SistecoConfig)MTable.get(getCtx(), I_Z_SistecoConfig.Table_Name)
			.getPO(getZ_SistecoConfig_ID(), get_TrxName());	}

	/** Set Z_SistecoConfig ID.
		@param Z_SistecoConfig_ID Z_SistecoConfig ID	  */
	public void setZ_SistecoConfig_ID (int Z_SistecoConfig_ID)
	{
		if (Z_SistecoConfig_ID < 1) 
			set_Value (COLUMNNAME_Z_SistecoConfig_ID, null);
		else 
			set_Value (COLUMNNAME_Z_SistecoConfig_ID, Integer.valueOf(Z_SistecoConfig_ID));
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