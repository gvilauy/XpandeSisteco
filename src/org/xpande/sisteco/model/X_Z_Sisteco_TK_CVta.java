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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for Z_Sisteco_TK_CVta
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_Sisteco_TK_CVta extends PO implements I_Z_Sisteco_TK_CVta, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170529L;

    /** Standard Constructor */
    public X_Z_Sisteco_TK_CVta (Properties ctx, int Z_Sisteco_TK_CVta_ID, String trxName)
    {
      super (ctx, Z_Sisteco_TK_CVta_ID, trxName);
      /** if (Z_Sisteco_TK_CVta_ID == 0)
        {
			setI_IsImported (false);
// N
			setProcessed (false);
// N
			setZ_SistecoInterfacePazos_ID (0);
			setZ_Sisteco_TK_CVta_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_Sisteco_TK_CVta (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_Sisteco_TK_CVta[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Period getC_Period() throws RuntimeException
    {
		return (I_C_Period)MTable.get(getCtx(), I_C_Period.Table_Name)
			.getPO(getC_Period_ID(), get_TrxName());	}

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1) 
			set_Value (COLUMNNAME_C_Period_ID, null);
		else 
			set_Value (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set ST_CantidadArticulos.
		@param ST_CantidadArticulos ST_CantidadArticulos	  */
	public void setST_CantidadArticulos (int ST_CantidadArticulos)
	{
		set_Value (COLUMNNAME_ST_CantidadArticulos, Integer.valueOf(ST_CantidadArticulos));
	}

	/** Get ST_CantidadArticulos.
		@return ST_CantidadArticulos	  */
	public int getST_CantidadArticulos () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ST_CantidadArticulos);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ST_CantidadLineas.
		@param ST_CantidadLineas ST_CantidadLineas	  */
	public void setST_CantidadLineas (int ST_CantidadLineas)
	{
		set_Value (COLUMNNAME_ST_CantidadLineas, Integer.valueOf(ST_CantidadLineas));
	}

	/** Get ST_CantidadLineas.
		@return ST_CantidadLineas	  */
	public int getST_CantidadLineas () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ST_CantidadLineas);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ST_CodigoCaja.
		@param ST_CodigoCaja ST_CodigoCaja	  */
	public void setST_CodigoCaja (String ST_CodigoCaja)
	{
		set_Value (COLUMNNAME_ST_CodigoCaja, ST_CodigoCaja);
	}

	/** Get ST_CodigoCaja.
		@return ST_CodigoCaja	  */
	public String getST_CodigoCaja () 
	{
		return (String)get_Value(COLUMNNAME_ST_CodigoCaja);
	}

	/** Set ST_CodigoCajaDevolucion.
		@param ST_CodigoCajaDevolucion ST_CodigoCajaDevolucion	  */
	public void setST_CodigoCajaDevolucion (String ST_CodigoCajaDevolucion)
	{
		set_Value (COLUMNNAME_ST_CodigoCajaDevolucion, ST_CodigoCajaDevolucion);
	}

	/** Get ST_CodigoCajaDevolucion.
		@return ST_CodigoCajaDevolucion	  */
	public String getST_CodigoCajaDevolucion () 
	{
		return (String)get_Value(COLUMNNAME_ST_CodigoCajaDevolucion);
	}

	/** Set ST_CodigoCajera.
		@param ST_CodigoCajera ST_CodigoCajera	  */
	public void setST_CodigoCajera (String ST_CodigoCajera)
	{
		set_Value (COLUMNNAME_ST_CodigoCajera, ST_CodigoCajera);
	}

	/** Get ST_CodigoCajera.
		@return ST_CodigoCajera	  */
	public String getST_CodigoCajera () 
	{
		return (String)get_Value(COLUMNNAME_ST_CodigoCajera);
	}

	/** Set ST_EstadoTicket.
		@param ST_EstadoTicket ST_EstadoTicket	  */
	public void setST_EstadoTicket (String ST_EstadoTicket)
	{
		set_Value (COLUMNNAME_ST_EstadoTicket, ST_EstadoTicket);
	}

	/** Get ST_EstadoTicket.
		@return ST_EstadoTicket	  */
	public String getST_EstadoTicket () 
	{
		return (String)get_Value(COLUMNNAME_ST_EstadoTicket);
	}

	/** Set ST_IdentificadorLinea.
		@param ST_IdentificadorLinea ST_IdentificadorLinea	  */
	public void setST_IdentificadorLinea (String ST_IdentificadorLinea)
	{
		set_Value (COLUMNNAME_ST_IdentificadorLinea, ST_IdentificadorLinea);
	}

	/** Get ST_IdentificadorLinea.
		@return ST_IdentificadorLinea	  */
	public String getST_IdentificadorLinea () 
	{
		return (String)get_Value(COLUMNNAME_ST_IdentificadorLinea);
	}

	/** Set ST_NumeroTicket.
		@param ST_NumeroTicket ST_NumeroTicket	  */
	public void setST_NumeroTicket (String ST_NumeroTicket)
	{
		set_Value (COLUMNNAME_ST_NumeroTicket, ST_NumeroTicket);
	}

	/** Get ST_NumeroTicket.
		@return ST_NumeroTicket	  */
	public String getST_NumeroTicket () 
	{
		return (String)get_Value(COLUMNNAME_ST_NumeroTicket);
	}

	/** Set ST_NumeroTicketDevolucion.
		@param ST_NumeroTicketDevolucion ST_NumeroTicketDevolucion	  */
	public void setST_NumeroTicketDevolucion (String ST_NumeroTicketDevolucion)
	{
		set_Value (COLUMNNAME_ST_NumeroTicketDevolucion, ST_NumeroTicketDevolucion);
	}

	/** Get ST_NumeroTicketDevolucion.
		@return ST_NumeroTicketDevolucion	  */
	public String getST_NumeroTicketDevolucion () 
	{
		return (String)get_Value(COLUMNNAME_ST_NumeroTicketDevolucion);
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

	/** Set ST_TimestampTicket.
		@param ST_TimestampTicket ST_TimestampTicket	  */
	public void setST_TimestampTicket (String ST_TimestampTicket)
	{
		set_Value (COLUMNNAME_ST_TimestampTicket, ST_TimestampTicket);
	}

	/** Get ST_TimestampTicket.
		@return ST_TimestampTicket	  */
	public String getST_TimestampTicket () 
	{
		return (String)get_Value(COLUMNNAME_ST_TimestampTicket);
	}

	/** Set ST_TipoCliente.
		@param ST_TipoCliente ST_TipoCliente	  */
	public void setST_TipoCliente (String ST_TipoCliente)
	{
		set_Value (COLUMNNAME_ST_TipoCliente, ST_TipoCliente);
	}

	/** Get ST_TipoCliente.
		@return ST_TipoCliente	  */
	public String getST_TipoCliente () 
	{
		return (String)get_Value(COLUMNNAME_ST_TipoCliente);
	}

	/** Set ST_TotalAPagar.
		@param ST_TotalAPagar ST_TotalAPagar	  */
	public void setST_TotalAPagar (BigDecimal ST_TotalAPagar)
	{
		set_Value (COLUMNNAME_ST_TotalAPagar, ST_TotalAPagar);
	}

	/** Get ST_TotalAPagar.
		@return ST_TotalAPagar	  */
	public BigDecimal getST_TotalAPagar () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalAPagar);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Z_Sisteco_TK_CVta ID.
		@param Z_Sisteco_TK_CVta_ID Z_Sisteco_TK_CVta ID	  */
	public void setZ_Sisteco_TK_CVta_ID (int Z_Sisteco_TK_CVta_ID)
	{
		if (Z_Sisteco_TK_CVta_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_Sisteco_TK_CVta_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_Sisteco_TK_CVta_ID, Integer.valueOf(Z_Sisteco_TK_CVta_ID));
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
}