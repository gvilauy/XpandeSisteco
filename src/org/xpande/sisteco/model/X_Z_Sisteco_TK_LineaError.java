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

/** Generated Model for Z_Sisteco_TK_LineaError
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_Sisteco_TK_LineaError extends PO implements I_Z_Sisteco_TK_LineaError, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170601L;

    /** Standard Constructor */
    public X_Z_Sisteco_TK_LineaError (Properties ctx, int Z_Sisteco_TK_LineaError_ID, String trxName)
    {
      super (ctx, Z_Sisteco_TK_LineaError_ID, trxName);
      /** if (Z_Sisteco_TK_LineaError_ID == 0)
        {
			setZ_Sisteco_TK_LineaError_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_Sisteco_TK_LineaError (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_Sisteco_TK_LineaError[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set File Name.
		@param FileName 
		Name of the local file or URL
	  */
	public void setFileName (String FileName)
	{
		set_Value (COLUMNNAME_FileName, FileName);
	}

	/** Get File Name.
		@return Name of the local file or URL
	  */
	public String getFileName () 
	{
		return (String)get_Value(COLUMNNAME_FileName);
	}

	/** Set ST_LineaArchivo.
		@param ST_LineaArchivo ST_LineaArchivo	  */
	public void setST_LineaArchivo (String ST_LineaArchivo)
	{
		set_Value (COLUMNNAME_ST_LineaArchivo, ST_LineaArchivo);
	}

	/** Get ST_LineaArchivo.
		@return ST_LineaArchivo	  */
	public String getST_LineaArchivo () 
	{
		return (String)get_Value(COLUMNNAME_ST_LineaArchivo);
	}

	/** Set ST_PositionFile.
		@param ST_PositionFile ST_PositionFile	  */
	public void setST_PositionFile (String ST_PositionFile)
	{
		set_Value (COLUMNNAME_ST_PositionFile, ST_PositionFile);
	}

	/** Get ST_PositionFile.
		@return ST_PositionFile	  */
	public String getST_PositionFile () 
	{
		return (String)get_Value(COLUMNNAME_ST_PositionFile);
	}

	/** Set ST_TipoLinea.
		@param ST_TipoLinea ST_TipoLinea	  */
	public void setST_TipoLinea (String ST_TipoLinea)
	{
		set_Value (COLUMNNAME_ST_TipoLinea, ST_TipoLinea);
	}

	/** Get ST_TipoLinea.
		@return ST_TipoLinea	  */
	public String getST_TipoLinea () 
	{
		return (String)get_Value(COLUMNNAME_ST_TipoLinea);
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

	/** Set Z_Sisteco_TK_LineaError ID.
		@param Z_Sisteco_TK_LineaError_ID Z_Sisteco_TK_LineaError ID	  */
	public void setZ_Sisteco_TK_LineaError_ID (int Z_Sisteco_TK_LineaError_ID)
	{
		if (Z_Sisteco_TK_LineaError_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_Sisteco_TK_LineaError_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_Sisteco_TK_LineaError_ID, Integer.valueOf(Z_Sisteco_TK_LineaError_ID));
	}

	/** Get Z_Sisteco_TK_LineaError ID.
		@return Z_Sisteco_TK_LineaError ID	  */
	public int getZ_Sisteco_TK_LineaError_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_Sisteco_TK_LineaError_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}