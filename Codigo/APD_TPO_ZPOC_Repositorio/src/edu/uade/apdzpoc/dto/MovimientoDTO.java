package edu.uade.apdzpoc.dto;

import java.io.Serializable;
import java.util.Date;


public class MovimientoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Integer idMovimiento;
	protected Date fecha;
	protected ArticuloDTO articulo;
	protected int cantidad;
	protected String estado;
	public Integer getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public ArticuloDTO getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "MovimientoDTO [idMovimiento=" + idMovimiento + ", fecha=" + fecha + ", articulo=" + articulo
				+ ", cantidad=" + cantidad + ", estado=" + estado + "]";
	}

	
	
	
	
}
