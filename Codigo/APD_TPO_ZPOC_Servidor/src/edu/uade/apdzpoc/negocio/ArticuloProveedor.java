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

import edu.uade.apdzpoc.dao.ArticuloProveedorDAO;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.ProveedorException;

public class ArticuloProveedor {

	private Articulo articulo;
	private Proveedor proveedor;
	private float precio;

	public ArticuloProveedor(Articulo articulo, Proveedor proveedor, float precio) {
		this.articulo = articulo;
		this.proveedor = proveedor;
		this.precio = precio;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public static Proveedor getMejorProveedorPorArticulo(Articulo articulo) throws ArticuloProveedorException, ProveedorException {
		return ArticuloProveedorDAO.getInstancia().findBestProveedorByArticulo(articulo.getCodigoBarra());
	}

}
