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
 
package edu.uade.apdzpoc.negocio;

import edu.uade.apdzpoc.dao.UbicacionDAO;
import edu.uade.apdzpoc.enums.EstadoUbicacion;

public class Ubicacion {

	private int idUbicacion;
	private String nombre;
	private String calle;
	private int bloque;
	private int estante;
	private int posicion;
	private EstadoUbicacion estado;
	private int capacidad;
	private int capacidadInicial;

	public Ubicacion(String Nombre, String calle, int bloque, int estante, int posicion, EstadoUbicacion estado, int capacidad) {
		this.nombre=Nombre;
		this.calle = calle;
		this.bloque = bloque;
		this.estante = estante;
		this.posicion = posicion;
		this.estado = estado;
		this.capacidad = capacidad;
		this.capacidadInicial = capacidad; // Cuando creo la ubicación, seteo la capacidad inicial.
	}

	public Ubicacion() {
		// TODO Auto-generated constructor stub
	}

	public int getIdUbicacion() {
		return idUbicacion;
	}
	public void setIdUbicacion(int idUbicacion){
		this.idUbicacion=idUbicacion;
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
	
	public void save() {
		UbicacionDAO.getInstancia().save(this);
	}

	public int getCapacidadInicial() {
		return capacidadInicial;
	}

	public void setCapacidadInicial(int capacidadInicial) {
		this.capacidadInicial = capacidadInicial;
	}
	
	public void actualizarUbicacion(int cantidadRestante) {
		if (cantidadRestante >= this.getCapacidad()) {
			this.setCapacidad(0);
			this.setEstado(EstadoUbicacion.Libre);
		} else {
			this.setCapacidad(this.getCapacidad() - cantidadRestante);
		}
		this.save();
	}
	
	
}
