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

import edu.uade.apdzpoc.enums.EstadoItemPedido;

public class Almacen {
	private static Almacen instancia;

	private Almacen() {

	}

	public static Almacen getInstancia() {
		if (instancia == null)
			instancia = new Almacen();
		return instancia;
	}

	public boolean alcanzaStockPedido(PedidoWeb pedidoWeb) {

		List<ItemPedido> itemsPedidos = pedidoWeb.getItems();
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
	
	public List<Movimiento> crearMovimientos(PedidoWeb pedidoWeb) {
		List<Movimiento> result = new ArrayList<>();
		for(ItemPedido item : pedidoWeb.getItems()) {
			if (item.getEstado() == EstadoItemPedido.Con_Stock) {
				result.add(item.getArticulo().crearMovimientoPedido(item.getCantidad(), pedidoWeb));
			} else {
				List<OrdenCompra> loc = Compras.getInstancia().crearOrdenesCompra(item, pedidoWeb); // Genero las OC
				result.add(item.getArticulo().crearMovimientoCompra(loc.get(0).getCantidad() * loc.size(), pedidoWeb.getFechaGeneracion())); // Por si la cantidad supera más 100% la cantidad de pedido
			}
		}
		return result;
	}
	
	public MovimientoCompra crearMovimiento(OrdenCompra oc) {
		 return oc.getArticulo().crearMovimientoCompra(oc);
	}
}
