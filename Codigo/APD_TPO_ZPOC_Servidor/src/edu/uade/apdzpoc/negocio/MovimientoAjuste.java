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

public class MovimientoAjuste extends Movimiento {


	/* (non-Javadoc)
	 * @see edu.uade.apdzpoc.negocio.Movimiento#actualizarNovedadStock(edu.uade.apdzpoc.negocio.Articulo)
	 */
	private int legajoOperador;
	private int legajoAutorizante;
	private int destino;
	private Lote lote;
	
	
	
	public MovimientoAjuste(Date fecha, Articulo articulo, int cantidad, String estado, String tipo, int legajoOperador,
			int legajoAutorizante, int destino, Lote lote) {
		super(fecha, articulo, cantidad, estado, tipo);
		this.legajoOperador = legajoOperador;
		this.legajoAutorizante = legajoAutorizante;
		this.destino = destino;
		this.lote = lote;
	}




	public int getLegajoOperador() {
		return legajoOperador;
	}




	public void setLegajoOperador(int legajoOperador) {
		this.legajoOperador = legajoOperador;
	}




	public int getLegajoAutorizante() {
		return legajoAutorizante;
	}




	public void setLegajoAutorizante(int legajoAutorizante) {
		this.legajoAutorizante = legajoAutorizante;
	}




	public int getDestino() {
		return destino;
	}




	public void setDestino(int destino) {
		this.destino = destino;
	}




	public Lote getLote() {
		return lote;
	}




	public void setLote(Lote lote) {
		this.lote = lote;
	}




	public MovimientoAjuste(Date fecha, Articulo articulo, int cantidad, String estado, String tipo) {
		super(fecha, articulo, cantidad, estado, tipo);
		// TODO Auto-generated constructor stub
	}

	
	
	
	@Override
	public void actualizarNovedadStock(Articulo art) {
		
	}

}
