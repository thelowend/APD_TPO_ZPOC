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

import java.util.List;

import edu.uade.apdzpoc.dao.ClienteDAO;

public class Cliente {

	private Integer idCliente;
	private String nombre;
	private int documento;
	private String domicilioFacturacion;
	private boolean responsableInscripto;
	private boolean ivaInscripto;
	private float descuento;
	private CuentaCorriente cuentaCorriente;

	public Cliente() {
	};

	public Cliente(String nombre, int documento, String domicilioFacturacion, boolean responsableInscripto,
			boolean ivaInscripto, float descuento, CuentaCorriente cuentaCorriente) {
		this.nombre = nombre;
		this.documento = documento;
		this.domicilioFacturacion = domicilioFacturacion;
		this.responsableInscripto = responsableInscripto;
		this.ivaInscripto = ivaInscripto;
		this.descuento = descuento;
		this.cuentaCorriente = cuentaCorriente;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
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

	public float disponibleEnCuenta() {
		return this.getCuentaCorriente().getDisponible();
	}

	public boolean leAlcanza(float costo) {
		return this.disponibleEnCuenta() > costo;
	}

	public String getTipoFactura() {
		return this.isResponsableInscripto() ? "A" : "B";
	}

	public void save() {
		ClienteDAO.getInstancia().save(this);
	}

	public static List <Cliente> obtenerClientesParaPublicar() {
		return ClienteDAO.getInstancia().getAll();
	}
	
	
	
}
