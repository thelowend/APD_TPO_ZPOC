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

public class Compras {
	private static Compras instancia;

	private Compras() {}

	public static Compras getInstancia() {
		if (instancia == null)
			instancia = new Compras();
		return instancia;
	}

	public boolean verificarClienteSaldoPedido(PedidoWeb pedidoWeb) {
		// TODO Auto-generated method stub
		List<ItemPedido> itemsComprados = pedidoWeb.getItems();
		Cliente cliente = pedidoWeb.getCliente();
		float totalCompra = 0f;
		
		for(ItemPedido item : itemsComprados) {
			totalCompra += item.calcularTotal();
		}
		
		if (cliente.getCuentaCorriente().getSaldo() > totalCompra) {
			// Le seteo el estado del pedido como rechazado y devuelvo falso, señalizando que no pasó la verificación.
			pedidoWeb.setEstadoPedido(EstadoPedido.Rechazado);
			return false;
		} else {
			// devuelvo true, señalizando que pasó la verificación correctamente.
			return true;
		}
		
	}
}
