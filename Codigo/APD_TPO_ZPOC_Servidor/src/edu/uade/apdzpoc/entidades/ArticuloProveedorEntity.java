package edu.uade.apdzpoc.entidades;

import javax.persistence.*;


@Entity
@Table(name="ArticulosProveedor")

public class ArticuloProveedorEntity {

	@EmbeddedId
	private ArticuloProveedorIDEntity id;
	
	@Column(name="PrecioCompra")
	private Float precio;

	public ArticuloProveedorEntity() {
	
	}

	public ArticuloProveedorIDEntity getId() {
		return id;
	}

	public void setId(ArticuloProveedorIDEntity id) {
		this.id = id;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
	
	
	
}
