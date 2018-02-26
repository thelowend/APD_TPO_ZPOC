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


public class Cliente {

	private int idCliente;
	private String nombre;
	private int documento;
	private String domicilioFacturacion;
	private boolean responsableInscripto;
	private boolean ivaInscripto;
	private float descuento;
	private CuentaCorriente cuentaCorriente;
	
	
	public Cliente () {};
	
	public Cliente(int idCliente, String nombre, int documento, String domicilioFacturacion,
			boolean responsableInscripto, boolean ivaInscripto, float descuento, CuentaCorriente cuentaCorriente) {
		
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.documento = documento;
		this.domicilioFacturacion = domicilioFacturacion;
		this.responsableInscripto = responsableInscripto;
		this.ivaInscripto = ivaInscripto;
		this.descuento = descuento;
		this.cuentaCorriente = cuentaCorriente;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	public String getDomicilioFacturacion() {
		return domicilioFacturacion;
	}
	public void setDomicilioFacturacion(String domicilioFacturacion) {
		this.domicilioFacturacion = domicilioFacturacion;
	}
	public boolean isResponsableInscripto() {
		return responsableInscripto;
	}
	public void setResponsableInscripto(boolean responsableInscripto) {
		this.responsableInscripto = responsableInscripto;
	}
	public boolean isIvaInscripto() {
		return ivaInscripto;
	}
	public void setIvaInscripto(boolean ivaInscripto) {
		this.ivaInscripto = ivaInscripto;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	public CuentaCorriente getCuentaCorriente() {
		return cuentaCorriente;
	}
	public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}
	
	
}
