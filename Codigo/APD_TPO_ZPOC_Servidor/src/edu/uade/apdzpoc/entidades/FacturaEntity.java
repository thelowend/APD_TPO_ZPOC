package edu.uade.apdzpoc.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name="Facturas")
public class FacturaEntity {

	@Id
	@Column(name="NroFactura")
	private Integer idFactura;
	
	@ManyToOne
	@JoinColumn(name="IdCliente")
	private ClienteEntity cliente;
	
	@Column(name="FechaEmision")
	@Type(type="date")
	private Date fechaEmision;
	
	@Column(name="FechaVencimiento")
	@Type(type="date")
	private Date fechaVencimiento;
	
	@Column(name="TipoFactura")
	private String tipoFactura;
	
	@OneToMany
	@JoinColumn(name="Nro_Factura")
	private List<ItemFacturaEntity> itemsFactura;
	
	@Column(name="TotalFactura")
	private float totalFactura;
	
	
	public FacturaEntity() {
	
	}


	public int getIdFactura() {
		return idFactura;
	}


	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}


	public Date getFechaEmision() {
		return fechaEmision;
	}


	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}


	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	public String getTipoFactura() {
		return tipoFactura;
	}


	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}


	public List<ItemFacturaEntity> getItemsFactura() {
		return itemsFactura;
	}


	public void setItemsFactura(List<ItemFacturaEntity> itemsFactura) {
		this.itemsFactura = itemsFactura;
	}


	public float getTotalFactura() {
		return totalFactura;
	}


	public void setTotalFactura(float totalFactura) {
		this.totalFactura = totalFactura;
	}
	
	

}
