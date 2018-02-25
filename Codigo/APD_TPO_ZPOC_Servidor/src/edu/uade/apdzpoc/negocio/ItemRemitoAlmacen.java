/**
 * 
 * 
 * TPO: APDZPOC
 * 
 * GRUPO 08
 * Integrantes:
 * 	LU:0119404	- Zapatero, Barbara Daniela
 * 	LU:1022185	- Pablos, Diego Maximiliano
 * 	LU:0133009	- Ojeda, Maria De Los Angeles
 *  LU:0127304	- Cavallaro, Cristian Alberto
 *  
 *
 */
 
package edu.uade.apdzpoc.negocio;

public class ItemRemitoAlmacen {

	private int IdItemRemitoAlmacen;
	public int getIdItemRemitoAlmacen() {
		return IdItemRemitoAlmacen;
	}

	public void setIdItemRemitoAlmacen(int idItemRemitoAlmacen) {
		IdItemRemitoAlmacen = idItemRemitoAlmacen;
	}

	private Articulo articulo;
	private int cantidad;
	private Ubicacion ubicacion;
	
	public ItemRemitoAlmacen(Articulo articulo, int cantidad, Ubicacion ubicacion) {
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.ubicacion = ubicacion;
	}

	public ItemRemitoAlmacen() {
		// TODO Auto-generated constructor stub
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
}
