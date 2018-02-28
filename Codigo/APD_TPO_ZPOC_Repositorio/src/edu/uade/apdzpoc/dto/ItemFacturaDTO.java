package edu.uade.apdzpoc.dto;

import java.io.Serializable;

public class ItemFacturaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArticuloDTO articulo;
	private int cantidad;
	private float precio;
	public ItemFacturaDTO(ArticuloDTO articulo, int cantidad, float precio) {
		super();
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.precio = precio;
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
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "ItemFacturaDTO [articulo=" + articulo + ", cantidad=" + cantidad + ", precio=" + precio + "]";
	}
	

	
	
	
	
	
}
