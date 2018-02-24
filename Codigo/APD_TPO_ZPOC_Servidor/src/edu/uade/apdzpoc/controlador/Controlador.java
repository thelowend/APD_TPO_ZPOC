package edu.uade.apdzpoc.controlador;

import java.util.List;

//import edu.uade.apdzpoc.dto.ClienteDTO;
//import edu.uade.apdzpoc.dto.ItemPedidoDTO;

import edu.uade.apdzpoc.negocio.Almacen;
import edu.uade.apdzpoc.negocio.Cliente;
import edu.uade.apdzpoc.negocio.Compras;
import edu.uade.apdzpoc.negocio.Despacho;

import edu.uade.apdzpoc.negocio.ItemPedido;
import edu.uade.apdzpoc.negocio.PedidoWeb;

import edu.uade.apdzpoc.enums.EstadoPedido;

/**
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


public class Controlador {
	
	private static Controlador instancia;
	
	private Controlador(){ }
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	// En el business delegate, esto será: 
	// public void crearPedidoWeb(List<ItemPedidoDTO> articulos, ClienteDTO cliente) {
	public void crearPedidoWeb(List<ItemPedido> articulos, Cliente cliente) {
		
		Compras compras = Compras.getInstancia();
		Despacho despacho = Despacho.getInstancia();
		Almacen almacen = Almacen.getInstancia();
		
		PedidoWeb pedidoWeb = new PedidoWeb(cliente, EstadoPedido.Pendiente_Validacion, "Calle Falsa 123", articulos);
		
		if (!compras.verificarClienteSaldoPedido(pedidoWeb)) {
			// TODO: Hacer lo que corresponde cuando no pasa esta verificación:
			// Informar al usuario, etc. Razón: “No puede realizar el pedido, debe regularizar los pagos adeudados”
			// si está rechazado no lo encola.
			return;
		}
		
		if (!almacen.verificarStockPedido(pedidoWeb)) {
			// Acá ya no hay return/interrupción porque vamos a encolar el pedido
			// Mensaje: "Su pedido está siendo procesado. Ingrese luego para verificar el estado de este."
		} else {
			// Mensaje: Su pedido será entregado dentro de los 30 días.
		}
		
		// Encolo el pedido que creamos
		despacho.encolarPedidoWeb(pedidoWeb);
			
	}
	
}
