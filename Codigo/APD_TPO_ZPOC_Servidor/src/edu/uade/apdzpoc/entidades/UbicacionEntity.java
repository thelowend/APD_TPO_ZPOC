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

@Entity
@Table(name="")
public class UbicacionEntity {

	/**
	 * 
	 */
	@Id
	@Column(name="Id_Ubicacion")
	private String idUbicacion;
	
	@Column (name="Calle")
	private String calle;
	
	@Column (name="Bloque")
	private Integer bloque;
	
	@Column (name="Estante")
	private Integer estante;
		
	@Column (name="Bandeja")
	private Integer bandeja;
	
	@Column (name="Posicion")
	private int posicion;
	
	@Column (name="Estado")	
	private String estado;
	
	@Column
	private Integer capacidad;
	
	
	
	public UbicacionEntity() {
		
	}



	public String getIdUbicacion() {
		return idUbicacion;
	}



	public void setIdUbicacion(String idUbicacion) {
		this.idUbicacion = idUbicacion;
	}



	public String getCalle() {
		return calle;
	}



	public void setCalle(String calle) {
		this.calle = calle;
	}



	public Integer getBloque() {
		return bloque;
	}



	public void setBloque(Integer bloque) {
		this.bloque = bloque;
	}



	public Integer getEstante() {
		return estante;
	}



	public void setEstante(Integer estante) {
		this.estante = estante;
	}



	public Integer getBandeja() {
		return bandeja;
	}



	public void setBandeja(Integer bandeja) {
		this.bandeja = bandeja;
	}



	public int getPosicion() {
		return posicion;
	}



	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public Integer getCapacidad() {
		return capacidad;
	}



	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	
	
	
	
}
