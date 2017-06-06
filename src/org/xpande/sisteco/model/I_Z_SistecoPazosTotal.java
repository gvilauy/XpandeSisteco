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

/** Generated Interface for Z_SistecoPazosTotal
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_Z_SistecoPazosTotal 
{

    /** TableName=Z_SistecoPazosTotal */
    public static final String Table_Name = "Z_SistecoPazosTotal";

    /** AD_Table_ID=1000033 */
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

    /** Column name ST_TotalDevEnvases */
    public static final String COLUMNNAME_ST_TotalDevEnvases = "ST_TotalDevEnvases";

	/** Set ST_TotalDevEnvases	  */
	public void setST_TotalDevEnvases(BigDecimal ST_TotalDevEnvases);

	/** Get ST_TotalDevEnvases	  */
	public BigDecimal getST_TotalDevEnvases();

    /** Column name ST_TotalEdenred */
    public static final String COLUMNNAME_ST_TotalEdenred = "ST_TotalEdenred";

	/** Set ST_TotalEdenred	  */
	public void setST_TotalEdenred(BigDecimal ST_TotalEdenred);

	/** Get ST_TotalEdenred	  */
	public BigDecimal getST_TotalEdenred();

    /** Column name ST_TotalFacturas */
    public static final String COLUMNNAME_ST_TotalFacturas = "ST_TotalFacturas";

	/** Set ST_TotalFacturas	  */
	public void setST_TotalFacturas(BigDecimal ST_TotalFacturas);

	/** Get ST_TotalFacturas	  */
	public BigDecimal getST_TotalFacturas();

    /** Column name ST_TotalFondeo */
    public static final String COLUMNNAME_ST_TotalFondeo = "ST_TotalFondeo";

	/** Set ST_TotalFondeo	  */
	public void setST_TotalFondeo(BigDecimal ST_TotalFondeo);

	/** Get ST_TotalFondeo	  */
	public BigDecimal getST_TotalFondeo();

    /** Column name ST_TotalFondeoUSD */
    public static final String COLUMNNAME_ST_TotalFondeoUSD = "ST_TotalFondeoUSD";

	/** Set ST_TotalFondeoUSD	  */
	public void setST_TotalFondeoUSD(BigDecimal ST_TotalFondeoUSD);

	/** Get ST_TotalFondeoUSD	  */
	public BigDecimal getST_TotalFondeoUSD();

    /** Column name ST_TotalRetiros */
    public static final String COLUMNNAME_ST_TotalRetiros = "ST_TotalRetiros";

	/** Set ST_TotalRetiros	  */
	public void setST_TotalRetiros(BigDecimal ST_TotalRetiros);

	/** Get ST_TotalRetiros	  */
	public BigDecimal getST_TotalRetiros();

    /** Column name ST_TotalServicios */
    public static final String COLUMNNAME_ST_TotalServicios = "ST_TotalServicios";

	/** Set ST_TotalServicios	  */
	public void setST_TotalServicios(BigDecimal ST_TotalServicios);

	/** Get ST_TotalServicios	  */
	public BigDecimal getST_TotalServicios();

    /** Column name ST_TotalVtaCheque */
    public static final String COLUMNNAME_ST_TotalVtaCheque = "ST_TotalVtaCheque";

	/** Set ST_TotalVtaCheque	  */
	public void setST_TotalVtaCheque(BigDecimal ST_TotalVtaCheque);

	/** Get ST_TotalVtaCheque	  */
	public BigDecimal getST_TotalVtaCheque();

    /** Column name ST_TotalVtaChequeUSD */
    public static final String COLUMNNAME_ST_TotalVtaChequeUSD = "ST_TotalVtaChequeUSD";

	/** Set ST_TotalVtaChequeUSD	  */
	public void setST_TotalVtaChequeUSD(BigDecimal ST_TotalVtaChequeUSD);

	/** Get ST_TotalVtaChequeUSD	  */
	public BigDecimal getST_TotalVtaChequeUSD();

    /** Column name ST_TotalVtaClientes */
    public static final String COLUMNNAME_ST_TotalVtaClientes = "ST_TotalVtaClientes";

	/** Set ST_TotalVtaClientes	  */
	public void setST_TotalVtaClientes(BigDecimal ST_TotalVtaClientes);

	/** Get ST_TotalVtaClientes	  */
	public BigDecimal getST_TotalVtaClientes();

    /** Column name ST_TotalVtaCredito */
    public static final String COLUMNNAME_ST_TotalVtaCredito = "ST_TotalVtaCredito";

	/** Set ST_TotalVtaCredito	  */
	public void setST_TotalVtaCredito(BigDecimal ST_TotalVtaCredito);

	/** Get ST_TotalVtaCredito	  */
	public BigDecimal getST_TotalVtaCredito();

    /** Column name ST_TotalVtaCreditoUSD */
    public static final String COLUMNNAME_ST_TotalVtaCreditoUSD = "ST_TotalVtaCreditoUSD";

	/** Set ST_TotalVtaCreditoUSD	  */
	public void setST_TotalVtaCreditoUSD(BigDecimal ST_TotalVtaCreditoUSD);

	/** Get ST_TotalVtaCreditoUSD	  */
	public BigDecimal getST_TotalVtaCreditoUSD();

    /** Column name ST_TotalVtaEfectivo */
    public static final String COLUMNNAME_ST_TotalVtaEfectivo = "ST_TotalVtaEfectivo";

	/** Set ST_TotalVtaEfectivo	  */
	public void setST_TotalVtaEfectivo(BigDecimal ST_TotalVtaEfectivo);

	/** Get ST_TotalVtaEfectivo	  */
	public BigDecimal getST_TotalVtaEfectivo();

    /** Column name ST_TotalVtaEfectivoUSD */
    public static final String COLUMNNAME_ST_TotalVtaEfectivoUSD = "ST_TotalVtaEfectivoUSD";

	/** Set ST_TotalVtaEfectivoUSD	  */
	public void setST_TotalVtaEfectivoUSD(BigDecimal ST_TotalVtaEfectivoUSD);

	/** Get ST_TotalVtaEfectivoUSD	  */
	public BigDecimal getST_TotalVtaEfectivoUSD();

    /** Column name ST_TotalVtaLuncheon */
    public static final String COLUMNNAME_ST_TotalVtaLuncheon = "ST_TotalVtaLuncheon";

	/** Set ST_TotalVtaLuncheon	  */
	public void setST_TotalVtaLuncheon(BigDecimal ST_TotalVtaLuncheon);

	/** Get ST_TotalVtaLuncheon	  */
	public BigDecimal getST_TotalVtaLuncheon();

    /** Column name ST_TotalVtaSodexo */
    public static final String COLUMNNAME_ST_TotalVtaSodexo = "ST_TotalVtaSodexo";

	/** Set ST_TotalVtaSodexo	  */
	public void setST_TotalVtaSodexo(BigDecimal ST_TotalVtaSodexo);

	/** Get ST_TotalVtaSodexo	  */
	public BigDecimal getST_TotalVtaSodexo();

    /** Column name ST_TotalVtaTarjeta */
    public static final String COLUMNNAME_ST_TotalVtaTarjeta = "ST_TotalVtaTarjeta";

	/** Set ST_TotalVtaTarjeta	  */
	public void setST_TotalVtaTarjeta(BigDecimal ST_TotalVtaTarjeta);

	/** Get ST_TotalVtaTarjeta	  */
	public BigDecimal getST_TotalVtaTarjeta();

    /** Column name ST_TotalVtaTarjetaCuota */
    public static final String COLUMNNAME_ST_TotalVtaTarjetaCuota = "ST_TotalVtaTarjetaCuota";

	/** Set ST_TotalVtaTarjetaCuota	  */
	public void setST_TotalVtaTarjetaCuota(BigDecimal ST_TotalVtaTarjetaCuota);

	/** Get ST_TotalVtaTarjetaCuota	  */
	public BigDecimal getST_TotalVtaTarjetaCuota();

    /** Column name ST_TotalVtaTarjetaManual */
    public static final String COLUMNNAME_ST_TotalVtaTarjetaManual = "ST_TotalVtaTarjetaManual";

	/** Set ST_TotalVtaTarjetaManual	  */
	public void setST_TotalVtaTarjetaManual(BigDecimal ST_TotalVtaTarjetaManual);

	/** Get ST_TotalVtaTarjetaManual	  */
	public BigDecimal getST_TotalVtaTarjetaManual();

    /** Column name ST_TotalVtaTarjetaUSD */
    public static final String COLUMNNAME_ST_TotalVtaTarjetaUSD = "ST_TotalVtaTarjetaUSD";

	/** Set ST_TotalVtaTarjetaUSD	  */
	public void setST_TotalVtaTarjetaUSD(BigDecimal ST_TotalVtaTarjetaUSD);

	/** Get ST_TotalVtaTarjetaUSD	  */
	public BigDecimal getST_TotalVtaTarjetaUSD();

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

    /** Column name Z_SistecoPazosTotal_ID */
    public static final String COLUMNNAME_Z_SistecoPazosTotal_ID = "Z_SistecoPazosTotal_ID";

	/** Set Z_SistecoPazosTotal ID	  */
	public void setZ_SistecoPazosTotal_ID(int Z_SistecoPazosTotal_ID);

	/** Get Z_SistecoPazosTotal ID	  */
	public int getZ_SistecoPazosTotal_ID();
}
