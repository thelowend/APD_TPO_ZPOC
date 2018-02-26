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

package edu.uade.apdzpoc.controlador;

import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.negocio.Almacen;
import edu.uade.apdzpoc.negocio.Cliente;
import edu.uade.apdzpoc.negocio.Compras;
import edu.uade.apdzpoc.negocio.Despacho;
import edu.uade.apdzpoc.negocio.ItemPedido;
import edu.uade.apdzpoc.negocio.MovimientoCompra;
import edu.uade.apdzpoc.negocio.OrdenCompra;
import edu.uade.apdzpoc.negocio.PedidoWeb;
import edu.uade.apdzpoc.dao.PedidoWebDAO;
import edu.uade.apdzpoc.enums.EstadoItemPedido;
import edu.uade.apdzpoc.enums.EstadoOC;
import edu.uade.apdzpoc.enums.EstadoPedido;

public class Controlador {
	
	private static Controlador instancia;
	
	private Controlador() {}
	
	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	// En el business delegate, esto será:  public int crearPedidoWeb(List<ItemPedidoDTO> articulos, ClienteDTO cliente, String direccion) {
	public int crearPedidoWeb(List<ItemPedido> articulos, Cliente cliente, String direccion) {
		
		PedidoWeb pedidoWeb = new PedidoWeb(cliente, EstadoPedido.Pendiente_Validacion, direccion, articulos);
		
		// El despacho procesará el pedido Web y seteará su estado:
		Despacho.getInstancia().procesarPedidoWeb(pedidoWeb);
		
		// Devuelvo el ID del pedido para el DTO que se enviará a la GUI del cliente:
		return pedidoWeb.getIdPedido();
	}
	
	// Acá desde la GUI el empleado de Despacho despacha el pedido, con la fecha de entrega y la empresa de transporte a cargo:
	public void despacharPedido(PedidoWeb pw, Date fechaEntrega, String empresaTransporte) {
		Despacho.getInstancia().despacharPedido(pw, fechaEntrega, empresaTransporte);
	}
	
	// En el business delegate recibirá OrdenCompraDTO ocDTO, EstadoOC estadoOC
	public void ingresarCompra(OrdenCompra oc, EstadoOC estadoOC) {
		Almacen almacen = Almacen.getInstancia();
		Compras compras = Compras.getInstancia();
		
		// Compras valida el estado de la orden de compra:
		compras.validarOrdenCompra(oc, estadoOC);
		
		if (oc.getEstado() == EstadoOC.Aceptada) {
			// Si es aceptada genero los movimientos correspondientes por cada artículo:
			MovimientoCompra mc = almacen.crearMovimiento(oc);
			mc.actualizarNovedadStock();
			mc.getArticulo().save(); // Guardo el artículo con el stock actualizado y los movimientos nuevos
			
			almacen.asignarUbicacionesArticulos(oc); // Esto genera los remito almacen en pendiente y les asigna ubicaciones en el almacen.
			
			procesarPedidosWeb(oc); // Proceso los pedidos pendientes en base a la oc aceptada, para verificar si ya se pueden completar:
		} else {
			// Si fue rechazada, creo una nueva OC igual a la rechazada (porque la necesidad no desaparece):
			ItemPedido itemPedido = new ItemPedido(oc.getArticulo(), oc.getCantidad(), EstadoItemPedido.Sin_Stock);
			compras.crearOrdenesCompra(itemPedido, oc.getPedidoW());
		}
		
		oc.save(); // Persistimos la OC
		
	}
	
	private void procesarPedidosWeb(OrdenCompra oc) {
		
		// Prioriza el pedido que originó la orden de compra.
		Despacho.getInstancia().procesarPedidoWeb(oc.getPedidoW());
		
		// Si logró completar el pedido priorizado, verifico que otros pedidos puedo despachar:
		if (oc.getPedidoW().getEstadoPedido() != EstadoPedido.Pendiente_Despacho) {
			List<PedidoWeb> pedidosPendientes = PedidoWebDAO.getInstancia().getAllbyArticulo(oc.getArticulo().getCodigoBarra());
			for (PedidoWeb pw : pedidosPendientes) {
				Despacho.getInstancia().procesarPedidoWeb(pw);
			}
		}
	}
	
}
