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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.dao.LoteDAO;
import edu.uade.apdzpoc.dao.OrdenCompraDAO;
import edu.uade.apdzpoc.dao.UbicacionDAO;
import edu.uade.apdzpoc.enums.EstadoOC;
import edu.uade.apdzpoc.enums.EstadoUbicacion;
import edu.uade.apdzpoc.excepciones.LoteException;
import edu.uade.apdzpoc.excepciones.UbicacionException;

public class OrdenCompra {

	private Integer idOC;
	private Proveedor proveedor;
	private int cantidad;
	private EstadoOC estado;
	private Articulo articulo;
	private Lote lote;
	private PedidoWeb pedidoW;
	private Date fecha;

	public OrdenCompra(Proveedor proveedor, Articulo articulo, PedidoWeb pedidoW) {
		this.proveedor = proveedor;
		this.cantidad = articulo.getCantidadCompra();
		this.articulo = articulo;
		this.pedidoW = pedidoW;
		this.estado = EstadoOC.Pendiente;
	}

	public OrdenCompra() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdOC() {
		return idOC;
	}

	public void setIdOC(Integer idOC) {
		this.idOC = idOC;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public EstadoOC getEstado() {
		return estado;
	}

	public void setEstado(EstadoOC estado) {
		this.estado = estado;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public PedidoWeb getPedidoW() {
		return pedidoW;
	}

	public void setPedidoW(PedidoWeb pedidoW) {
		this.pedidoW = pedidoW;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void save() {
		OrdenCompraDAO.getInstancia().save(this);
	}

	public static List<OrdenCompra> obtenerOCParaValidar() {
		return OrdenCompraDAO.getInstancia().findByEstado(EstadoOC.Pendiente);
	}

	public List<ItemRemitoAlmacen> asignarUbicacionesArticulos() throws UbicacionException {
		
		Lote loteArticulo = this.getLote();
		int cantArticulosSinUbicacion = this.getCantidad();
		
		List<ItemRemitoAlmacen> itemsRemitoAlmacen = new ArrayList<>();		
		
		while(cantArticulosSinUbicacion > 0) { 
			
			Ubicacion uAux = loteArticulo.getUbicacionConCapacidad(); 
			
			
			if (uAux.getCapacidad() <= cantArticulosSinUbicacion) {
				cantArticulosSinUbicacion =- uAux.getCapacidad(); 
				uAux.setCapacidad(0);
				uAux.setEstado(EstadoUbicacion.Completa);
				itemsRemitoAlmacen.add(new ItemRemitoAlmacen(this.getArticulo(), uAux.getCapacidad(), uAux));
			} else {
				uAux.setCapacidad(uAux.getCapacidad() - cantArticulosSinUbicacion);
				itemsRemitoAlmacen.add(new ItemRemitoAlmacen(this.getArticulo(), cantArticulosSinUbicacion, uAux));
				cantArticulosSinUbicacion = 0;
			}
			
			uAux.save();
		}
		
		return itemsRemitoAlmacen;
		
	}
	
	public Ubicacion getUbicacionLibre(Lote loteArticulo) throws LoteException, UbicacionException {
		Ubicacion ubicacionAux = null;
		
		// Me fijo si existe el lote
		Lote loteAux = LoteDAO.getInstancia().findByNro(loteArticulo.getNroLote());
		
		// Si el lote existe, verifico si alguna de sus ubicaciones tiene capacidad:
		if (loteAux != null) {
			
			List<Ubicacion> ubicacionesDelLote = loteAux.getUbicaciones();
			
			// Salgo tan pronto como encuentro una ubicaci�n con disponibilidad:
			for(int i = 0; ubicacionAux == null && i < ubicacionesDelLote.size(); i++) {
				Ubicacion u = ubicacionesDelLote.get(i);
				if (u.getEstado() == EstadoUbicacion.Con_disponibilidad) {
					ubicacionAux = u;
				}
			}
			
			// Si NO encontr� una ubicacion con disponibilidad:
			if (ubicacionAux == null) {
				// Asigno ubicaci�n libre al lote:
				ubicacionAux = getUbicacionLibre();
				ubicacionAux.setEstado(EstadoUbicacion.Con_disponibilidad); // Determino que la ubicaci�n tiene disponibilidad
				ubicacionAux.save(); // Persisto la ubicaci�n
				loteAux.addUbicacion(ubicacionAux);
				loteAux.save(); //Persisto el lote para que quede la ubicaci�n asignada.
			}
				
		} else {
			// Si el lote no existe, directamente le asigno una ubicaci�n libre al mismo
			ubicacionAux = getUbicacionLibre();
			ubicacionAux.setEstado(EstadoUbicacion.Con_disponibilidad); // Determino que la ubicaci�n tiene disponibilidad
			ubicacionAux.save(); // Persisto la ubicaci�n
			loteArticulo.addUbicacion(ubicacionAux);
			loteArticulo.save();
		}
		
		return ubicacionAux;
		
	}
	
	public Ubicacion getUbicacionLibre() throws UbicacionException {
		return UbicacionDAO.getInstancia().getUbicacionLibre();
	}
	
	

}
