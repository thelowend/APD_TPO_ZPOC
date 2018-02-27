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

import edu.uade.apdzpoc.enums.EstadoItemPedido;

public class ItemPedido {

	private Integer IdItemPedido;
	public Integer getIdItemPedido() {
		return IdItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		IdItemPedido = idItemPedido;
	}

	private Articulo articulo;
	private int cantidad;
	private EstadoItemPedido estado;

	public ItemPedido(Articulo articulo, int cantidad, EstadoItemPedido estado) {
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.estado = estado;
	}

	public ItemPedido() {
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

	public EstadoItemPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoItemPedido estado) {
		this.estado = estado;
	}
	
	public float calcularTotal() {
		return this.articulo.getPrecioVenta() * this.cantidad;
	}
	
	public boolean hayStock() {
		
		boolean hayStock = this.getArticulo().tieneStock(this.getCantidad());
		if (!hayStock) {
			this.setEstado(EstadoItemPedido.Sin_Stock);
		} else {
			this.setEstado(EstadoItemPedido.Con_Stock);
		}
		return hayStock;
	}

}
