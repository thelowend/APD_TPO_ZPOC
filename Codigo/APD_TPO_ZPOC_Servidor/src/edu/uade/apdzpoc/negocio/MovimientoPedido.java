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

public class MovimientoPedido extends Movimiento {

	private PedidoWeb pw;
	
	public MovimientoPedido(Date fecha, Articulo articulo, int cantidad) {
		super(fecha, articulo, cantidad);
	}

	public MovimientoPedido(Date fecha, Articulo articulo, int cantidad, PedidoWeb pw) {
		super(fecha, articulo, cantidad);
		this.pw = pw;
	}

	public MovimientoPedido() {
		// TODO Auto-generated constructor stub
	}

	public PedidoWeb getPw() {
		return pw;
	}

	public void setPw(PedidoWeb pw) {
		this.pw = pw;
	}
	
	public void actualizarNovedadStock() {
		int cantidadRecibida = this.cantidad;
		
		int stockActual = this.articulo.getStockDisponible() + cantidadRecibida + this.articulo.getStockVirtual();
	 	this.articulo.setStockDisponible(stockActual < 0 ? 0 : stockActual);
		this.articulo.setStockVirtual(stockActual > 0 ? 0 : stockActual);
		
		this.articulo.setStockPendienteEntrega(this.articulo.getStockPendienteEntrega() - cantidadRecibida);}
	
//		this.articulo.setStockDisponible(0);
//		this.articulo.setStockVirtual(0);
	}

}
