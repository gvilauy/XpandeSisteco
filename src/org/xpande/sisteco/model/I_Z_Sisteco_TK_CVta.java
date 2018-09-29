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

/** Generated Interface for Z_Sisteco_TK_CVta
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_Z_Sisteco_TK_CVta 
{

    /** TableName=Z_Sisteco_TK_CVta */
    public static final String Table_Name = "Z_Sisteco_TK_CVta";

    /** AD_Table_ID=1000003 */
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

    /** Column name C_Period_ID */
    public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";

	/** Set Period.
	  * Period of the Calendar
	  */
	public void setC_Period_ID(int C_Period_ID);

	/** Get Period.
	  * Period of the Calendar
	  */
	public int getC_Period_ID();

	public I_C_Period getC_Period() throws RuntimeException;

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
	public void setDateTrx(Timestamp DateTrx);

	/** Get Transaction Date.
	  * Transaction Date
	  */
	public Timestamp getDateTrx();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported(boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

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

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed(boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name ST_CantidadArticulos */
    public static final String COLUMNNAME_ST_CantidadArticulos = "ST_CantidadArticulos";

	/** Set ST_CantidadArticulos	  */
	public void setST_CantidadArticulos(int ST_CantidadArticulos);

	/** Get ST_CantidadArticulos	  */
	public int getST_CantidadArticulos();

    /** Column name ST_CantidadLineas */
    public static final String COLUMNNAME_ST_CantidadLineas = "ST_CantidadLineas";

	/** Set ST_CantidadLineas	  */
	public void setST_CantidadLineas(int ST_CantidadLineas);

	/** Get ST_CantidadLineas	  */
	public int getST_CantidadLineas();

    /** Column name ST_CodigoCaja */
    public static final String COLUMNNAME_ST_CodigoCaja = "ST_CodigoCaja";

	/** Set ST_CodigoCaja	  */
	public void setST_CodigoCaja(String ST_CodigoCaja);

	/** Get ST_CodigoCaja	  */
	public String getST_CodigoCaja();

    /** Column name ST_CodigoCajaDevolucion */
    public static final String COLUMNNAME_ST_CodigoCajaDevolucion = "ST_CodigoCajaDevolucion";

	/** Set ST_CodigoCajaDevolucion	  */
	public void setST_CodigoCajaDevolucion(String ST_CodigoCajaDevolucion);

	/** Get ST_CodigoCajaDevolucion	  */
	public String getST_CodigoCajaDevolucion();

    /** Column name ST_CodigoCajera */
    public static final String COLUMNNAME_ST_CodigoCajera = "ST_CodigoCajera";

	/** Set ST_CodigoCajera	  */
	public void setST_CodigoCajera(String ST_CodigoCajera);

	/** Get ST_CodigoCajera	  */
	public String getST_CodigoCajera();

    /** Column name ST_EstadoTicket */
    public static final String COLUMNNAME_ST_EstadoTicket = "ST_EstadoTicket";

	/** Set ST_EstadoTicket	  */
	public void setST_EstadoTicket(String ST_EstadoTicket);

	/** Get ST_EstadoTicket	  */
	public String getST_EstadoTicket();

    /** Column name ST_IdentificadorLinea */
    public static final String COLUMNNAME_ST_IdentificadorLinea = "ST_IdentificadorLinea";

	/** Set ST_IdentificadorLinea	  */
	public void setST_IdentificadorLinea(String ST_IdentificadorLinea);

	/** Get ST_IdentificadorLinea	  */
	public String getST_IdentificadorLinea();

    /** Column name ST_NumeroTicket */
    public static final String COLUMNNAME_ST_NumeroTicket = "ST_NumeroTicket";

	/** Set ST_NumeroTicket	  */
	public void setST_NumeroTicket(String ST_NumeroTicket);

	/** Get ST_NumeroTicket	  */
	public String getST_NumeroTicket();

    /** Column name ST_NumeroTicketDevolucion */
    public static final String COLUMNNAME_ST_NumeroTicketDevolucion = "ST_NumeroTicketDevolucion";

	/** Set ST_NumeroTicketDevolucion	  */
	public void setST_NumeroTicketDevolucion(String ST_NumeroTicketDevolucion);

	/** Get ST_NumeroTicketDevolucion	  */
	public String getST_NumeroTicketDevolucion();

    /** Column name ST_PositionFile */
    public static final String COLUMNNAME_ST_PositionFile = "ST_PositionFile";

	/** Set ST_PositionFile	  */
	public void setST_PositionFile(String ST_PositionFile);

	/** Get ST_PositionFile	  */
	public String getST_PositionFile();

    /** Column name ST_TimestampTicket */
    public static final String COLUMNNAME_ST_TimestampTicket = "ST_TimestampTicket";

	/** Set ST_TimestampTicket	  */
	public void setST_TimestampTicket(String ST_TimestampTicket);

	/** Get ST_TimestampTicket	  */
	public String getST_TimestampTicket();

    /** Column name ST_TipoCliente */
    public static final String COLUMNNAME_ST_TipoCliente = "ST_TipoCliente";

	/** Set ST_TipoCliente	  */
	public void setST_TipoCliente(String ST_TipoCliente);

	/** Get ST_TipoCliente	  */
	public String getST_TipoCliente();

    /** Column name ST_TotalAPagar */
    public static final String COLUMNNAME_ST_TotalAPagar = "ST_TotalAPagar";

	/** Set ST_TotalAPagar	  */
	public void setST_TotalAPagar(BigDecimal ST_TotalAPagar);

	/** Get ST_TotalAPagar	  */
	public BigDecimal getST_TotalAPagar();

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
}
