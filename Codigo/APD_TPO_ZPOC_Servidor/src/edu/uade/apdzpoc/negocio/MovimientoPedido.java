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
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.uade.apdzpoc.negocio.Movimiento#actualizarNovedadStock(edu.uade.apdzpoc.
	 * negocio.Articulo)
	 */

	@Override
	public void actualizarNovedadStock() {
		
		this.articulo.setStockDisponible(0);
		this.articulo.setStockVirtual(0);
	}

	public PedidoWeb getPw() {
		return pw;
	}

	public void setPw(PedidoWeb pw) {
		this.pw = pw;
	}

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

}
