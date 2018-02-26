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

public class OrdenCompraException extends Exception {

	private static final long serialVersionUID = 7574094548440600998L;

	public OrdenCompraException(String mensaje) {
		super(mensaje);
	}
}