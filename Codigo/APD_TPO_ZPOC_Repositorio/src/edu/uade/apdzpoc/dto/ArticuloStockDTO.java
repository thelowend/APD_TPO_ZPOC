package edu.uade.apdzpoc.dto;

import java.io.Serializable;
import java.util.List;



public class ArticuloStockDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codigoBarra;
	private String nombreArticulo;
	private String descripcion;
	private float precioVenta;
	private int cantidadCompra;
	private String presentacion;
	private String tamanio;

	private int stockFisico;
	private int stockVirtual;
	private int stockDisponible;
	private int stockPendienteEntrega;
	public ArticuloStockDTO(Integer codigoBarra, String nombreArticulo, String descripcion, float precioVenta,
			String presentacion, String tamanio, int stockFisico, int stockVirtual,
			int stockDisponible, int stockPendienteEntrega) {
		super();
		this.codigoBarra = codigoBarra;
		this.nombreArticulo = nombreArticulo;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.cantidadCompra = cantidadCompra;
		this.presentacion = presentacion;
		this.tamanio = tamanio;
		this.stockFisico = stockFisico;
		this.stockVirtual = stockVirtual;
		this.stockDisponible = stockDisponible;
		this.stockPendienteEntrega = stockPendienteEntrega;
	}
	public Integer getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(Integer codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public String getNombreArticulo() {
		return nombreArticulo;
	}
	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}
	public int getCantidadCompra() {
		return cantidadCompra;
	}
	public void setCantidadCompra(int cantidadCompra) {
		this.cantidadCompra = cantidadCompra;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
	public int getStockFisico() {
		return stockFisico;
	}
	public void setStockFisico(int stockFisico) {
		this.stockFisico = stockFisico;
	}
	public int getStockVirtual() {
		return stockVirtual;
	}
	public void setStockVirtual(int stockVirtual) {
		this.stockVirtual = stockVirtual;
	}
	public int getStockDisponible() {
		return stockDisponible;
	}
	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}
	public int getStockPendienteEntrega() {
		return stockPendienteEntrega;
	}
	public void setStockPendienteEntrega(int stockPendienteEntrega) {
		this.stockPendienteEntrega = stockPendienteEntrega;
	}
	@Override
	public String toString() {
		return "ArticuloStockDTO [codigoBarra=" + codigoBarra + ", nombreArticulo=" + nombreArticulo + ", descripcion="
				+ descripcion + ", precioVenta=" + precioVenta + ", cantidadCompra=" + cantidadCompra
				+ ", presentacion=" + presentacion + ", tamanio=" + tamanio + ", stockFisico=" + stockFisico
				+ ", stockVirtual=" + stockVirtual + ", stockDisponible=" + stockDisponible + ", stockPendienteEntrega="
				+ stockPendienteEntrega + "]";
	}

	
	
	
	
	
	
	
	
}
