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
	
	/* (non-Javadoc)
	 * @see edu.uade.apdzpoc.negocio.Movimiento#actualizarNovedadStock(edu.uade.apdzpoc.negocio.Articulo)
	 */
	
	public MovimientoCompra(Date fecha, Articulo articulo, int cantidad, String estado, String tipo, OrdenCompra oc,
			Lote lote) {
		super(fecha, articulo, cantidad, estado, tipo);
		this.oc = oc;
		this.lote = lote;
	}
	
	
	
	
	public MovimientoCompra(Date fecha, Articulo articulo, int cantidad, String estado, String tipo) {
		super(fecha, articulo, cantidad, estado, tipo);
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
	public void actualizarNovedadStock(Articulo art) {
		// TODO Auto-generated method stub

	}


		
}
