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

/** Generated Interface for Z_Sisteco_TK_LDev
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_Z_Sisteco_TK_LDev 
{

    /** TableName=Z_Sisteco_TK_LDev */
    public static final String Table_Name = "Z_Sisteco_TK_LDev";

    /** AD_Table_ID=1000005 */
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

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID(int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;

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

    /** Column name ST_Cantidad */
    public static final String COLUMNNAME_ST_Cantidad = "ST_Cantidad";

	/** Set ST_Cantidad.
	  *  
	  */
	public void setST_Cantidad(BigDecimal ST_Cantidad);

	/** Get ST_Cantidad.
	  *  
	  */
	public BigDecimal getST_Cantidad();

    /** Column name ST_CodigoArticuloOriginal */
    public static final String COLUMNNAME_ST_CodigoArticuloOriginal = "ST_CodigoArticuloOriginal";

	/** Set ST_CodigoArticuloOriginal	  */
	public void setST_CodigoArticuloOriginal(String ST_CodigoArticuloOriginal);

	/** Get ST_CodigoArticuloOriginal	  */
	public String getST_CodigoArticuloOriginal();

    /** Column name ST_CodigoArtSubf */
    public static final String COLUMNNAME_ST_CodigoArtSubf = "ST_CodigoArtSubf";

	/** Set ST_CodigoArtSubf	  */
	public void setST_CodigoArtSubf(String ST_CodigoArtSubf);

	/** Get ST_CodigoArtSubf	  */
	public String getST_CodigoArtSubf();

    /** Column name ST_CodigoIVA */
    public static final String COLUMNNAME_ST_CodigoIVA = "ST_CodigoIVA";

	/** Set ST_CodigoIVA	  */
	public void setST_CodigoIVA(String ST_CodigoIVA);

	/** Get ST_CodigoIVA	  */
	public String getST_CodigoIVA();

    /** Column name ST_CodigoSupervisora */
    public static final String COLUMNNAME_ST_CodigoSupervisora = "ST_CodigoSupervisora";

	/** Set ST_CodigoSupervisora	  */
	public void setST_CodigoSupervisora(String ST_CodigoSupervisora);

	/** Get ST_CodigoSupervisora	  */
	public String getST_CodigoSupervisora();

    /** Column name ST_CodigoVendedor */
    public static final String COLUMNNAME_ST_CodigoVendedor = "ST_CodigoVendedor";

	/** Set ST_CodigoVendedor	  */
	public void setST_CodigoVendedor(String ST_CodigoVendedor);

	/** Get ST_CodigoVendedor	  */
	public String getST_CodigoVendedor();

    /** Column name ST_IndicadorArtSubf */
    public static final String COLUMNNAME_ST_IndicadorArtSubf = "ST_IndicadorArtSubf";

	/** Set ST_IndicadorArtSubf	  */
	public void setST_IndicadorArtSubf(String ST_IndicadorArtSubf);

	/** Get ST_IndicadorArtSubf	  */
	public String getST_IndicadorArtSubf();

    /** Column name ST_IVA */
    public static final String COLUMNNAME_ST_IVA = "ST_IVA";

	/** Set ST_IVA	  */
	public void setST_IVA(BigDecimal ST_IVA);

	/** Get ST_IVA	  */
	public BigDecimal getST_IVA();

    /** Column name ST_IVADescuento */
    public static final String COLUMNNAME_ST_IVADescuento = "ST_IVADescuento";

	/** Set ST_IVADescuento	  */
	public void setST_IVADescuento(BigDecimal ST_IVADescuento);

	/** Get ST_IVADescuento	  */
	public BigDecimal getST_IVADescuento();

    /** Column name ST_IvaPrecioInicial */
    public static final String COLUMNNAME_ST_IvaPrecioInicial = "ST_IvaPrecioInicial";

	/** Set ST_IvaPrecioInicial.
	  * Atributo para interface de Sisteco
	  */
	public void setST_IvaPrecioInicial(BigDecimal ST_IvaPrecioInicial);

	/** Get ST_IvaPrecioInicial.
	  * Atributo para interface de Sisteco
	  */
	public BigDecimal getST_IvaPrecioInicial();

    /** Column name ST_LineaCancelada */
    public static final String COLUMNNAME_ST_LineaCancelada = "ST_LineaCancelada";

	/** Set ST_LineaCancelada	  */
	public void setST_LineaCancelada(int ST_LineaCancelada);

	/** Get ST_LineaCancelada	  */
	public int getST_LineaCancelada();

    /** Column name ST_ModoIngreso */
    public static final String COLUMNNAME_ST_ModoIngreso = "ST_ModoIngreso";

	/** Set ST_ModoIngreso.
	  *  
	  */
	public void setST_ModoIngreso(String ST_ModoIngreso);

	/** Get ST_ModoIngreso.
	  *  
	  */
	public String getST_ModoIngreso();

    /** Column name ST_NumeroLinea */
    public static final String COLUMNNAME_ST_NumeroLinea = "ST_NumeroLinea";

	/** Set ST_NumeroLinea.
	  *  
	  */
	public void setST_NumeroLinea(String ST_NumeroLinea);

	/** Get ST_NumeroLinea.
	  *  
	  */
	public String getST_NumeroLinea();

    /** Column name ST_PositionFile */
    public static final String COLUMNNAME_ST_PositionFile = "ST_PositionFile";

	/** Set ST_PositionFile	  */
	public void setST_PositionFile(String ST_PositionFile);

	/** Get ST_PositionFile	  */
	public String getST_PositionFile();

    /** Column name ST_Precio */
    public static final String COLUMNNAME_ST_Precio = "ST_Precio";

	/** Set ST_Precio	  */
	public void setST_Precio(BigDecimal ST_Precio);

	/** Get ST_Precio	  */
	public BigDecimal getST_Precio();

    /** Column name ST_PrecioDescuento */
    public static final String COLUMNNAME_ST_PrecioDescuento = "ST_PrecioDescuento";

	/** Set ST_PrecioDescuento	  */
	public void setST_PrecioDescuento(BigDecimal ST_PrecioDescuento);

	/** Get ST_PrecioDescuento	  */
	public BigDecimal getST_PrecioDescuento();

    /** Column name ST_PrecioInicial */
    public static final String COLUMNNAME_ST_PrecioInicial = "ST_PrecioInicial";

	/** Set ST_PrecioInicial.
	  * Atributo de Sisteco para Interface
	  */
	public void setST_PrecioInicial(BigDecimal ST_PrecioInicial);

	/** Get ST_PrecioInicial.
	  * Atributo de Sisteco para Interface
	  */
	public BigDecimal getST_PrecioInicial();

    /** Column name ST_TimestampLinea */
    public static final String COLUMNNAME_ST_TimestampLinea = "ST_TimestampLinea";

	/** Set ST_TimestampLinea.
	  *  
	  */
	public void setST_TimestampLinea(String ST_TimestampLinea);

	/** Get ST_TimestampLinea.
	  *  
	  */
	public String getST_TimestampLinea();

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

    /** Column name Z_SistecoTipoLineaPazos_ID */
    public static final String COLUMNNAME_Z_SistecoTipoLineaPazos_ID = "Z_SistecoTipoLineaPazos_ID";

	/** Set Z_SistecoTipoLineaPazos ID	  */
	public void setZ_SistecoTipoLineaPazos_ID(int Z_SistecoTipoLineaPazos_ID);

	/** Get Z_SistecoTipoLineaPazos ID	  */
	public int getZ_SistecoTipoLineaPazos_ID();

	public I_Z_SistecoTipoLineaPazos getZ_SistecoTipoLineaPazos() throws RuntimeException;

    /** Column name Z_Sisteco_TK_CVta_ID */
    public static final String COLUMNNAME_Z_Sisteco_TK_CVta_ID = "Z_Sisteco_TK_CVta_ID";

	/** Set Z_Sisteco_TK_CVta ID	  */
	public void setZ_Sisteco_TK_CVta_ID(int Z_Sisteco_TK_CVta_ID);

	/** Get Z_Sisteco_TK_CVta ID	  */
	public int getZ_Sisteco_TK_CVta_ID();

	public I_Z_Sisteco_TK_CVta getZ_Sisteco_TK_CVta() throws RuntimeException;

    /** Column name Z_Sisteco_TK_LDev_ID */
    public static final String COLUMNNAME_Z_Sisteco_TK_LDev_ID = "Z_Sisteco_TK_LDev_ID";

	/** Set Z_Sisteco_TK_LDev ID	  */
	public void setZ_Sisteco_TK_LDev_ID(int Z_Sisteco_TK_LDev_ID);

	/** Get Z_Sisteco_TK_LDev ID	  */
	public int getZ_Sisteco_TK_LDev_ID();
}
