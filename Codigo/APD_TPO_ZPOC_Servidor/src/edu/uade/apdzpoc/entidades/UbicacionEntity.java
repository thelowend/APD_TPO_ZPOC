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

import edu.uade.apdzpoc.enums.EstadoUbicacion;

@Entity
@Table(name = "Ubicaciones")
public class UbicacionEntity {

	@Id
	@Column(name = "IdUbicacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUbicacion;
	
	@Column(name = "Nombre")
	private String nombre;

	@Column(name = "Calle")
	private String calle;

	@Column(name = "Bloque")
	private int bloque;

	@Column(name = "Estante")
	private int estante;

	@Column(name = "Posicion")
	private int posicion;

	@Column(name = "Estado")
	@Enumerated(EnumType.STRING)
	private EstadoUbicacion estado;

	@Column(name = "Capacidad")
	private int Capacidad;

	@Column(name = "CapacidadInicial")
	private int CapacidadInicial;

	public UbicacionEntity() {

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

	public void setBloque(Integer bloque) {
		this.bloque = bloque;
	}

	public int getEstante() {
		return estante;
	}

	public void setEstante(Integer estante) {
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
		return Capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.Capacidad = capacidad;
	}

	public int getCapacidadInicial() {
		return CapacidadInicial;
	}

	public void setCapacidadInicial(Integer capacidadInicial) {
		this.CapacidadInicial = capacidadInicial;
	}

}
