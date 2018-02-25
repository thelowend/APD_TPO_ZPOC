package edu.uade.apdzpoc.controlador;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

//import edu.uade.apdzpoc.dto.ClienteDTO;
//import edu.uade.apdzpoc.dto.ItemPedidoDTO;

import edu.uade.apdzpoc.negocio.Almacen;
import edu.uade.apdzpoc.negocio.Articulo;
import edu.uade.apdzpoc.negocio.Cliente;
import edu.uade.apdzpoc.negocio.Compras;
import edu.uade.apdzpoc.negocio.Despacho;
import edu.uade.apdzpoc.negocio.Facturacion;
import edu.uade.apdzpoc.negocio.ItemPedido;
import edu.uade.apdzpoc.negocio.Lote;
import edu.uade.apdzpoc.negocio.Movimiento;
import edu.uade.apdzpoc.negocio.MovimientoCompra;
import edu.uade.apdzpoc.negocio.OrdenCompra;
import edu.uade.apdzpoc.negocio.PedidoWeb;
import edu.uade.apdzpoc.enums.EstadoOC;
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
	
	private Controlador() {
	}
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	// En el business delegate, esto será: 
	// public void crearPedidoWeb(List<ItemPedidoDTO> articulos, ClienteDTO cliente, String direccion) {
	public int crearPedidoWeb(List<ItemPedido> articulos, Cliente cliente, String direccion) {
		
		Facturacion facturacion = Facturacion.getInstancia();
		Almacen almacen = Almacen.getInstancia();
		Despacho despacho = Despacho.getInstancia();
		
		PedidoWeb pedidoWeb = new PedidoWeb(cliente, EstadoPedido.Pendiente_Validacion, direccion, articulos);
		
		// Para enviar mensajes al usuario
		ResourceBundle mensajes = ResourceBundle.getBundle("mensajes");
		String mensajeResultado = "";
		
		if (!facturacion.alcanzaLimiteCTA(pedidoWeb)) {
			// Mandar al despacho para actualizar el pedido
			pedidoWeb.setEstadoPedido(EstadoPedido.Rechazado);
			mensajeResultado = mensajes.getString("rechazado");
			// El Despacho NO encola el pedido.
		} else {
			
			if (!almacen.alcanzaStockPedido(pedidoWeb)) {
				pedidoWeb.setEstadoPedido(EstadoPedido.Pendiente_Stock);
				mensajeResultado = mensajes.getString("pendiente_stock");
				
			} else {
				pedidoWeb.setEstadoPedido(EstadoPedido.Pendiente_Despacho);
				mensajeResultado = mensajes.getString("pendiente_despacho");
				
				facturacion.crearFactura(pedidoWeb);
				facturacion.crearRemito(pedidoWeb);
				
			}
			
			// Paso por el almacen para generar los movimientos:
			List<Movimiento> lm = almacen.crearMovimientos(pedidoWeb);
			
			for(Movimiento m : lm) {
				m.actualizarNovedadStock();
			}
			
			// El Despacho encola el pedido.
			despacho.encolarPedidoWeb(pedidoWeb);
		}
		
		// Ver como mandar "mensajeResultado" a la vista.
		
		// Devuelvo el ID del pedido.
		return pedidoWeb.getIdPedido();
	}
	
	// En el business delegate recibe OrdenCompraDTO
	public void ingresarCompra(OrdenCompra oc, EstadoOC estadoOC) {
		Almacen almacen = Almacen.getInstancia();
		
		Compras.getInstancia().validarOrdenCompra(oc, estadoOC);
		
		if (oc.getEstado() == EstadoOC.Aceptada) {
			
			MovimientoCompra mc = almacen.crearMovimiento(oc);
			mc.actualizarNovedadStock();
			
		} else {
			// Rechazada
		}
		
	}
	
	// Lo envío a Facturación para confeccionar la factura:
	// 
	//Acá: la gestión del pago??
	
}
