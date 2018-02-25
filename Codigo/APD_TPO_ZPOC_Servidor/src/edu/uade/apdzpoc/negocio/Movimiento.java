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

public abstract class Movimiento {

	protected int idMovimiento;
	protected Date fecha;
	protected Articulo articulo;
	protected int cantidad;
	protected String estado;
	// protected String tipo;

	public Movimiento(Date fecha, Articulo articulo, int cantidad/*, String tipo */) {
		this.fecha = fecha;
		this.articulo = articulo;
		this.cantidad = cantidad;
		// this.tipo = tipo;
	}

	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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


//	public String getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(String tipo) {
//		this.tipo = tipo;
//	}

	public abstract void actualizarNovedadStock();

}
