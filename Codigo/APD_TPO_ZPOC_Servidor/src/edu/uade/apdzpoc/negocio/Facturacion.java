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

import edu.uade.apdzpoc.enums.EstadoPedido;

public class Facturacion {
	private static Facturacion instancia;

	private Facturacion() {

	}

	public static Facturacion getInstancia() {
		if (instancia == null)
			instancia = new Facturacion();
		return instancia;
	}
	
	public boolean alcanzaLimiteCTA(PedidoWeb pedidoWeb) {
		List<ItemPedido> itemsComprados = pedidoWeb.getItems();
		Cliente cliente = pedidoWeb.getCliente();
		float totalCompra = 0f;
		
		for(ItemPedido item : itemsComprados) {
			totalCompra += item.calcularTotal();
		}
		
		return cliente.getCuentaCorriente().getSaldo() > totalCompra;
		
//		if (cliente.getCuentaCorriente().getSaldo() > totalCompra) {
//			// Le seteo el estado del pedido como rechazado y devuelvo falso, señalizando que no pasó la verificación.
//			pedidoWeb.setEstadoPedido(EstadoPedido.Rechazado);
//			return false;
//		} else {
//			// devuelvo true, señalizando que pasó la verificación correctamente.
//			return true;
//		}
	}
	
	public void crearFactura(PedidoWeb pedidoWeb) {
		Date fechaEmision = new Date();
		Date fechaVencimiento = new Date(); // De dónde lo saco? Del artículo?
		String tipoFactura = "A"; // De dónde lo saco? Del cliente?
		
		List<ItemFactura> itemsFactura = new ArrayList<>();
		for(ItemPedido item : pedidoWeb.getItems()) {
			// Esta bien guardar el calculartotal como campo de itemFactura al momento de crearlo,
			// ya que en el futuro podría cambiar el precio del artículo, pero no debería cambiar el precio en la factura emitida.
			itemsFactura.add(new ItemFactura(item.getArticulo(), item.getCantidad(), item.calcularTotal()));
		}
		
		// Creo la nueva factura
		new Factura(pedidoWeb.getCliente(), fechaEmision, fechaVencimiento, tipoFactura, itemsFactura);
		
	}
}
