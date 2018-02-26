/**
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
package edu.uade.apdzpoc.excepciones;

public class PagoClienteException extends Exception {

	private static final long serialVersionUID = 598333950778427358L;

	public PagoClienteException(String mensaje) {
		super(mensaje);
	}
}