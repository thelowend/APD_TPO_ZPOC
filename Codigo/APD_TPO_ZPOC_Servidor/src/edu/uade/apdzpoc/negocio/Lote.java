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

public class Lote {

	private int nroLote;
	private Date vencimiento;
	private Articulo articulo;
	private List<Ubicacion> ubicaciones;
	public Lote(int nroLote, Date vencimiento, Articulo articulo, List<Ubicacion> ubicaciones) {
		this.nroLote = nroLote;
		this.vencimiento = vencimiento;
		this.articulo = articulo;
		this.ubicaciones = ubicaciones;
	}
	public Lote() {
		// TODO Auto-generated constructor stub
	}
	public int getNroLote() {
		return nroLote;
	}
	public void setNroLote(int nroLote) {
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
	public void save() {
		LoteDAO.getInstancia().save(this);
	}
	
	
	
}
