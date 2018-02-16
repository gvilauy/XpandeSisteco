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

/** Generated Interface for Z_SistecoInterfaceOut
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_Z_SistecoInterfaceOut 
{

    /** TableName=Z_SistecoInterfaceOut */
    public static final String Table_Name = "Z_SistecoInterfaceOut";

    /** AD_Table_ID=1000065 */
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
	public void setAD_Org_ID(int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID(int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID(int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

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

    /** Column name CRUDType */
    public static final String COLUMNNAME_CRUDType = "CRUDType";

	/** Set CRUDType.
	  * Tipo de acción de alta, baja, modificacion o lectura de registro en Base de Datos
	  */
	public void setCRUDType(String CRUDType);

	/** Get CRUDType.
	  * Tipo de acción de alta, baja, modificacion o lectura de registro en Base de Datos
	  */
	public String getCRUDType();

    /** Column name DateExecuted */
    public static final String COLUMNNAME_DateExecuted = "DateExecuted";

	/** Set DateExecuted.
	  * Fecha ejecutado
	  */
	public void setDateExecuted(Timestamp DateExecuted);

	/** Get DateExecuted.
	  * Fecha ejecutado
	  */
	public Timestamp getDateExecuted();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription(String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate(Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name ErrorMsg */
    public static final String COLUMNNAME_ErrorMsg = "ErrorMsg";

	/** Set Error Msg	  */
	public void setErrorMsg(String ErrorMsg);

	/** Get Error Msg	  */
	public String getErrorMsg();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive(boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsError */
    public static final String COLUMNNAME_IsError = "IsError";

	/** Set Error.
	  * An Error occurred in the execution
	  */
	public void setIsError(boolean IsError);

	/** Get Error.
	  * An Error occurred in the execution
	  */
	public boolean isError();

    /** Column name IsExecuted */
    public static final String COLUMNNAME_IsExecuted = "IsExecuted";

	/** Set IsExecuted	  */
	public void setIsExecuted(boolean IsExecuted);

	/** Get IsExecuted	  */
	public boolean isExecuted();

    /** Column name IsPriceChanged */
    public static final String COLUMNNAME_IsPriceChanged = "IsPriceChanged";

	/** Set IsPriceChanged.
	  * El precio fue modificado
	  */
	public void setIsPriceChanged(boolean IsPriceChanged);

	/** Get IsPriceChanged.
	  * El precio fue modificado
	  */
	public boolean isPriceChanged();

    /** Column name IsTandemChanged */
    public static final String COLUMNNAME_IsTandemChanged = "IsTandemChanged";

	/** Set IsTandemChanged.
	  * Si se modifico asociación de Tandem en un Producto para Reatil
	  */
	public void setIsTandemChanged(boolean IsTandemChanged);

	/** Get IsTandemChanged.
	  * Si se modifico asociación de Tandem en un Producto para Reatil
	  */
	public boolean isTandemChanged();

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID(int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

	public I_M_PriceList getM_PriceList() throws RuntimeException;

    /** Column name M_Product_Tandem_ID */
    public static final String COLUMNNAME_M_Product_Tandem_ID = "M_Product_Tandem_ID";

	/** Set M_Product_Tandem_ID.
	  * Producto Tandem que se asocia a otro producto en Retail
	  */
	public void setM_Product_Tandem_ID(int M_Product_Tandem_ID);

	/** Get M_Product_Tandem_ID.
	  * Producto Tandem que se asocia a otro producto en Retail
	  */
	public int getM_Product_Tandem_ID();

	public I_M_Product getM_Product_Tandem() throws RuntimeException;

    /** Column name PriceSO */
    public static final String COLUMNNAME_PriceSO = "PriceSO";

	/** Set PriceSO.
	  * PriceSO
	  */
	public void setPriceSO(BigDecimal PriceSO);

	/** Get PriceSO.
	  * PriceSO
	  */
	public BigDecimal getPriceSO();

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID(int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo(int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate(Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

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

    /** Column name WithOfferSO */
    public static final String COLUMNNAME_WithOfferSO = "WithOfferSO";

	/** Set WithOfferSO.
	  * Si tiene o no oferta en precio de venta en Retail
	  */
	public void setWithOfferSO(boolean WithOfferSO);

	/** Get WithOfferSO.
	  * Si tiene o no oferta en precio de venta en Retail
	  */
	public boolean isWithOfferSO();

    /** Column name Z_ComunicacionPOS_ID */
    public static final String COLUMNNAME_Z_ComunicacionPOS_ID = "Z_ComunicacionPOS_ID";

	/** Set Z_ComunicacionPOS ID	  */
	public void setZ_ComunicacionPOS_ID(int Z_ComunicacionPOS_ID);

	/** Get Z_ComunicacionPOS ID	  */
	public int getZ_ComunicacionPOS_ID();

    /** Column name Z_SistecoInterfaceOut_ID */
    public static final String COLUMNNAME_Z_SistecoInterfaceOut_ID = "Z_SistecoInterfaceOut_ID";

	/** Set Z_SistecoInterfaceOut ID	  */
	public void setZ_SistecoInterfaceOut_ID(int Z_SistecoInterfaceOut_ID);

	/** Get Z_SistecoInterfaceOut ID	  */
	public int getZ_SistecoInterfaceOut_ID();
}
