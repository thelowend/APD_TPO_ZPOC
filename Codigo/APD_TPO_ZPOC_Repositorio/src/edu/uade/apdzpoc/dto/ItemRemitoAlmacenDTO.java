package edu.uade.apdzpoc.dto;

import java.io.Serializable;

public class ItemRemitoAlmacenDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArticuloDTO articulo;
	private int cantidad;
	private UbicacionDTO ubicacion;
	
	
	public ItemRemitoAlmacenDTO(ArticuloDTO articulo, int cantidad, UbicacionDTO ubicacion) {
		super();
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.ubicacion = ubicacion;
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
	public UbicacionDTO getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(UbicacionDTO ubicacion) {
		this.ubicacion = ubicacion;
	}
	@Override
	public String toString() {
		return "ItemRemitoAlmacenDTO [articulo=" + articulo + ", cantidad=" + cantidad + ", ubicacion=" + ubicacion
				+ "]";
	}

	
	
	
}
