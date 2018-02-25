package edu.uade.apdzpoc.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class UbicacionLoteIDEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="Id_Ubicacion", nullable=false)
	private UbicacionEntity ubicacion;
	
	@ManyToOne
	@JoinColumn(name="Nro_Lote", nullable=false)
	private LoteEntity lote;
	
	
	public UbicacionLoteIDEntity() {
		// TODO Auto-generated constructor stub
	}


	public UbicacionEntity getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(UbicacionEntity ubicacion) {
		this.ubicacion = ubicacion;
	}


	public LoteEntity getLote() {
		return lote;
	}


	public void setLote(LoteEntity lote) {
		this.lote = lote;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lote == null) ? 0 : lote.hashCode());
		result = prime * result + ((ubicacion == null) ? 0 : ubicacion.hashCode());
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
		if (lote == null) {
			if (other.lote != null)
				return false;
		} else if (!lote.equals(other.lote))
			return false;
		if (ubicacion == null) {
			if (other.ubicacion != null)
				return false;
		} else if (!ubicacion.equals(other.ubicacion))
			return false;
		return true;
	}


	
	
	
}
