package edu.uade.apdzpoc.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Factura")
public class FacturaEntity {

	@Id
	@Column(name="Nro_Factura")
	private int idFactura;
	
	@Column(name="Fecha_Emision")
	private Date fechaEmision;
	
	@Column(name="Fecha_Vencimiento")
	private Date fechaVencimiento;
	
	@Column(name="Tipo_Factura")
	private String tipoFactura;
	
	@OneToMany
	@JoinColumn(name="Nro_Factura")
	private List<ItemFacturaEntity> itemsFactura;
	
	@Column(name="Total_Factura")
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
