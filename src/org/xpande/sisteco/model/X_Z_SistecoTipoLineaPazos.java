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

/** Generated Model for Z_SistecoTipoLineaPazos
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoTipoLineaPazos extends PO implements I_Z_SistecoTipoLineaPazos, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170601L;

    /** Standard Constructor */
    public X_Z_SistecoTipoLineaPazos (Properties ctx, int Z_SistecoTipoLineaPazos_ID, String trxName)
    {
      super (ctx, Z_SistecoTipoLineaPazos_ID, trxName);
      /** if (Z_SistecoTipoLineaPazos_ID == 0)
        {
			setIsCabezalArchivo (false);
// N
			setName (null);
			setValue (null);
			setZ_SistecoConfig_ID (0);
			setZ_SistecoTipoLineaPazos_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoTipoLineaPazos (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_SistecoTipoLineaPazos[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_ImpFormat getAD_ImpFormat() throws RuntimeException
    {
		return (I_AD_ImpFormat)MTable.get(getCtx(), I_AD_ImpFormat.Table_Name)
			.getPO(getAD_ImpFormat_ID(), get_TrxName());	}

	/** Set Import Format.
		@param AD_ImpFormat_ID Import Format	  */
	public void setAD_ImpFormat_ID (int AD_ImpFormat_ID)
	{
		if (AD_ImpFormat_ID < 1) 
			set_Value (COLUMNNAME_AD_ImpFormat_ID, null);
		else 
			set_Value (COLUMNNAME_AD_ImpFormat_ID, Integer.valueOf(AD_ImpFormat_ID));
	}

	/** Get Import Format.
		@return Import Format	  */
	public int getAD_ImpFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ImpFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set IsCabezalArchivo.
		@param IsCabezalArchivo IsCabezalArchivo	  */
	public void setIsCabezalArchivo (boolean IsCabezalArchivo)
	{
		set_Value (COLUMNNAME_IsCabezalArchivo, Boolean.valueOf(IsCabezalArchivo));
	}

	/** Get IsCabezalArchivo.
		@return IsCabezalArchivo	  */
	public boolean isCabezalArchivo () 
	{
		Object oo = get_Value(COLUMNNAME_IsCabezalArchivo);
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

	/** Set Z_SistecoTipoLineaPazos ID.
		@param Z_SistecoTipoLineaPazos_ID Z_SistecoTipoLineaPazos ID	  */
	public void setZ_SistecoTipoLineaPazos_ID (int Z_SistecoTipoLineaPazos_ID)
	{
		if (Z_SistecoTipoLineaPazos_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoTipoLineaPazos_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoTipoLineaPazos_ID, Integer.valueOf(Z_SistecoTipoLineaPazos_ID));
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
}