package edu.uade.apdzpoc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.enums.EstadoFactura;


public class FacturaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idFactura;
	private ClienteDTO cliente;
	private Date fechaEmision;
	private Date fechaVencimiento;
	private String tipoFactura;
	private List<ItemFacturaDTO> itemsFactura;
	private float totalFactura;
	private EstadoFactura estado;
	public FacturaDTO(Integer idFactura, ClienteDTO cliente, Date fechaEmision, Date fechaVencimiento,
			String tipoFactura, List<ItemFacturaDTO> itemsFactura, float totalFactura, EstadoFactura estado) {
		super();
		this.idFactura = idFactura;
		this.cliente = cliente;
		this.fechaEmision = fechaEmision;
		this.fechaVencimiento = fechaVencimiento;
		this.tipoFactura = tipoFactura;
		this.itemsFactura = itemsFactura;
		this.totalFactura = totalFactura;
		this.estado = estado;
	}
	public Integer getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
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
	public List<ItemFacturaDTO> getItemsFactura() {
		return itemsFactura;
	}
	public void setItemsFactura(List<ItemFacturaDTO> itemsFactura) {
		this.itemsFactura = itemsFactura;
	}
	public float getTotalFactura() {
		return totalFactura;
	}
	public void setTotalFactura(float totalFactura) {
		this.totalFactura = totalFactura;
	}
	public EstadoFactura getEstado() {
		return estado;
	}
	public void setEstado(EstadoFactura estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "FacturaDTO [idFactura=" + idFactura + ", cliente=" + cliente + ", fechaEmision=" + fechaEmision
				+ ", fechaVencimiento=" + fechaVencimiento + ", tipoFactura=" + tipoFactura + ", itemsFactura="
				+ itemsFactura + ", totalFactura=" + totalFactura + ", estado=" + estado + "]";
	}	
	
	
	
	
	
}
