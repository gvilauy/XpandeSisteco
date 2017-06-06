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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for Z_SistecoPazosTotal
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoPazosTotal extends PO implements I_Z_SistecoPazosTotal, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170606L;

    /** Standard Constructor */
    public X_Z_SistecoPazosTotal (Properties ctx, int Z_SistecoPazosTotal_ID, String trxName)
    {
      super (ctx, Z_SistecoPazosTotal_ID, trxName);
      /** if (Z_SistecoPazosTotal_ID == 0)
        {
			setZ_SistecoInterfacePazos_ID (0);
			setZ_SistecoPazosTotal_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoPazosTotal (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_SistecoPazosTotal[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set ST_TotalDevEnvases.
		@param ST_TotalDevEnvases ST_TotalDevEnvases	  */
	public void setST_TotalDevEnvases (BigDecimal ST_TotalDevEnvases)
	{
		set_Value (COLUMNNAME_ST_TotalDevEnvases, ST_TotalDevEnvases);
	}

	/** Get ST_TotalDevEnvases.
		@return ST_TotalDevEnvases	  */
	public BigDecimal getST_TotalDevEnvases () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalDevEnvases);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalEdenred.
		@param ST_TotalEdenred ST_TotalEdenred	  */
	public void setST_TotalEdenred (BigDecimal ST_TotalEdenred)
	{
		set_Value (COLUMNNAME_ST_TotalEdenred, ST_TotalEdenred);
	}

	/** Get ST_TotalEdenred.
		@return ST_TotalEdenred	  */
	public BigDecimal getST_TotalEdenred () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalEdenred);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalFacturas.
		@param ST_TotalFacturas ST_TotalFacturas	  */
	public void setST_TotalFacturas (BigDecimal ST_TotalFacturas)
	{
		set_Value (COLUMNNAME_ST_TotalFacturas, ST_TotalFacturas);
	}

	/** Get ST_TotalFacturas.
		@return ST_TotalFacturas	  */
	public BigDecimal getST_TotalFacturas () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalFacturas);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalFondeo.
		@param ST_TotalFondeo ST_TotalFondeo	  */
	public void setST_TotalFondeo (BigDecimal ST_TotalFondeo)
	{
		set_Value (COLUMNNAME_ST_TotalFondeo, ST_TotalFondeo);
	}

	/** Get ST_TotalFondeo.
		@return ST_TotalFondeo	  */
	public BigDecimal getST_TotalFondeo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalFondeo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalFondeoUSD.
		@param ST_TotalFondeoUSD ST_TotalFondeoUSD	  */
	public void setST_TotalFondeoUSD (BigDecimal ST_TotalFondeoUSD)
	{
		set_Value (COLUMNNAME_ST_TotalFondeoUSD, ST_TotalFondeoUSD);
	}

	/** Get ST_TotalFondeoUSD.
		@return ST_TotalFondeoUSD	  */
	public BigDecimal getST_TotalFondeoUSD () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalFondeoUSD);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalRetiros.
		@param ST_TotalRetiros ST_TotalRetiros	  */
	public void setST_TotalRetiros (BigDecimal ST_TotalRetiros)
	{
		set_Value (COLUMNNAME_ST_TotalRetiros, ST_TotalRetiros);
	}

	/** Get ST_TotalRetiros.
		@return ST_TotalRetiros	  */
	public BigDecimal getST_TotalRetiros () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalRetiros);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalServicios.
		@param ST_TotalServicios ST_TotalServicios	  */
	public void setST_TotalServicios (BigDecimal ST_TotalServicios)
	{
		set_Value (COLUMNNAME_ST_TotalServicios, ST_TotalServicios);
	}

	/** Get ST_TotalServicios.
		@return ST_TotalServicios	  */
	public BigDecimal getST_TotalServicios () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalServicios);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaCheque.
		@param ST_TotalVtaCheque ST_TotalVtaCheque	  */
	public void setST_TotalVtaCheque (BigDecimal ST_TotalVtaCheque)
	{
		set_Value (COLUMNNAME_ST_TotalVtaCheque, ST_TotalVtaCheque);
	}

	/** Get ST_TotalVtaCheque.
		@return ST_TotalVtaCheque	  */
	public BigDecimal getST_TotalVtaCheque () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaCheque);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaChequeUSD.
		@param ST_TotalVtaChequeUSD ST_TotalVtaChequeUSD	  */
	public void setST_TotalVtaChequeUSD (BigDecimal ST_TotalVtaChequeUSD)
	{
		set_Value (COLUMNNAME_ST_TotalVtaChequeUSD, ST_TotalVtaChequeUSD);
	}

	/** Get ST_TotalVtaChequeUSD.
		@return ST_TotalVtaChequeUSD	  */
	public BigDecimal getST_TotalVtaChequeUSD () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaChequeUSD);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaClientes.
		@param ST_TotalVtaClientes ST_TotalVtaClientes	  */
	public void setST_TotalVtaClientes (BigDecimal ST_TotalVtaClientes)
	{
		set_Value (COLUMNNAME_ST_TotalVtaClientes, ST_TotalVtaClientes);
	}

	/** Get ST_TotalVtaClientes.
		@return ST_TotalVtaClientes	  */
	public BigDecimal getST_TotalVtaClientes () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaClientes);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaCredito.
		@param ST_TotalVtaCredito ST_TotalVtaCredito	  */
	public void setST_TotalVtaCredito (BigDecimal ST_TotalVtaCredito)
	{
		set_Value (COLUMNNAME_ST_TotalVtaCredito, ST_TotalVtaCredito);
	}

	/** Get ST_TotalVtaCredito.
		@return ST_TotalVtaCredito	  */
	public BigDecimal getST_TotalVtaCredito () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaCredito);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaCreditoUSD.
		@param ST_TotalVtaCreditoUSD ST_TotalVtaCreditoUSD	  */
	public void setST_TotalVtaCreditoUSD (BigDecimal ST_TotalVtaCreditoUSD)
	{
		set_Value (COLUMNNAME_ST_TotalVtaCreditoUSD, ST_TotalVtaCreditoUSD);
	}

	/** Get ST_TotalVtaCreditoUSD.
		@return ST_TotalVtaCreditoUSD	  */
	public BigDecimal getST_TotalVtaCreditoUSD () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaCreditoUSD);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaEfectivo.
		@param ST_TotalVtaEfectivo ST_TotalVtaEfectivo	  */
	public void setST_TotalVtaEfectivo (BigDecimal ST_TotalVtaEfectivo)
	{
		set_Value (COLUMNNAME_ST_TotalVtaEfectivo, ST_TotalVtaEfectivo);
	}

	/** Get ST_TotalVtaEfectivo.
		@return ST_TotalVtaEfectivo	  */
	public BigDecimal getST_TotalVtaEfectivo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaEfectivo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaEfectivoUSD.
		@param ST_TotalVtaEfectivoUSD ST_TotalVtaEfectivoUSD	  */
	public void setST_TotalVtaEfectivoUSD (BigDecimal ST_TotalVtaEfectivoUSD)
	{
		set_Value (COLUMNNAME_ST_TotalVtaEfectivoUSD, ST_TotalVtaEfectivoUSD);
	}

	/** Get ST_TotalVtaEfectivoUSD.
		@return ST_TotalVtaEfectivoUSD	  */
	public BigDecimal getST_TotalVtaEfectivoUSD () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaEfectivoUSD);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaLuncheon.
		@param ST_TotalVtaLuncheon ST_TotalVtaLuncheon	  */
	public void setST_TotalVtaLuncheon (BigDecimal ST_TotalVtaLuncheon)
	{
		set_Value (COLUMNNAME_ST_TotalVtaLuncheon, ST_TotalVtaLuncheon);
	}

	/** Get ST_TotalVtaLuncheon.
		@return ST_TotalVtaLuncheon	  */
	public BigDecimal getST_TotalVtaLuncheon () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaLuncheon);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaSodexo.
		@param ST_TotalVtaSodexo ST_TotalVtaSodexo	  */
	public void setST_TotalVtaSodexo (BigDecimal ST_TotalVtaSodexo)
	{
		set_Value (COLUMNNAME_ST_TotalVtaSodexo, ST_TotalVtaSodexo);
	}

	/** Get ST_TotalVtaSodexo.
		@return ST_TotalVtaSodexo	  */
	public BigDecimal getST_TotalVtaSodexo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaSodexo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaTarjeta.
		@param ST_TotalVtaTarjeta ST_TotalVtaTarjeta	  */
	public void setST_TotalVtaTarjeta (BigDecimal ST_TotalVtaTarjeta)
	{
		set_Value (COLUMNNAME_ST_TotalVtaTarjeta, ST_TotalVtaTarjeta);
	}

	/** Get ST_TotalVtaTarjeta.
		@return ST_TotalVtaTarjeta	  */
	public BigDecimal getST_TotalVtaTarjeta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaTarjeta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaTarjetaCuota.
		@param ST_TotalVtaTarjetaCuota ST_TotalVtaTarjetaCuota	  */
	public void setST_TotalVtaTarjetaCuota (BigDecimal ST_TotalVtaTarjetaCuota)
	{
		set_Value (COLUMNNAME_ST_TotalVtaTarjetaCuota, ST_TotalVtaTarjetaCuota);
	}

	/** Get ST_TotalVtaTarjetaCuota.
		@return ST_TotalVtaTarjetaCuota	  */
	public BigDecimal getST_TotalVtaTarjetaCuota () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaTarjetaCuota);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaTarjetaManual.
		@param ST_TotalVtaTarjetaManual ST_TotalVtaTarjetaManual	  */
	public void setST_TotalVtaTarjetaManual (BigDecimal ST_TotalVtaTarjetaManual)
	{
		set_Value (COLUMNNAME_ST_TotalVtaTarjetaManual, ST_TotalVtaTarjetaManual);
	}

	/** Get ST_TotalVtaTarjetaManual.
		@return ST_TotalVtaTarjetaManual	  */
	public BigDecimal getST_TotalVtaTarjetaManual () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaTarjetaManual);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_TotalVtaTarjetaUSD.
		@param ST_TotalVtaTarjetaUSD ST_TotalVtaTarjetaUSD	  */
	public void setST_TotalVtaTarjetaUSD (BigDecimal ST_TotalVtaTarjetaUSD)
	{
		set_Value (COLUMNNAME_ST_TotalVtaTarjetaUSD, ST_TotalVtaTarjetaUSD);
	}

	/** Get ST_TotalVtaTarjetaUSD.
		@return ST_TotalVtaTarjetaUSD	  */
	public BigDecimal getST_TotalVtaTarjetaUSD () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_TotalVtaTarjetaUSD);
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

	/** Set Z_SistecoPazosTotal ID.
		@param Z_SistecoPazosTotal_ID Z_SistecoPazosTotal ID	  */
	public void setZ_SistecoPazosTotal_ID (int Z_SistecoPazosTotal_ID)
	{
		if (Z_SistecoPazosTotal_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoPazosTotal_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoPazosTotal_ID, Integer.valueOf(Z_SistecoPazosTotal_ID));
	}

	/** Get Z_SistecoPazosTotal ID.
		@return Z_SistecoPazosTotal ID	  */
	public int getZ_SistecoPazosTotal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_SistecoPazosTotal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}