package edu.uade.apdzpoc.entidades;
import javax.persistence.*;

@Entity
@Table(name="Proveedores")
public class ProveedorEntity {

	
	@Id
	@Column(name="IdProveedor")
	private Integer idProveedor;
	
	@Column(name="Nombre")
	private String nombreProveedor;
	
	
	public ProveedorEntity() {
		
	}


	public int getIdProveedor() {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProveedor == null) ? 0 : idProveedor.hashCode());
		result = prime * result + ((nombreProveedor == null) ? 0 : nombreProveedor.hashCode());
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
		ProveedorEntity other = (ProveedorEntity) obj;
		if (idProveedor == null) {
			if (other.idProveedor != null)
				return false;
		} else if (!idProveedor.equals(other.idProveedor))
			return false;
		if (nombreProveedor == null) {
			if (other.nombreProveedor != null)
				return false;
		} else if (!nombreProveedor.equals(other.nombreProveedor))
			return false;
		return true;
	}


	
}
