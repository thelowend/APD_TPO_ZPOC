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
 
package edu.uade.apdzpoc.entidades;


import javax.persistence.*;


@Embeddable
public class ArticuloProveedorIDEntity {
	
	@ManyToOne
	@JoinColumn(name="Codigo_Barra", nullable=false)
	private ArticuloEntity articulo;
	
	@ManyToOne
	@JoinColumn(name="identificador", nullable=false)
	private ProveedorEntity proveedor;


	public ArticuloProveedorIDEntity() {	}


	public ArticuloEntity getArticulo() {
		return articulo;
	}


	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}


	public ProveedorEntity getProveedor() {
		return proveedor;
	}


	public void setProveedor(ProveedorEntity proveedor) {
		this.proveedor = proveedor;
	}

	
	
	
	

}
