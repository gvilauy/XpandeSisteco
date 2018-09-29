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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for Z_SistecoInterfacePazos
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoInterfacePazos extends PO implements I_Z_SistecoInterfacePazos, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170908L;

    /** Standard Constructor */
    public X_Z_SistecoInterfacePazos (Properties ctx, int Z_SistecoInterfacePazos_ID, String trxName)
    {
      super (ctx, Z_SistecoInterfacePazos_ID, trxName);
      /** if (Z_SistecoInterfacePazos_ID == 0)
        {
			setFileName (null);
			setIsBatchProcessed (false);
// N
			setProcessed (false);
// N
			setZ_SistecoInterfacePazos_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoInterfacePazos (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_SistecoInterfacePazos[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		set_Value (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
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

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
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

	/** Set FileNameNoPath.
		@param FileNameNoPath 
		Nombre de archivo sin Path
	  */
	public void setFileNameNoPath (String FileNameNoPath)
	{
		set_Value (COLUMNNAME_FileNameNoPath, FileNameNoPath);
	}

	/** Get FileNameNoPath.
		@return Nombre de archivo sin Path
	  */
	public String getFileNameNoPath () 
	{
		return (String)get_Value(COLUMNNAME_FileNameNoPath);
	}

	/** Set IsBatchProcessed.
		@param IsBatchProcessed IsBatchProcessed	  */
	public void setIsBatchProcessed (boolean IsBatchProcessed)
	{
		set_Value (COLUMNNAME_IsBatchProcessed, Boolean.valueOf(IsBatchProcessed));
	}

	/** Get IsBatchProcessed.
		@return IsBatchProcessed	  */
	public boolean isBatchProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_IsBatchProcessed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set ProcessButton.
		@param ProcessButton ProcessButton	  */
	public void setProcessButton (String ProcessButton)
	{
		set_Value (COLUMNNAME_ProcessButton, ProcessButton);
	}

	/** Get ProcessButton.
		@return ProcessButton	  */
	public String getProcessButton () 
	{
		return (String)get_Value(COLUMNNAME_ProcessButton);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Set ST_ContadorCabezales.
		@param ST_ContadorCabezales ST_ContadorCabezales	  */
	public void setST_ContadorCabezales (int ST_ContadorCabezales)
	{
		set_Value (COLUMNNAME_ST_ContadorCabezales, Integer.valueOf(ST_ContadorCabezales));
	}

	/** Get ST_ContadorCabezales.
		@return ST_ContadorCabezales	  */
	public int getST_ContadorCabezales () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ST_ContadorCabezales);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ST_ContadorLineas.
		@param ST_ContadorLineas ST_ContadorLineas	  */
	public void setST_ContadorLineas (int ST_ContadorLineas)
	{
		set_Value (COLUMNNAME_ST_ContadorLineas, Integer.valueOf(ST_ContadorLineas));
	}

	/** Get ST_ContadorLineas.
		@return ST_ContadorLineas	  */
	public int getST_ContadorLineas () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ST_ContadorLineas);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ST_ContadorTotal.
		@param ST_ContadorTotal ST_ContadorTotal	  */
	public void setST_ContadorTotal (int ST_ContadorTotal)
	{
		set_Value (COLUMNNAME_ST_ContadorTotal, Integer.valueOf(ST_ContadorTotal));
	}

	/** Get ST_ContadorTotal.
		@return ST_ContadorTotal	  */
	public int getST_ContadorTotal () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ST_ContadorTotal);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Z_SistecoInterfacePazos ID.
		@param Z_SistecoInterfacePazos_ID Z_SistecoInterfacePazos ID	  */
	public void setZ_SistecoInterfacePazos_ID (int Z_SistecoInterfacePazos_ID)
	{
		if (Z_SistecoInterfacePazos_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoInterfacePazos_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoInterfacePazos_ID, Integer.valueOf(Z_SistecoInterfacePazos_ID));
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
}