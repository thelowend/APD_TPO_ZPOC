package edu.uade.apdzpoc.entidades;

import javax.persistence.*;

@Embeddable
public class UbicacionLoteIDEntity {

	@ManyToOne
	@JoinColumn(name="Codigo_Barra", nullable=false)
	private ArticuloEntity articulo;
	
	@ManyToOne
	@JoinColumn(name="Nro_Lote", nullable=false)
	private ProveedorEntity proveedor;
	
	
	public UbicacionLoteIDEntity() {
		// TODO Auto-generated constructor stub
	}


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
