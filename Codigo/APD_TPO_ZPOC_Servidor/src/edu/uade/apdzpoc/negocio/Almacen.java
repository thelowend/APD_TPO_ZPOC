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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.dao.LoteDAO;
import edu.uade.apdzpoc.dao.UbicacionDAO;
import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.DestinoArticulos;
import edu.uade.apdzpoc.enums.EstadoItemPedido;
import edu.uade.apdzpoc.enums.EstadoRemito;
import edu.uade.apdzpoc.enums.EstadoUbicacion;
import edu.uade.apdzpoc.enums.TipoRemitoAlmacen;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.LoteException;
import edu.uade.apdzpoc.excepciones.ProveedorException;
import edu.uade.apdzpoc.excepciones.UbicacionException;

public class Almacen {
	private static Almacen instancia;

	private Almacen() {

	}

	public static Almacen getInstancia() {
		if (instancia == null)
			instancia = new Almacen();
		return instancia;
	}

	public boolean alcanzaStockPedido(PedidoWeb pw) {

		List<ItemPedido> itemsPedidos = pw.getItems();
		boolean hayStockDeTodosLosItems = true; // Si al iterar sucede que no hay stock de todos los items del pedido,
												// quedará en true.

		for (ItemPedido item : itemsPedidos) {
			boolean hayStock = item.getArticulo().getStockDisponible() > item.getCantidad();
			if (!hayStock) {
				item.setEstado(EstadoItemPedido.Sin_Stock);
				hayStockDeTodosLosItems = false;
			} else {
				item.setEstado(EstadoItemPedido.Con_Stock);
			}
		}

		return hayStockDeTodosLosItems;
	}
	
	public List<Movimiento> crearMovimientos(PedidoWeb pw) throws ArticuloException, ArticuloProveedorException, ProveedorException {
		List<Movimiento> result = new ArrayList<>();
		for(ItemPedido item : pw.getItems()) {
			if (item.getEstado() == EstadoItemPedido.Con_Stock) {
				result.add(item.getArticulo().crearMovimientoPedido(item.getCantidad(), pw));
			} else {
				List<OrdenCompra> loc = Compras.getInstancia().crearOrdenesCompra(item, pw); // Genero las OC
				result.add(item.getArticulo().crearMovimientoCompra(loc.get(0).getCantidad() * loc.size(), pw.getFechaGeneracion())); // Por si la cantidad supera más 100% la cantidad de pedido
			}
		}
		return result;
	}
	
	public MovimientoCompra crearMovimiento(OrdenCompra oc) {
		 return oc.getArticulo().crearMovimientoCompra(oc);
	}
	
	public void asignarUbicacionesArticulos(OrdenCompra oc) throws LoteException, UbicacionException {
		Lote loteArticulo = oc.getLote();
		int cantArticulosSinUbicacion = oc.getCantidad();
		List<ItemRemitoAlmacen> itemsRemitoAlmacen = new ArrayList<>();		
		
		while(cantArticulosSinUbicacion > 0) { 
			
			Ubicacion uAux = this.getUbicacionLibre(loteArticulo);
			if (uAux.getCapacidad() <= cantArticulosSinUbicacion) {
				cantArticulosSinUbicacion =- uAux.getCapacidad(); 
				uAux.setCapacidad(0);
				uAux.setEstado(EstadoUbicacion.Completa);
				itemsRemitoAlmacen.add(new ItemRemitoAlmacen(oc.getArticulo(), uAux.getCapacidad(), uAux));
			} else {
				uAux.setCapacidad(uAux.getCapacidad() - cantArticulosSinUbicacion);
				itemsRemitoAlmacen.add(new ItemRemitoAlmacen(oc.getArticulo(), cantArticulosSinUbicacion, uAux));
				cantArticulosSinUbicacion = 0;
			}
			
			this.crearRemitoAlmacen(itemsRemitoAlmacen, oc); // Esto genera el remito pendiente, después en la GUI de Almacen se listan los mismos para que el empleado los marque a "mano" como EstadoRemito.Procesado 
			uAux.save();
			
			//loteArticulo.save();
		}
		
	}
	
