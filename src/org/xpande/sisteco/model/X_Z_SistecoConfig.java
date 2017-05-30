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

/** Generated Model for Z_SistecoConfig
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoConfig extends PO implements I_Z_SistecoConfig, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170601L;

    /** Standard Constructor */
    public X_Z_SistecoConfig (Properties ctx, int Z_SistecoConfig_ID, String trxName)
    {
      super (ctx, Z_SistecoConfig_ID, trxName);
      /** if (Z_SistecoConfig_ID == 0)
        {
			setZ_SistecoConfig_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoConfig (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_SistecoConfig[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set PrefijoArchivoPazos.
		@param PrefijoArchivoPazos PrefijoArchivoPazos	  */
	public void setPrefijoArchivoPazos (String PrefijoArchivoPazos)
	{
		set_Value (COLUMNNAME_PrefijoArchivoPazos, PrefijoArchivoPazos);
	}

	/** Get PrefijoArchivoPazos.
		@return PrefijoArchivoPazos	  */
	public String getPrefijoArchivoPazos () 
	{
		return (String)get_Value(COLUMNNAME_PrefijoArchivoPazos);
	}

	/** Set RutaDestinoSistema.
		@param RutaDestinoSistema RutaDestinoSistema	  */
	public void setRutaDestinoSistema (String RutaDestinoSistema)
	{
		set_Value (COLUMNNAME_RutaDestinoSistema, RutaDestinoSistema);
	}

	/** Get RutaDestinoSistema.
		@return RutaDestinoSistema	  */
	public String getRutaDestinoSistema () 
	{
		return (String)get_Value(COLUMNNAME_RutaDestinoSistema);
	}

	/** Set RutaHistoricoPazos.
		@param RutaHistoricoPazos RutaHistoricoPazos	  */
	public void setRutaHistoricoPazos (String RutaHistoricoPazos)
	{
		set_Value (COLUMNNAME_RutaHistoricoPazos, RutaHistoricoPazos);
	}

	/** Get RutaHistoricoPazos.
		@return RutaHistoricoPazos	  */
	public String getRutaHistoricoPazos () 
	{
		return (String)get_Value(COLUMNNAME_RutaHistoricoPazos);
	}

	/** Set RutaOrigenPazos.
		@param RutaOrigenPazos RutaOrigenPazos	  */
	public void setRutaOrigenPazos (String RutaOrigenPazos)
	{
		set_Value (COLUMNNAME_RutaOrigenPazos, RutaOrigenPazos);
	}

	/** Get RutaOrigenPazos.
		@return RutaOrigenPazos	  */
	public String getRutaOrigenPazos () 
	{
		return (String)get_Value(COLUMNNAME_RutaOrigenPazos);
	}

	/** Set Z_SistecoConfig ID.
		@param Z_SistecoConfig_ID Z_SistecoConfig ID	  */
	public void setZ_SistecoConfig_ID (int Z_SistecoConfig_ID)
	{
		if (Z_SistecoConfig_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoConfig_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoConfig_ID, Integer.valueOf(Z_SistecoConfig_ID));
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