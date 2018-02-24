package edu.uade.apdzpoc.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class UbicacionLoteIDEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articulo == null) ? 0 : articulo.hashCode());
		result = prime * result + ((proveedor == null) ? 0 : proveedor.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UbicacionLoteIDEntity other = (UbicacionLoteIDEntity) obj;
		if (articulo == null) {
			if (other.articulo != null)
				return false;
		} else if (!articulo.equals(other.articulo))
			return false;
		if (proveedor == null) {
			if (other.proveedor != null)
				return false;
		} else if (!proveedor.equals(other.proveedor))
			return false;
		return true;
	}


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