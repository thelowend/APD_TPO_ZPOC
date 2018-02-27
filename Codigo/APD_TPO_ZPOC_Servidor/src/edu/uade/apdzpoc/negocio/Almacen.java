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

import edu.uade.apdzpoc.dao.ArticuloDAO;
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
		return pw.hayStockDeItems();
	}
	
	public List<Ubicacion> buscarUbicaciones(ItemPedido itemPedido) {
		// Devolver� las ubicaciones correspondientes a la cantidad de art�culos pedidos en ese item
		return itemPedido.getArticulo().obtenerUbicacionesItemsALiberar(itemPedido.getCantidad());
	}
	
	public void crearMovimientos(PedidoWeb pw) throws ArticuloException, ArticuloProveedorException, ProveedorException {
		
		List<Movimiento> movimientos = new ArrayList<>();
		
		for(ItemPedido item : pw.getItems()) {
			if (item.getEstado() == EstadoItemPedido.Con_Stock) {
				movimientos.add(item.crearMovimientoPedido(pw));	
			} else {
				List<OrdenCompra> loc = Compras.getInstancia().crearOrdenesCompraPorItem(item, pw); // Genero las OC
				for (OrdenCompra oc : loc) {
					movimientos.add(oc.getArticulo().crearMovimientoCompra(oc));
				}
			}
		}
		
		for (Movimiento m : movimientos) {
			m.actualizarNovedadStock();
		}
	}
	
	public MovimientoCompra crearMovimiento(OrdenCompra oc) {
		 return oc.getArticulo().crearMovimientoCompra(oc);
	}
	
	public void asignarUbicacionesArticulos(OrdenCompra oc) throws LoteException, UbicacionException {
		List<ItemRemitoAlmacen> ira = oc.asignarUbicacionesArticulos();
		this.crearRemitoAlmacen(ira, oc); // Esto genera el remito pendiente, despu�s en la GUI de Almacen se listan los mismos para que el empleado los marque a "mano" como EstadoRemito.Procesado 
	}
	
	
	public void generarRemitos(PedidoWeb pw) {
		new RemitoAlmacen(pw).save();
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
	
//	public Ubicacion getUbicacionLibre(Lote loteArticulo) throws LoteException, UbicacionException {
//		Ubicacion ubicacionAux = null;
//		
//		// Me fijo si existe el lote
//		Lote loteAux = LoteDAO.getInstancia().findByNro(loteArticulo.getNroLote());
//		
//		// Si el lote existe, verifico si alguna de sus ubicaciones tiene capacidad:
//		if (loteAux != null) {
//			
//			List<Ubicacion> ubicacionesDelLote = loteAux.getUbicaciones();
//			
//			// Salgo tan pronto como encuentro una ubicaci�n con disponibilidad:
//			for(int i = 0; ubicacionAux == null && i < ubicacionesDelLote.size(); i++) {
//				Ubicacion u = ubicacionesDelLote.get(i);
//				if (u.getEstado() == EstadoUbicacion.Con_disponibilidad) {
//					ubicacionAux = u;
//				}
//			}
//			
//			// Si NO encontr� una ubicacion con disponibilidad:
//			if (ubicacionAux == null) {
//				// Asigno ubicaci�n libre al lote:
//				ubicacionAux = getUbicacionLibre();
//				ubicacionAux.setEstado(EstadoUbicacion.Con_disponibilidad); // Determino que la ubicaci�n tiene disponibilidad
//				ubicacionAux.save(); // Persisto la ubicaci�n
//				loteAux.addUbicacion(ubicacionAux);
//				loteAux.save(); //Persisto el lote para que quede la ubicaci�n asignada.
//			}
//				
//		} else {
//			// Si el lote no existe, directamente le asigno una ubicaci�n libre al mismo
//			ubicacionAux = getUbicacionLibre();
//			ubicacionAux.setEstado(EstadoUbicacion.Con_disponibilidad); // Determino que la ubicaci�n tiene disponibilidad
//			ubicacionAux.save(); // Persisto la ubicaci�n
//			loteArticulo.addUbicacion(ubicacionAux);
//			loteArticulo.save();
//		}
//		
//		return ubicacionAux;
//		
//	}
//	
//	public Ubicacion getUbicacionLibre() throws UbicacionException {
//		return UbicacionDAO.getInstancia().getUbicacionLibre();
//	}
	
	// Funcion que se ejecuta cada 30 d�as.
	public void controlarVencimientos() {
		List<Lote> lotes = LoteDAO.getInstancia().getAllByVencimiento();

		// Fecha actual + 1 mes (Para verificar los que se vencen dentro de los pr�ximos 30 d�as)
		Date fechaVencimiento = Date.from(LocalDate.now().plusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		boolean fechaEsAnterior = false;
		for (int i = 0; !fechaEsAnterior && i < lotes.size(); i++) {
			Lote lote = lotes.get(i);
			
			if (fechaVencimiento.after(lote.getVencimiento())) {
				// Si el lote esta vencido:
				int legajoOperador = 0;   // Legajo para proceso autom�tico, no hay "operador" en verdad.
				int legajoAutorizante = 0; // qui�n lo autoriza?
				
				List<ItemRemitoAlmacen> ira = new ArrayList<>();
				
				int cantidadArticulosVencidos = 0;
				for (Ubicacion ubicacionVencida : lote.getUbicaciones()) {
					// Por cada ubicaci�n del lote vencido, voy creando los items del remito almac�n para lotes vencidos.
					int cantidadArticulosVencidosEnUbicacion = ubicacionVencida.getCapacidadInicial() - ubicacionVencida.getCapacidad(); // Otengo la cantidad de items en el lote
					ira.add(new ItemRemitoAlmacen(lote.getArticulo(), cantidadArticulosVencidos, ubicacionVencida));
					cantidadArticulosVencidos += cantidadArticulosVencidosEnUbicacion;
				}
				
				// Creo el Movimiento de ajuste negativo
				lote.getArticulo().crearMovimientoAjuste(cantidadArticulosVencidos, CausaAjuste.Vecimiento, legajoOperador, legajoAutorizante, DestinoArticulos.Destruccion, lote);
				
				// Creo el remito vencido. Cuando se procese en el futuro, se liberar�n las ubicaciones en donde estaba dicho lote.
				crearRemitoAlmacen(ira, lote);
				
			} else {
				fechaEsAnterior = true; // Dejo de revisar los lotes si la fecha de vencimiento alcanzada ya es anterior a la actual.
			}
		}
	}

	
	
	//propiedad que se utiliza para realizar ajustes en las existencias del stock
	public void actualizarInventario(int cantidad, CausaAjuste causa, int legajoOperador, int legajoAutorizante, Lote lote, DestinoArticulos destino) throws ArticuloException {
		Articulo a = ArticuloDAO.getInstancia().findByLote(lote.getNroLote());
		a.crearMovimientoAjuste(cantidad, causa, legajoOperador, legajoAutorizante, destino, lote);
		
	}
}
