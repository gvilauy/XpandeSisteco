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

/** Generated Interface for Z_Sisteco_TK_LineaError
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_Z_Sisteco_TK_LineaError 
{

    /** TableName=Z_Sisteco_TK_LineaError */
    public static final String Table_Name = "Z_Sisteco_TK_LineaError";

    /** AD_Table_ID=1000028 */
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

    /** Column name FileName */
    public static final String COLUMNNAME_FileName = "FileName";

	/** Set File Name.
	  * Name of the local file or URL
	  */
	public void setFileName(String FileName);

	/** Get File Name.
	  * Name of the local file or URL
	  */
	public String getFileName();

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

    /** Column name ST_LineaArchivo */
    public static final String COLUMNNAME_ST_LineaArchivo = "ST_LineaArchivo";

	/** Set ST_LineaArchivo	  */
	public void setST_LineaArchivo(String ST_LineaArchivo);

	/** Get ST_LineaArchivo	  */
	public String getST_LineaArchivo();

    /** Column name ST_PositionFile */
    public static final String COLUMNNAME_ST_PositionFile = "ST_PositionFile";

	/** Set ST_PositionFile	  */
	public void setST_PositionFile(String ST_PositionFile);

	/** Get ST_PositionFile	  */
	public String getST_PositionFile();

    /** Column name ST_TipoLinea */
    public static final String COLUMNNAME_ST_TipoLinea = "ST_TipoLinea";

	/** Set ST_TipoLinea	  */
	public void setST_TipoLinea(String ST_TipoLinea);

	/** Get ST_TipoLinea	  */
	public String getST_TipoLinea();

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
	public void setZ_SistecoInterfacePazos_ID(int Z_SistecoInterfacePazos_ID);

	/** Get Z_SistecoInterfacePazos ID	  */
	public int getZ_SistecoInterfacePazos_ID();

	public I_Z_SistecoInterfacePazos getZ_SistecoInterfacePazos() throws RuntimeException;

    /** Column name Z_Sisteco_TK_CVta_ID */
    public static final String COLUMNNAME_Z_Sisteco_TK_CVta_ID = "Z_Sisteco_TK_CVta_ID";

	/** Set Z_Sisteco_TK_CVta ID	  */
	public void setZ_Sisteco_TK_CVta_ID(int Z_Sisteco_TK_CVta_ID);

	/** Get Z_Sisteco_TK_CVta ID	  */
	public int getZ_Sisteco_TK_CVta_ID();

	public I_Z_Sisteco_TK_CVta getZ_Sisteco_TK_CVta() throws RuntimeException;

    /** Column name Z_Sisteco_TK_LineaError_ID */
    public static final String COLUMNNAME_Z_Sisteco_TK_LineaError_ID = "Z_Sisteco_TK_LineaError_ID";

	/** Set Z_Sisteco_TK_LineaError ID	  */
	public void setZ_Sisteco_TK_LineaError_ID(int Z_Sisteco_TK_LineaError_ID);

	/** Get Z_Sisteco_TK_LineaError ID	  */
	public int getZ_Sisteco_TK_LineaError_ID();
}
