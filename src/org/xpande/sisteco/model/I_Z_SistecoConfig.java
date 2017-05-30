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

/** Generated Interface for Z_SistecoConfig
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_Z_SistecoConfig 
{

    /** TableName=Z_SistecoConfig */
    public static final String Table_Name = "Z_SistecoConfig";

    /** AD_Table_ID=1000000 */
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

    /** Column name PrefijoArchivoPazos */
    public static final String COLUMNNAME_PrefijoArchivoPazos = "PrefijoArchivoPazos";

	/** Set PrefijoArchivoPazos	  */
	public void setPrefijoArchivoPazos(String PrefijoArchivoPazos);

	/** Get PrefijoArchivoPazos	  */
	public String getPrefijoArchivoPazos();

    /** Column name RutaDestinoSistema */
    public static final String COLUMNNAME_RutaDestinoSistema = "RutaDestinoSistema";

	/** Set RutaDestinoSistema	  */
	public void setRutaDestinoSistema(String RutaDestinoSistema);

	/** Get RutaDestinoSistema	  */
	public String getRutaDestinoSistema();

    /** Column name RutaHistoricoPazos */
    public static final String COLUMNNAME_RutaHistoricoPazos = "RutaHistoricoPazos";

	/** Set RutaHistoricoPazos	  */
	public void setRutaHistoricoPazos(String RutaHistoricoPazos);

	/** Get RutaHistoricoPazos	  */
	public String getRutaHistoricoPazos();

    /** Column name RutaOrigenPazos */
    public static final String COLUMNNAME_RutaOrigenPazos = "RutaOrigenPazos";

	/** Set RutaOrigenPazos	  */
	public void setRutaOrigenPazos(String RutaOrigenPazos);

	/** Get RutaOrigenPazos	  */
	public String getRutaOrigenPazos();

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

    /** Column name Z_SistecoConfig_ID */
    public static final String COLUMNNAME_Z_SistecoConfig_ID = "Z_SistecoConfig_ID";

	/** Set Z_SistecoConfig ID	  */
	public void setZ_SistecoConfig_ID(int Z_SistecoConfig_ID);

	/** Get Z_SistecoConfig ID	  */
	public int getZ_SistecoConfig_ID();
}
