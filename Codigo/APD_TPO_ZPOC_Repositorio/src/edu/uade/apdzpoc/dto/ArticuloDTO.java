package edu.uade.apdzpoc.dto;

import java.io.Serializable;

public class ArticuloDTO implements Serializable {

	private static final long serialVersionUID = 2074088629750197074L;
	private int codigoBarra;
	private String nombreArticulo;
	private String descripcion;
	private float precioVenta;
	private String presentacion;
	private String tamanio;

	public ArticuloDTO(int codigoBarra, String nombreArticulo, String descripcion, float precioVenta,
			String presentacion, String tamanio) {
		super();
		this.codigoBarra = codigoBarra;
		this.nombreArticulo = nombreArticulo;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.presentacion = presentacion;
		this.tamanio = tamanio;
	}


	public int getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(int codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
}
