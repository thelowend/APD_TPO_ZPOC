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

import java.util.List;

import edu.uade.apdzpoc.enums.EstadoPedido;

public class Almacen {
	private static Almacen instancia;

	private Almacen() {
		Compras compras = Compras.getInstancia(); // Almacén conoce a Compras.
	}

	public static Almacen getInstancia() {
		if (instancia == null)
			instancia = new Almacen();
		return instancia;
	}
	
	public boolean verificarStockPedido(PedidoWeb pedidoWeb) {
		Compras compras = Compras.getInstancia();
		List<ItemPedido> itemsPedidos = pedidoWeb.getItems();
		boolean hayStockDeTodosLosItems = true; // Si al iterar sucede que no hay stock de todos los items del pedido, quedará en true.
		
		for(ItemPedido item : itemsPedidos) {
			boolean hayStock = item.getArticulo().getStockDisponible() > item.getCantidad();
			if(!hayStock) {
				//GENERAR MovimientoCompra para ORDEN DE COMPRA
				hayStockDeTodosLosItems = false;
			}
		}
		
		if(!hayStockDeTodosLosItems) {
			pedidoWeb.setEstadoPedido(EstadoPedido.Pendiente_Stock);
			return false;
		} else {
			pedidoWeb.setEstadoPedido(EstadoPedido.Pendiente_Despacho);
			return true;
		}
	}
}