	public void buscarUbicacionesArticulos(PedidoWeb pw) {

		List<ItemPedido> itemsPedidos = pw.getItems();
		List<ItemRemitoAlmacen> itemsRemitoAlmacen = new ArrayList<>();
		
		
		for (ItemPedido item : itemsPedidos) {
			int cantidadAobtener = item.getCantidad();
			List<Lote> lotesArticulo = LoteDAO.getInstancia().getAllByArticulo(item.getArticulo()); // Obtengo los lotes del articulo ordenados por el vencimiento
			
			for(Lote lote : lotesArticulo) {
				for(Ubicacion ubicacion: lote.getUbicaciones()) {
 
					while(cantidadAobtener > 0) {
						
						int cantidadArticulosEnLote = ubicacion.getCapacidadInicial() - ubicacion.getCapacidad();
						
						if (cantidadAobtener < cantidadArticulosEnLote) {
							ubicacion.setCapacidad(ubicacion.getCapacidad() - cantidadAobtener);
							itemsRemitoAlmacen.add(new ItemRemitoAlmacen(item.getArticulo(), cantidadAobtener, ubicacion));
							cantidadAobtener = 0;
						} else {
							ubicacion.setCapacidad(ubicacion.getCapacidadInicial()); // La ubicación queda vacía
							cantidadAobtener -= ubicacion.getCapacidadInicial(); // Le resto a los articulos toda la capacidad de la ubicación
							
							// Libero la ubicación y la disasocio del lote
							ubicacion.setEstado(EstadoUbicacion.Libre);
							lote.removeUbicacion(ubicacion);
							lote.save();
							
							itemsRemitoAlmacen.add(new ItemRemitoAlmacen(item.getArticulo(), ubicacion.getCapacidadInicial(), ubicacion));
						}
						ubicacion.save();
					}
					
				}
			}
			
			// Creo el remito correspondiente
			this.crearRemitoAlmacen(itemsRemitoAlmacen, pw);
			
		}
	}
	
	public void crearRemitoAlmacen(List<ItemRemitoAlmacen> ira, OrdenCompra oc) {
		RemitoAlmacen ra = new RemitoAlmacen();
		ra.setEstado(EstadoRemito.Pendiente);
		ra.setItemsRemito(ira);
		ra.setNro(oc.getIdOC());
		ra.setTipo(TipoRemitoAlmacen.Compra);
		//ra.setIdRemito(java.util.UUID.randomUUID()); // Se supone que la base lo genera
		
		ra.save();
	}
	
	public void crearRemitoAlmacen(List<ItemRemitoAlmacen> ira, PedidoWeb pw) {
		RemitoAlmacen ra = new RemitoAlmacen();
		ra.setEstado(EstadoRemito.Pendiente);
		ra.setItemsRemito(ira);
		ra.setNro(pw.getIdPedido());
		ra.setTipo(TipoRemitoAlmacen.PedidoWeb);
		
		ra.save();
	}
	
	public void crearRemitoAlmacen(List<ItemRemitoAlmacen> ira, Lote loteVencido) {
		RemitoAlmacen ra = new RemitoAlmacen();
		ra.setEstado(EstadoRemito.Pendiente);
		ra.setItemsRemito(ira);
		ra.setTipo(TipoRemitoAlmacen.Vencimiento);
		ra.setNro(loteVencido.getNroLote());
		ra.save();
	}
	
