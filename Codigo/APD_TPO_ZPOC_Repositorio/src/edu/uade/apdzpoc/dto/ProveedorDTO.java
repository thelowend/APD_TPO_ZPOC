package edu.uade.apdzpoc.dto;

import java.io.Serializable;

public class ProveedorDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer idProveedor;
	private String nombreProveedor;
	public ProveedorDTO(Integer idProveedor, String nombreProveedor) {
		super();
		this.idProveedor = idProveedor;
		this.nombreProveedor = nombreProveedor;
	}
	public Integer getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	@Override
	public String toString() {
		return "ProveedorDTO [idProveedor=" + idProveedor + ", nombreProveedor=" + nombreProveedor + "]";
	}
	
	
	
	
	
	
}
