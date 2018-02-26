package edu.uade.apdzpoc.entidades;

import javax.persistence.*;

@Entity
@Table(name="UbicacionesLote")
public class UbicacionLoteEntity {

	@EmbeddedId
	private UbicacionLoteIDEntity id;
	
	/*@OneToOne
	@JoinColumn(name="Id_Ubicacion")
	private UbicacionEntity ubicacion;*/
	
	public UbicacionLoteEntity() {
		// TODO Auto-generated constructor stub
	}

	public UbicacionLoteIDEntity getId() {
		return id;
	}

	public void setId(UbicacionLoteIDEntity id) {
		this.id = id;
	}

	/*public UbicacionEntity getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionEntity ubicacion) {
		this.ubicacion = ubicacion;
	}*/
	
	
	

}
