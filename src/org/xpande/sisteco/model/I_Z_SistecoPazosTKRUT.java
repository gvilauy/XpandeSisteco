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
package org.xpande.sisteco.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for Z_SistecoPazosTKRUT
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_Z_SistecoPazosTKRUT 
{

    /** TableName=Z_SistecoPazosTKRUT */
    public static final String Table_Name = "Z_SistecoPazosTKRUT";

    /** AD_Table_ID=1000123 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AmtSubtotal */
    public static final String COLUMNNAME_AmtSubtotal = "AmtSubtotal";

	/** Set AmtSubtotal.
	  * Subtotales para no mostrar impuestos incluídos
	  */
	public void setAmtSubtotal (BigDecimal AmtSubtotal);

	/** Get AmtSubtotal.
	  * Subtotales para no mostrar impuestos incluídos
	  */
	public BigDecimal getAmtSubtotal();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name C_TaxGroup_ID */
    public static final String COLUMNNAME_C_TaxGroup_ID = "C_TaxGroup_ID";

	/** Set Tax Group	  */
	public void setC_TaxGroup_ID (int C_TaxGroup_ID);

	/** Get Tax Group	  */
	public int getC_TaxGroup_ID();

	public org.eevolution.model.I_C_TaxGroup getC_TaxGroup() throws RuntimeException;

    /** Column name DateTrx */
    public static final String COLUMNNAME_DateTrx = "DateTrx";

	/** Set Transaction Date.
	  * Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx);

	/** Get Transaction Date.
	  * Transaction Date
	  */
	public Timestamp getDateTrx();

    /** Column name DocumentNoRef */
    public static final String COLUMNNAME_DocumentNoRef = "DocumentNoRef";

	/** Set DocumentNoRef.
	  * Numero de documento referenciado
	  */
	public void setDocumentNoRef (String DocumentNoRef);

	/** Get DocumentNoRef.
	  * Numero de documento referenciado
	  */
	public String getDocumentNoRef();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name PayAmt */
    public static final String COLUMNNAME_PayAmt = "PayAmt";

	/** Set Payment amount.
	  * Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt);

	/** Get Payment amount.
	  * Amount being paid
	  */
	public BigDecimal getPayAmt();

    /** Column name ST_DocumentoReceptor */
    public static final String COLUMNNAME_ST_DocumentoReceptor = "ST_DocumentoReceptor";

	/** Set ST_DocumentoReceptor	  */
	public void setST_DocumentoReceptor (String ST_DocumentoReceptor);

	/** Get ST_DocumentoReceptor	  */
	public String getST_DocumentoReceptor();

    /** Column name ST_NombreReceptor */
    public static final String COLUMNNAME_ST_NombreReceptor = "ST_NombreReceptor";

	/** Set ST_NombreReceptor	  */
	public void setST_NombreReceptor (String ST_NombreReceptor);

	/** Get ST_NombreReceptor	  */
	public String getST_NombreReceptor();

    /** Column name ST_NumeroCFE */
    public static final String COLUMNNAME_ST_NumeroCFE = "ST_NumeroCFE";

	/** Set ST_NumeroCFE	  */
	public void setST_NumeroCFE (String ST_NumeroCFE);

	/** Get ST_NumeroCFE	  */
	public String getST_NumeroCFE();

    /** Column name ST_NumeroTicket */
    public static final String COLUMNNAME_ST_NumeroTicket = "ST_NumeroTicket";

	/** Set ST_NumeroTicket	  */
	public void setST_NumeroTicket (String ST_NumeroTicket);

	/** Get ST_NumeroTicket	  */
	public String getST_NumeroTicket();

    /** Column name ST_SerieCFE */
    public static final String COLUMNNAME_ST_SerieCFE = "ST_SerieCFE";

	/** Set ST_SerieCFE	  */
	public void setST_SerieCFE (String ST_SerieCFE);

	/** Get ST_SerieCFE	  */
	public String getST_SerieCFE();

    /** Column name ST_TipoCFE */
    public static final String COLUMNNAME_ST_TipoCFE = "ST_TipoCFE";

	/** Set ST_TipoCFE	  */
	public void setST_TipoCFE (String ST_TipoCFE);

	/** Get ST_TipoCFE	  */
	public String getST_TipoCFE();

    /** Column name TaxAmt */
    public static final String COLUMNNAME_TaxAmt = "TaxAmt";

	/** Set Tax Amount.
	  * Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt);

	/** Get Tax Amount.
	  * Tax Amount for a document
	  */
	public BigDecimal getTaxAmt();

    /** Column name TotalAmt */
    public static final String COLUMNNAME_TotalAmt = "TotalAmt";

	/** Set Total Amount.
	  * Total Amount
	  */
	public void setTotalAmt (BigDecimal TotalAmt);

	/** Get Total Amount.
	  * Total Amount
	  */
	public BigDecimal getTotalAmt();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name Z_SistecoInterfacePazos_ID */
    public static final String COLUMNNAME_Z_SistecoInterfacePazos_ID = "Z_SistecoInterfacePazos_ID";

	/** Set Z_SistecoInterfacePazos ID	  */
	public void setZ_SistecoInterfacePazos_ID (int Z_SistecoInterfacePazos_ID);

	/** Get Z_SistecoInterfacePazos ID	  */
	public int getZ_SistecoInterfacePazos_ID();

	public I_Z_SistecoInterfacePazos getZ_SistecoInterfacePazos() throws RuntimeException;

    /** Column name Z_SistecoPazosTKRUT_ID */
    public static final String COLUMNNAME_Z_SistecoPazosTKRUT_ID = "Z_SistecoPazosTKRUT_ID";

	/** Set Z_SistecoPazosTKRUT ID	  */
	public void setZ_SistecoPazosTKRUT_ID (int Z_SistecoPazosTKRUT_ID);

	/** Get Z_SistecoPazosTKRUT ID	  */
	public int getZ_SistecoPazosTKRUT_ID();
}
