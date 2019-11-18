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

/** Generated Model for Z_SistecoImpuesto
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoImpuesto extends PO implements I_Z_SistecoImpuesto, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191118L;

    /** Standard Constructor */
    public X_Z_SistecoImpuesto (Properties ctx, int Z_SistecoImpuesto_ID, String trxName)
    {
      super (ctx, Z_SistecoImpuesto_ID, trxName);
      /** if (Z_SistecoImpuesto_ID == 0)
        {
			setC_TaxCategory_ID (0);
			setC_Tax_ID (0);
			setST_CodigoIVA (null);
			setZ_SistecoConfig_ID (0);
			setZ_SistecoImpuesto_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoImpuesto (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_SistecoImpuesto[")
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

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
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

	/** Set Z_SistecoImpuesto ID.
		@param Z_SistecoImpuesto_ID Z_SistecoImpuesto ID	  */
	public void setZ_SistecoImpuesto_ID (int Z_SistecoImpuesto_ID)
	{
		if (Z_SistecoImpuesto_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoImpuesto_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoImpuesto_ID, Integer.valueOf(Z_SistecoImpuesto_ID));
	}

	/** Get Z_SistecoImpuesto ID.
		@return Z_SistecoImpuesto ID	  */
	public int getZ_SistecoImpuesto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_SistecoImpuesto_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}