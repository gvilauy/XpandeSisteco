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

/** Generated Model for Z_SistecoPazosTKRUT
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_SistecoPazosTKRUT extends PO implements I_Z_SistecoPazosTKRUT, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200722L;

    /** Standard Constructor */
    public X_Z_SistecoPazosTKRUT (Properties ctx, int Z_SistecoPazosTKRUT_ID, String trxName)
    {
      super (ctx, Z_SistecoPazosTKRUT_ID, trxName);
      /** if (Z_SistecoPazosTKRUT_ID == 0)
        {
			setZ_SistecoInterfacePazos_ID (0);
			setZ_SistecoPazosTKRUT_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_SistecoPazosTKRUT (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_SistecoPazosTKRUT[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set AmtSubtotal.
		@param AmtSubtotal 
		Subtotales para no mostrar impuestos incluídos
	  */
	public void setAmtSubtotal (BigDecimal AmtSubtotal)
	{
		set_Value (COLUMNNAME_AmtSubtotal, AmtSubtotal);
	}

	/** Get AmtSubtotal.
		@return Subtotales para no mostrar impuestos incluídos
	  */
	public BigDecimal getAmtSubtotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtSubtotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public org.eevolution.model.I_C_TaxGroup getC_TaxGroup() throws RuntimeException
    {
		return (org.eevolution.model.I_C_TaxGroup)MTable.get(getCtx(), org.eevolution.model.I_C_TaxGroup.Table_Name)
			.getPO(getC_TaxGroup_ID(), get_TrxName());	}

	/** Set Tax Group.
		@param C_TaxGroup_ID Tax Group	  */
	public void setC_TaxGroup_ID (int C_TaxGroup_ID)
	{
		if (C_TaxGroup_ID < 1) 
			set_Value (COLUMNNAME_C_TaxGroup_ID, null);
		else 
			set_Value (COLUMNNAME_C_TaxGroup_ID, Integer.valueOf(C_TaxGroup_ID));
	}

	/** Get Tax Group.
		@return Tax Group	  */
	public int getC_TaxGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_TaxGroup_ID);
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

	/** Set DocumentNoRef.
		@param DocumentNoRef 
		Numero de documento referenciado
	  */
	public void setDocumentNoRef (String DocumentNoRef)
	{
		set_Value (COLUMNNAME_DocumentNoRef, DocumentNoRef);
	}

	/** Get DocumentNoRef.
		@return Numero de documento referenciado
	  */
	public String getDocumentNoRef () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNoRef);
	}

	/** Set Payment amount.
		@param PayAmt 
		Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt)
	{
		set_Value (COLUMNNAME_PayAmt, PayAmt);
	}

	/** Get Payment amount.
		@return Amount being paid
	  */
	public BigDecimal getPayAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PayAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ST_DocumentoReceptor.
		@param ST_DocumentoReceptor ST_DocumentoReceptor	  */
	public void setST_DocumentoReceptor (String ST_DocumentoReceptor)
	{
		set_Value (COLUMNNAME_ST_DocumentoReceptor, ST_DocumentoReceptor);
	}

	/** Get ST_DocumentoReceptor.
		@return ST_DocumentoReceptor	  */
	public String getST_DocumentoReceptor () 
	{
		return (String)get_Value(COLUMNNAME_ST_DocumentoReceptor);
	}

	/** Set ST_NombreReceptor.
		@param ST_NombreReceptor ST_NombreReceptor	  */
	public void setST_NombreReceptor (String ST_NombreReceptor)
	{
		set_Value (COLUMNNAME_ST_NombreReceptor, ST_NombreReceptor);
	}

	/** Get ST_NombreReceptor.
		@return ST_NombreReceptor	  */
	public String getST_NombreReceptor () 
	{
		return (String)get_Value(COLUMNNAME_ST_NombreReceptor);
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

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Amount.
		@param TotalAmt 
		Total Amount
	  */
	public void setTotalAmt (BigDecimal TotalAmt)
	{
		set_Value (COLUMNNAME_TotalAmt, TotalAmt);
	}

	/** Get Total Amount.
		@return Total Amount
	  */
	public BigDecimal getTotalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalAmt);
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

	/** Set Z_SistecoPazosTKRUT ID.
		@param Z_SistecoPazosTKRUT_ID Z_SistecoPazosTKRUT ID	  */
	public void setZ_SistecoPazosTKRUT_ID (int Z_SistecoPazosTKRUT_ID)
	{
		if (Z_SistecoPazosTKRUT_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoPazosTKRUT_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_SistecoPazosTKRUT_ID, Integer.valueOf(Z_SistecoPazosTKRUT_ID));
	}

	/** Get Z_SistecoPazosTKRUT ID.
		@return Z_SistecoPazosTKRUT ID	  */
	public int getZ_SistecoPazosTKRUT_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_SistecoPazosTKRUT_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}