package edu.uade.apdzpoc.entidades;
import javax.persistence.*;

@Entity
@Table(name="Proveedor")
public class ProveedorEntity {

	@Id
	@Column(name="Id_Proveedor")
	private Integer idProveedor;
	
	@Column(name="Nombre")
	private String nombreProveedor;
	
	
	public ProveedorEntity() {
		
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

	
	
	
}
