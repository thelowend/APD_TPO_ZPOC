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
import java.util.List;

public class Factura {
	
	private int idFactura;
	private Date fechaEmision;
	private Date fechaVencimiento;
	private String tipoFactura;
	private List<ItemFactura> itemsFactura;
	private float totalFactura;
	
	public float calcularMontoTotal(PedidoWeb pw){
		return 0f;
	}
	
	
	

}
