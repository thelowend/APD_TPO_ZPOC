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

import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.dao.LoteDAO;
import edu.uade.apdzpoc.enums.EstadoUbicacion;
import edu.uade.apdzpoc.excepciones.UbicacionException;

public class Lote {

	private Integer nroLote;
	private Date vencimiento;
	private Articulo articulo;
	private List<Ubicacion> ubicaciones;

	public Lote(Date vencimiento, Articulo articulo, List<Ubicacion> ubicaciones) {
		this.vencimiento = vencimiento;
		this.articulo = articulo;
		this.ubicaciones = ubicaciones;
	}

	public Lote() {
		// TODO Auto-generated constructor stub
	}

	public Integer getNroLote() {
		return nroLote;
	}

	public void setNroLote(Integer nroLote) {
		this.nroLote = nroLote;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public void addUbicacion(Ubicacion ubicacion) {
		this.ubicaciones.add(ubicacion);
	}

	public void removeUbicacion(Ubicacion ubicacion) {
		this.ubicaciones.remove(ubicacion);
	}

	public Ubicacion getMejorUbicacion() {

		List<Ubicacion> ubicaciones = this.getUbicaciones();
		Ubicacion mejor = ubicaciones.get(0);

		//Empiezo desde la segunda porque ya tomï¿½ la primera
		for (int i = 1; i < ubicaciones.size(); i++) {
			if(ubicaciones.get(i).getCapacidad() < mejor.getCapacidad()) {
				mejor = ubicaciones.get(i);
			}
		}

		return mejor;
	}
	
	public Ubicacion getUbicacionConCapacidad() throws UbicacionException {

		Ubicacion ubicacion = null;
		boolean encontrada = false;
		
		for (int i = 1; !encontrada && i < ubicaciones.size(); i++) {
			if(ubicaciones.get(i).getEstado() == EstadoUbicacion.Con_disponibilidad) {
				encontrada = true;
				ubicacion = ubicaciones.get(i);
			}
		}
		
		if (ubicacion == null) {
			ubicacion = Ubicacion.obtenerUbicacionLibre(); // Si el lote no tenía ninguna ubicación con capacidad...
			this.addUbicacion(ubicacion);
			ubicacion.setEstado(EstadoUbicacion.Con_disponibilidad);
			this.save();
		}
		return ubicacion;
	}

	public void save() {
		LoteDAO.getInstancia().save(this);
	}

//	public void actualizarUbicacion(Ubicacion u, int cantidadRestante) {
//		if (cantidadRestante >= u.getCapacidad()) {
//			u.setCapacidad(0);
//			u.setEstado(EstadoUbicacion.Libre);
//			this.removeUbicacion(u);
//		} else {
//			u.setCapacidad(u.getCapacidad() - cantidadRestante);
//		}
//	}


}