	public Ubicacion getUbicacionLibre(Lote loteArticulo) throws LoteException, UbicacionException {
		Ubicacion ubicacionAux = null;
		
		// Me fijo si existe el lote
		Lote loteAux = LoteDAO.getInstancia().findrecuperadoByNro(loteArticulo.getNroLote()); 
		
		// Si el lote existe, verifico si alguna de sus ubicaciones tiene capacidad:
		if (loteAux != null) {
			
			List<Ubicacion> ubicacionesDelLote = loteAux.getUbicaciones();
			
			// Salgo tan pronto como encuentro una ubicación con disponibilidad:
			for(int i = 0; ubicacionAux == null && i < ubicacionesDelLote.size(); i++) {
				Ubicacion u = ubicacionesDelLote.get(i);
				if (u.getEstado() == EstadoUbicacion.Con_disponibilidad) {
					ubicacionAux = u;
				}
			}
			
			// Si NO encontró una ubicacion con disponibilidad:
			if (ubicacionAux == null) {
				// Asigno ubicación libre al lote:
				ubicacionAux = getUbicacionLibre();
				ubicacionAux.setEstado(EstadoUbicacion.Con_disponibilidad); // Determino que la ubicación tiene disponibilidad
				ubicacionAux.save(); // Persisto la ubicación
				loteAux.addUbicacion(ubicacionAux);
				loteAux.save(); //Persisto el lote para que quede la ubicación asignada.
			}
				
		} else {
			// Si el lote no existe, directamente le asigno una ubicación libre al mismo
			ubicacionAux = getUbicacionLibre();
			ubicacionAux.setEstado(EstadoUbicacion.Con_disponibilidad); // Determino que la ubicación tiene disponibilidad
			ubicacionAux.save(); // Persisto la ubicación
			loteArticulo.addUbicacion(ubicacionAux);
			loteArticulo.save();
		}
		
		return ubicacionAux;
		
	}
	
	public Ubicacion getUbicacionLibre() throws UbicacionException {
		return UbicacionDAO.getInstancia().getUbicacionLibre();
	}
	
	// Funcion que se ejecuta cada 30 días.
	public void controlarVencimientos() {
		List<Lote> lotes = LoteDAO.getInstancia().getAllByVencimiento();

		// Fecha actual + 1 mes (Para verificar los que se vencen dentro de los próximos 30 días)
		Date fechaVencimiento = Date.from(LocalDate.now().plusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		boolean fechaEsAnterior = false;
		for (int i = 0; !fechaEsAnterior && i < lotes.size(); i++) {
			Lote lote = lotes.get(i);
			
			if (fechaVencimiento.after(lote.getVencimiento())) {
				// Si el lote esta vencido:
				int legajoOperador = 0;   // Legajo para proceso automático, no hay "operador" en verdad.
				int legajoAutorizante = 0; // quién lo autoriza?
				
				List<ItemRemitoAlmacen> ira = new ArrayList<>();
				
				int cantidadArticulosVencidos = 0;
				for (Ubicacion ubicacionVencida : lote.getUbicaciones()) {
					// Por cada ubicación del lote vencido, voy creando los items del remito almacén para lotes vencidos.
					int cantidadArticulosVencidosEnUbicacion = ubicacionVencida.getCapacidadInicial() - ubicacionVencida.getCapacidad(); // Otengo la cantidad de items en el lote
					ira.add(new ItemRemitoAlmacen(lote.getArticulo(), cantidadArticulosVencidos, ubicacionVencida));
					cantidadArticulosVencidos += cantidadArticulosVencidosEnUbicacion;
				}
				
				// Creo el Movimiento de ajuste negativo
				lote.getArticulo().crearMovimientoAjuste(cantidadArticulosVencidos, new Date(), CausaAjuste.Vecimiento, legajoOperador, legajoAutorizante, DestinoArticulos.Destruccion, lote);
				
				// Creo el remito vencido. Cuando se procese en el futuro, se liberarán las ubicaciones en donde estaba dicho lote.
				crearRemitoAlmacen(ira, lote);
				
			} else {
				fechaEsAnterior = true; // Dejo de revisar los lotes si la fecha de vencimiento alcanzada ya es anterior a la actual.
			}
		}
	}
}
