package edu.uade.apdzpoc.dto;

import java.io.Serializable;

import edu.uade.apdzpoc.enums.EstadoUbicacion;

public class UbicacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idUbicacion;
	private String nombre;
	private String calle;
	private int bloque;
	private int estante;
	private int posicion;
	private EstadoUbicacion estado;
	private int capacidad;
	private int capacidadInicial;
	public UbicacionDTO(Integer idUbicacion, String nombre, String calle, int bloque, int estante, int posicion,
			EstadoUbicacion estado, int capacidad, int capacidadInicial) {
		super();
		this.idUbicacion = idUbicacion;
		this.nombre = nombre;
		this.calle = calle;
		this.bloque = bloque;
		this.estante = estante;
		this.posicion = posicion;
		this.estado = estado;
		this.capacidad = capacidad;
		this.capacidadInicial = capacidadInicial;
	}
	
	
	
	
	
	
	




	public UbicacionDTO() {
		
	}










	public Integer getIdUbicacion() {
		return idUbicacion;
	}










	public void setIdUbicacion(Integer idUbicacion) {
		this.idUbicacion = idUbicacion;
	}










	public String getNombre() {
		return nombre;
	}










	public void setNombre(String nombre) {
		this.nombre = nombre;
	}










	public String getCalle() {
		return calle;
	}










	public void setCalle(String calle) {
		this.calle = calle;
	}










	public int getBloque() {
		return bloque;
	}










	public void setBloque(int bloque) {
		this.bloque = bloque;
	}










	public int getEstante() {
		return estante;
	}










	public void setEstante(int estante) {
		this.estante = estante;
	}










	public int getPosicion() {
		return posicion;
	}










	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}










	public EstadoUbicacion getEstado() {
		return estado;
	}










	public void setEstado(EstadoUbicacion estado) {
		this.estado = estado;
	}










	public int getCapacidad() {
		return capacidad;
	}










	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}










	public int getCapacidadInicial() {
		return capacidadInicial;
	}










	public void setCapacidadInicial(int capacidadInicial) {
		this.capacidadInicial = capacidadInicial;
	}










	@Override
	public String toString() {
		return "UbicacionesDTO [idUbicacion=" + idUbicacion + ", nombre=" + nombre + ", calle=" + calle + ", bloque="
				+ bloque + ", estante=" + estante + ", posicion=" + posicion + ", estado=" + estado + ", capacidad="
				+ capacidad + ", capacidadInicial=" + capacidadInicial + "]";
	}
	
	
	

}
