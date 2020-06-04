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

/** Generated Interface for Z_SistecoVtaCtaCte
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_Z_SistecoVtaCtaCte 
{

    /** TableName=Z_SistecoVtaCtaCte */
    public static final String Table_Name = "Z_SistecoVtaCtaCte";

    /** AD_Table_ID=1000348 */
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

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

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

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public I_C_Invoice getC_Invoice() throws RuntimeException;

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

    /** Column name ST_DescripcionCFE */
    public static final String COLUMNNAME_ST_DescripcionCFE = "ST_DescripcionCFE";

	/** Set ST_DescripcionCFE	  */
	public void setST_DescripcionCFE (String ST_DescripcionCFE);

	/** Get ST_DescripcionCFE	  */
	public String getST_DescripcionCFE();

    /** Column name ST_Importe */
    public static final String COLUMNNAME_ST_Importe = "ST_Importe";

	/** Set ST_Importe	  */
	public void setST_Importe (BigDecimal ST_Importe);

	/** Get ST_Importe	  */
	public BigDecimal getST_Importe();

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

    /** Column name TaxID */
    public static final String COLUMNNAME_TaxID = "TaxID";

	/** Set Tax ID.
	  * Tax Identification
	  */
	public void setTaxID (String TaxID);

	/** Get Tax ID.
	  * Tax Identification
	  */
	public String getTaxID();

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

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Z_SistecoInterfacePazos_ID */
    public static final String COLUMNNAME_Z_SistecoInterfacePazos_ID = "Z_SistecoInterfacePazos_ID";

	/** Set Z_SistecoInterfacePazos ID	  */
	public void setZ_SistecoInterfacePazos_ID (int Z_SistecoInterfacePazos_ID);

	/** Get Z_SistecoInterfacePazos ID	  */
	public int getZ_SistecoInterfacePazos_ID();

	public I_Z_SistecoInterfacePazos getZ_SistecoInterfacePazos() throws RuntimeException;

    /** Column name Z_Sisteco_TK_CVta_ID */
    public static final String COLUMNNAME_Z_Sisteco_TK_CVta_ID = "Z_Sisteco_TK_CVta_ID";

	/** Set Z_Sisteco_TK_CVta ID	  */
	public void setZ_Sisteco_TK_CVta_ID (int Z_Sisteco_TK_CVta_ID);

	/** Get Z_Sisteco_TK_CVta ID	  */
	public int getZ_Sisteco_TK_CVta_ID();

	public I_Z_Sisteco_TK_CVta getZ_Sisteco_TK_CVta() throws RuntimeException;

    /** Column name Z_SistecoVtaCtaCte_ID */
    public static final String COLUMNNAME_Z_SistecoVtaCtaCte_ID = "Z_SistecoVtaCtaCte_ID";

	/** Set Z_SistecoVtaCtaCte ID	  */
	public void setZ_SistecoVtaCtaCte_ID (int Z_SistecoVtaCtaCte_ID);

	/** Get Z_SistecoVtaCtaCte ID	  */
	public int getZ_SistecoVtaCtaCte_ID();
}
