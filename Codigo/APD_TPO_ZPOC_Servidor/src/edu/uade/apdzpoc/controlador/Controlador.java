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
import edu.uade.apdzpoc.negocio.Facturacion;
import edu.uade.apdzpoc.negocio.ItemPedido;
import edu.uade.apdzpoc.negocio.Lote;
import edu.uade.apdzpoc.negocio.MovimientoCompra;
import edu.uade.apdzpoc.negocio.OrdenCompra;
import edu.uade.apdzpoc.negocio.PagoCliente;
import edu.uade.apdzpoc.negocio.PedidoWeb;
import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.DestinoArticulos;
import edu.uade.apdzpoc.enums.EstadoItemPedido;
import edu.uade.apdzpoc.enums.EstadoOC;
import edu.uade.apdzpoc.enums.EstadoPedido;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.LoteException;
import edu.uade.apdzpoc.excepciones.ProveedorException;
import edu.uade.apdzpoc.excepciones.UbicacionException;

public class Controlador {
	
	private static Controlador instancia;
	
	private Controlador() {}
	
	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	// En el business delegate, esto será:  public int crearPedidoWeb(List<ItemPedidoDTO> articulos, ClienteDTO cliente, String direccion) {
	public int crearPedidoWeb(List<ItemPedido> articulos, Cliente cliente, String direccion) throws ArticuloException, ArticuloProveedorException, ProveedorException {
		
		PedidoWeb pedidoWeb = new PedidoWeb(cliente, EstadoPedido.Pendiente_Validacion, direccion, articulos).saveAndFetch();
		
		// El despacho procesará el pedido Web y el mismo quedará en el estado correspondiente:
		Despacho.getInstancia().procesarPedidoWeb(pedidoWeb);
		
		// Devuelvo el ID del pedido para el DTO que se enviará a la GUI del cliente:
		return pedidoWeb.getIdPedido();
	}
	
	public void despacharPedido(PedidoWeb pw, Date fechaEntrega, String empresaTransporte) {
		Despacho.getInstancia().despacharPedido(pw, fechaEntrega, empresaTransporte);
	}
	
	// Acá desde la GUI el empleado de Despacho despacha el pedido, con la fecha de entrega y la empresa de transporte a cargo:
	
	// TODO: Add exception
	public List<PedidoWeb> obtenerPedidosParaDespachar() {
		return Despacho.getInstancia().obtenerPedidosParaDespachar();
	}
	
	// TODO: Add exception
	public List<OrdenCompra> obtenerOrdenesdeCompraParaValidar() {
		return Compras.getInstancia().obtenerOCParaValidar();
	}
	
	// En el business delegate recibirá OrdenCompraDTO ocDTO, EstadoOC estadoOC
	public void ingresarCompra(OrdenCompra oc, EstadoOC estadoOC) throws LoteException, UbicacionException, ArticuloException, ArticuloProveedorException, ProveedorException {
		Almacen almacen = Almacen.getInstancia();
		Compras compras = Compras.getInstancia();
		
		// Compras valida el estado de la orden de compra:
		compras.validarOrdenCompra(oc, estadoOC);
		
		if (oc.getEstado() == EstadoOC.Aceptada) {
			// Si es aceptada genero los movimientos correspondientes por cada artículo:
			MovimientoCompra mc = almacen.crearMovimiento(oc);
			mc.actualizarNovedadStock();
			
			almacen.asignarUbicacionesArticulos(oc); // Esto genera los remito almacen en pendiente y les asigna ubicaciones en el almacen.
			
			procesarPedidosWeb(oc); // Proceso los pedidos pendientes en base a la oc aceptada, para verificar si ya se pueden completar:
		} else {
			// Si fue rechazada, creo una nueva OC igual a la rechazada (porque la necesidad no desaparece):
			ItemPedido itemPedido = new ItemPedido(oc.getArticulo(), oc.getCantidad(), EstadoItemPedido.Sin_Stock);
			compras.crearOrdenesCompraPorItem(itemPedido, oc.getPedidoW());
		}
		
		oc.save(); // Persistimos la OC
		
	}
	// Ingresar pago de cliente para poder actualizar su cuenta corriente
	
	public void ingresarPagoCliente(PagoCliente pago) {
		Facturacion.getInstancia().ingresarPagoCliente(pago);
	}
	
	// En el bd vamos a recibir loteDTO, etc. 
	public void ajustarInventario(int cantidad, CausaAjuste causa, int legajoOperador, int legajoAutorizante, Lote lote, DestinoArticulos destino) throws ArticuloException {
		Almacen.getInstancia().actualizarInventario(cantidad, causa, legajoOperador, legajoAutorizante, lote, destino);
	}
	
	// Cada 30 días el Almacén controla automáticamente los vencimientos:
	public void controlarVencimientos() {
		Almacen.getInstancia().controlarVencimientos();
	}
	
	private void procesarPedidosWeb(OrdenCompra oc) throws ArticuloException, ArticuloProveedorException, ProveedorException {
		
		PedidoWeb pedidoOriginal = oc.getPedidoW();
		// Prioriza el pedido que originó la orden de compra.
		Despacho.getInstancia().procesarPedidoWeb(pedidoOriginal);
		
		// Si logró completar el pedido priorizado, verifico que otros pedidos puedo despachar:
		if (pedidoOriginal.getEstadoPedido() != EstadoPedido.Pendiente_Despacho) {
			
			List<PedidoWeb> pedidosPendientes = oc.getArticulo().traerPedidosPendientes();
			
			for (PedidoWeb pw : pedidosPendientes) {
				Despacho.getInstancia().procesarPedidoWeb(pw);
			}
		}
	}
	
}
