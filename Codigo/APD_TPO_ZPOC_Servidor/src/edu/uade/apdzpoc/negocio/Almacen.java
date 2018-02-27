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
import java.util.List;
import java.util.UUID;

import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.DestinoArticulos;
import edu.uade.apdzpoc.enums.EstadoItemPedido;
import edu.uade.apdzpoc.enums.EstadoRemito;
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
		// Devuelve las ubicaciones correspondientes a la cantidad de articulos pedidos en ese item
		return itemPedido.getArticulo().obtenerUbicacionesItemsALiberar(itemPedido.getCantidad());
	}

	public void crearMovimientos(PedidoWeb pw) throws ArticuloException, ArticuloProveedorException, ProveedorException {

		List<Movimiento> movimientos = new ArrayList<>();

		for (ItemPedido item : pw.getItems()) {
			if (item.getEstado() == EstadoItemPedido.Con_Stock) {
				movimientos.add(item.crearMovimientoPedido(pw));
			} else {
				List<OrdenCompra> loc = Compras.getInstancia().crearOrdenesCompraPorItem(item, pw); // Genero las OC
				for (OrdenCompra oc : loc)
					movimientos.add(oc.getArticulo().crearMovimientoCompra(oc));
			}
		}

		for (Movimiento m : movimientos)
				m.actualizarNovedadStock();
	}

	public MovimientoCompra crearMovimiento(OrdenCompra oc) {
		return oc.getArticulo().crearMovimientoCompra(oc);
	}

	public void asignarUbicacionesArticulos(OrdenCompra oc) throws LoteException, UbicacionException {
		List<ItemRemitoAlmacen> ira = oc.asignarUbicacionesArticulos();
		this.crearRemitoAlmacen(ira, oc); // Esto genera el remito pendiente, despues en la GUI de Almacen se listan los mismos para que el empleado los marque a "mano" como EstadoRemito.Procesado
	}

	public void generarRemitos(PedidoWeb pw) {
		new RemitoAlmacen(pw).save();
	}
	
	private TipoRemitoAlmacen causaMapper(CausaAjuste causa) {
		TipoRemitoAlmacen tipo = null;
		if (CausaAjuste.Vecimiento == causa) {
			tipo = TipoRemitoAlmacen.Vencimiento;
		} else if (CausaAjuste.Rotura == causa) {
			tipo = TipoRemitoAlmacen.Rotura;
		} else if (CausaAjuste.Existencias_Negativas == causa) {
			tipo = TipoRemitoAlmacen.Negativo;
		} else if (CausaAjuste.Existencias_Positivas == causa) {
			tipo = TipoRemitoAlmacen.Positivo;
		}
		return tipo;
	}

	private void crearRemitoAlmacen(MovimientoAjuste ma, int numero) {
		RemitoAlmacen ra = new RemitoAlmacen();
		ra.setEstado(EstadoRemito.Procesado);
		ra.generarItemsRemito(ma);
		ra.setNro(numero);
		ra.setTipo(causaMapper(ma.getCausa()));
		ra.save();
	}

	public void crearRemitoAlmacen(List<ItemRemitoAlmacen> ira, OrdenCompra oc) {
		RemitoAlmacen ra = new RemitoAlmacen();
		ra.setEstado(EstadoRemito.Pendiente);
		ra.setItemsRemito(ira);
		ra.setNro(oc.getIdOC());
		ra.setTipo(TipoRemitoAlmacen.Compra);
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

	// La cantidadAjustar será positiva o negativa de acuerdo al tipo de ajuste
	public void actualizarInventario(int cantidadAjustar, CausaAjuste causa, int legajoOperador, int legajoAutorizante, Lote lote, DestinoArticulos destino) throws ArticuloException {

		MovimientoAjuste ma = lote.getArticulo().crearMovimientoAjuste(cantidadAjustar, causa, legajoOperador, legajoAutorizante, destino, lote);
		ma.actualizarNovedadStock();

		this.crearRemitoAlmacen(ma, UUID.randomUUID().hashCode());
	}

	// TODO: Pasar la siguiente funcionalidad a POO. Funcion que se ejecuta cada 30 días.
	public void controlarVencimientos() { }

}
