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

import edu.uade.apdzpoc.dao.ProveedorDAO;

public class Proveedor {

	private Integer idProveedor;
	private String nombreProveedor;
	public Proveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	public Proveedor() {
		// TODO Auto-generated constructor stub
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


    public void save() {
		ProveedorDAO.getInstancia().save(this);
    }
}
