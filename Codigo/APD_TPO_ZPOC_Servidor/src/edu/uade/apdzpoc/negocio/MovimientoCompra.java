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

public class MovimientoCompra extends Movimiento {

	private OrdenCompra oc;
	private Lote lote;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.uade.apdzpoc.negocio.Movimiento#actualizarNovedadStock(edu.uade.apdzpoc.
	 * negocio.Articulo)
	 */

	public MovimientoCompra(Date fecha, Articulo articulo, int cantidad, OrdenCompra oc, Lote lote) {
		super(fecha, articulo, cantidad);
		this.oc = oc;
		this.lote = lote;
	}

	public MovimientoCompra(Date fecha, Articulo articulo, int cantidad) {
		super(fecha, articulo, cantidad);
		// TODO Auto-generated constructor stub
	}

	public MovimientoCompra() {
		// TODO Auto-generated constructor stub
	}

	public OrdenCompra getOc() {
		return oc;
	}

	public void setOc(OrdenCompra oc) {
		this.oc = oc;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	@Override
	public void actualizarNovedadStock() {
		
		int cantidadRecibida = this.cantidad;
		
		int stockActual = this.articulo.getStockDisponible() + cantidadRecibida + this.articulo.getStockVirtual();
	 	this.articulo.setStockDisponible(stockActual < 0 ? 0 : stockActual);
		this.articulo.setStockVirtual(stockActual > 0 ? 0 : stockActual);
		
		this.articulo.setStockPendienteEntrega(this.articulo.getStockPendienteEntrega() - cantidadRecibida);

	}

}
