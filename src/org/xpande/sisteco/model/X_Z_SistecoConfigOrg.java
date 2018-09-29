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

/** Generated Model for Z_SistecoConfigOrg
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoConfigOrg extends PO implements I_Z_SistecoConfigOrg, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180213L;

    /** Standard Constructor */
    public X_Z_SistecoConfigOrg (Properties ctx, int Z_SistecoConfigOrg_ID, String trxName)
    {
      super (ctx, Z_SistecoConfigOrg_ID, trxName);
      /** if (Z_SistecoConfigOrg_ID == 0)
        {
			setAD_OrgTrx_ID (0);
			setZ_SistecoConfig_ID (0);
			setZ_SistecoConfigOrg_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoConfigOrg (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_SistecoConfigOrg[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
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

	/** Set Z_SistecoConfigOrg ID.
		@param Z_SistecoConfigOrg_ID Z_SistecoConfigOrg ID	  */
	public void setZ_SistecoConfigOrg_ID (int Z_SistecoConfigOrg_ID)
	{
		if (Z_SistecoConfigOrg_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoConfigOrg_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoConfigOrg_ID, Integer.valueOf(Z_SistecoConfigOrg_ID));
	}

	/** Get Z_SistecoConfigOrg ID.
		@return Z_SistecoConfigOrg ID	  */
	public int getZ_SistecoConfigOrg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_SistecoConfigOrg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}