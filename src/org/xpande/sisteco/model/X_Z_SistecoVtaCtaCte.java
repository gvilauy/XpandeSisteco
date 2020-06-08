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

/** Generated Model for Z_SistecoVtaCtaCte
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoVtaCtaCte extends PO implements I_Z_SistecoVtaCtaCte, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200608L;

    /** Standard Constructor */
    public X_Z_SistecoVtaCtaCte (Properties ctx, int Z_SistecoVtaCtaCte_ID, String trxName)
    {
      super (ctx, Z_SistecoVtaCtaCte_ID, trxName);
      /** if (Z_SistecoVtaCtaCte_ID == 0)
        {
			setZ_SistecoInterfacePazos_ID (0);
			setZ_Sisteco_TK_CVta_ID (0);
			setZ_SistecoVtaCtaCte_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoVtaCtaCte (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_SistecoVtaCtaCte[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (I_C_BPartner)MTable.get(getCtx(), I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException
    {
		return (I_C_BPartner_Location)MTable.get(getCtx(), I_C_BPartner_Location.Table_Name)
			.getPO(getC_BPartner_Location_ID(), get_TrxName());	}

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Currency getC_Currency() throws RuntimeException
    {
		return (I_C_Currency)MTable.get(getCtx(), I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_DocType getC_DocType() throws RuntimeException
    {
		return (I_C_DocType)MTable.get(getCtx(), I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (I_C_Invoice)MTable.get(getCtx(), I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
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

	/** Set ST_Caja.
		@param ST_Caja ST_Caja	  */
	public void setST_Caja (String ST_Caja)
	{
		set_Value (COLUMNNAME_ST_Caja, ST_Caja);
	}

	/** Get ST_Caja.
		@return ST_Caja	  */
	public String getST_Caja () 
	{
		return (String)get_Value(COLUMNNAME_ST_Caja);
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

	/** Set ST_DescripcionCFE.
		@param ST_DescripcionCFE ST_DescripcionCFE	  */
	public void setST_DescripcionCFE (String ST_DescripcionCFE)
	{
		set_Value (COLUMNNAME_ST_DescripcionCFE, ST_DescripcionCFE);
	}

	/** Get ST_DescripcionCFE.
		@return ST_DescripcionCFE	  */
	public String getST_DescripcionCFE () 
	{
		return (String)get_Value(COLUMNNAME_ST_DescripcionCFE);
	}

	/** Set ST_Importe.
		@param ST_Importe ST_Importe	  */
	public void setST_Importe (BigDecimal ST_Importe)
	{
		set_Value (COLUMNNAME_ST_Importe, ST_Importe);
	}

	/** Get ST_Importe.
		@return ST_Importe	  */
	public BigDecimal getST_Importe () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ST_Importe);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_NumeroCFE.
		@param ST_NumeroCFE ST_NumeroCFE	  */
	public void setST_NumeroCFE (String ST_NumeroCFE)
	{
		set_Value (COLUMNNAME_ST_NumeroCFE, ST_NumeroCFE);
	}

	/** Get ST_NumeroCFE.
		@return ST_NumeroCFE	  */
	public String getST_NumeroCFE () 
	{
		return (String)get_Value(COLUMNNAME_ST_NumeroCFE);
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

	/** Set ST_SerieCFE.
		@param ST_SerieCFE ST_SerieCFE	  */
	public void setST_SerieCFE (String ST_SerieCFE)
	{
		set_Value (COLUMNNAME_ST_SerieCFE, ST_SerieCFE);
	}

	/** Get ST_SerieCFE.
		@return ST_SerieCFE	  */
	public String getST_SerieCFE () 
	{
		return (String)get_Value(COLUMNNAME_ST_SerieCFE);
	}

	/** Set ST_TipoCFE.
		@param ST_TipoCFE ST_TipoCFE	  */
	public void setST_TipoCFE (String ST_TipoCFE)
	{
		set_Value (COLUMNNAME_ST_TipoCFE, ST_TipoCFE);
	}

	/** Get ST_TipoCFE.
		@return ST_TipoCFE	  */
	public String getST_TipoCFE () 
	{
		return (String)get_Value(COLUMNNAME_ST_TipoCFE);
	}

	/** Set Tax ID.
		@param TaxID 
		Tax Identification
	  */
	public void setTaxID (String TaxID)
	{
		set_Value (COLUMNNAME_TaxID, TaxID);
	}

	/** Get Tax ID.
		@return Tax Identification
	  */
	public String getTaxID () 
	{
		return (String)get_Value(COLUMNNAME_TaxID);
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

	/** Set Z_SistecoVtaCtaCte ID.
		@param Z_SistecoVtaCtaCte_ID Z_SistecoVtaCtaCte ID	  */
	public void setZ_SistecoVtaCtaCte_ID (int Z_SistecoVtaCtaCte_ID)
	{
		if (Z_SistecoVtaCtaCte_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoVtaCtaCte_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoVtaCtaCte_ID, Integer.valueOf(Z_SistecoVtaCtaCte_ID));
	}

	/** Get Z_SistecoVtaCtaCte ID.
		@return Z_SistecoVtaCtaCte ID	  */
	public int getZ_SistecoVtaCtaCte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_SistecoVtaCtaCte_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}