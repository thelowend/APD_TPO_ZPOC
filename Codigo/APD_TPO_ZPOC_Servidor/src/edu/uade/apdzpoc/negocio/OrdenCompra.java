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

import java.util.Date;

public class OrdenCompra {

	private int idOC;
	private Proveedor proveedor;
	private int cantidad;
	private int estado;
	private Articulo articulo;
	private Lote lote;
	private PedidoWeb pedidoW;
	private Date fecha;
	
	public OrdenCompra(Proveedor proveedor, int cantidad, Articulo articulo, Lote lote, PedidoWeb pedidoW) {
		super();
		this.proveedor = proveedor;
		this.cantidad = cantidad;
		this.articulo = articulo;
		this.lote = lote;
		this.pedidoW = pedidoW;
	}

	public int getIdOC() {
		return idOC;
	}

	public void setIdOC(int idOC) {
		this.idOC = idOC;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public PedidoWeb getPedidoW() {
		return pedidoW;
	}

	public void setPedidoW(PedidoWeb pedidoW) {
		this.pedidoW = pedidoW;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
