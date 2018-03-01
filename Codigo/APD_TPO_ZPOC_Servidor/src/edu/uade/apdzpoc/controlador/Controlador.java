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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.negocio.Almacen;
import edu.uade.apdzpoc.negocio.Articulo;
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
import edu.uade.apdzpoc.negocio.RemitoAlmacen;
import edu.uade.apdzpoc.util.DTOMapper;
import edu.uade.apdzpoc.dao.ArticuloDAO;
import edu.uade.apdzpoc.dao.OrdenCompraDAO;
import edu.uade.apdzpoc.dao.PedidoWebDAO;
import edu.uade.apdzpoc.dto.ArticuloDTO;
import edu.uade.apdzpoc.dto.ArticuloStockDTO;
import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.dto.ItemPedidoDTO;
import edu.uade.apdzpoc.dto.LoteDTO;
import edu.uade.apdzpoc.dto.OrdenCompraDTO;
import edu.uade.apdzpoc.dto.PagoClienteDTO;
import edu.uade.apdzpoc.dto.PedidoWebDTO;
import edu.uade.apdzpoc.dto.RemitoAlmacenDTO;
import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.DestinoArticulos;
import edu.uade.apdzpoc.enums.EstadoItemPedido;
import edu.uade.apdzpoc.enums.EstadoOC;
import edu.uade.apdzpoc.enums.EstadoPedido;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.ClienteException;
import edu.uade.apdzpoc.excepciones.FacturaException;
import edu.uade.apdzpoc.excepciones.LoteException;
import edu.uade.apdzpoc.excepciones.OrdenCompraException;
import edu.uade.apdzpoc.excepciones.PedidoWebException;
import edu.uade.apdzpoc.excepciones.ProveedorException;
import edu.uade.apdzpoc.excepciones.RemitoAlmacenException;
import edu.uade.apdzpoc.excepciones.UbicacionException;

public class Controlador {

	private static Controlador instancia;

	private Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	public int crearPedidoWeb(List<ItemPedidoDTO> articulosComprados, ClienteDTO cliente, String direccion)
			throws ArticuloException, ArticuloProveedorException, ProveedorException, ClienteException {

		List<ItemPedido> items = new ArrayList<ItemPedido>();
		for (ItemPedidoDTO i : articulosComprados) {
			ItemPedido ip = new ItemPedido().dtoItemPedidoToNegocio(i);
			items.add(ip);
		}
		Cliente cl = new Cliente().dtoClienteToNegocio(cliente);

		// Cliente cl = DTOMapper.getInstancia().dtoClienteToNegocio(cliente);

		PedidoWeb pedidoWeb = new PedidoWeb(cl, EstadoPedido.Pendiente_Validacion, direccion, items).saveAndFetch();

		// Devuelvo el ID del pedido para el DTO que se enviará a la GUI del cliente:
		return pedidoWeb.getIdPedido();
	}

	public PedidoWeb obtenerPedidoWeb(int idPedido) throws PedidoWebException {
		return Despacho.getInstancia().obtenerPedidoWeb(idPedido);
	}

	public void procesarPedidoWeb(PedidoWebDTO pedidoWeb)
			throws ArticuloException, ArticuloProveedorException, ProveedorException, PedidoWebException {
		// El despacho procesará el pedido Web y el mismo quedará en el estado
		// correspondiente:

		PedidoWeb p = new PedidoWeb().dtoPedidoWebToNegocio(pedidoWeb);

		Despacho.getInstancia().procesarPedidoWeb(p.dtoPedidoWebToNegocio(pedidoWeb));
	}

	public void procesarOrdenCompraPendiente(OrdenCompraDTO oc, EstadoOC estadoOC, LoteDTO lote)
			throws OrdenCompraException, LoteException, ArticuloException {
		// OrdenCompra ordenCompra =
		// DTOMapper.getInstancia().dtoOrdenCompraToNegocio(oc);
		OrdenCompra ordenCompra = new OrdenCompra().dtoOrdenCompraToNegocio(oc);

		// Lote lot = DTOMapper.getInstancia().dtoLotetoNegocio(lote);
		Lote lot = new Lote().dtoLotetoNegocio(lote);
		Compras.getInstancia().validarOrdenCompra(ordenCompra, estadoOC, lot);
	}

	public void despacharPedido(PedidoWebDTO pw, Date fechaEntrega, String empresaTransporte)
			throws PedidoWebException {
		PedidoWeb pedido = new PedidoWeb().dtoPedidoWebToNegocio(pw);

		Despacho.getInstancia().despacharPedido(pedido, fechaEntrega, empresaTransporte);
	}

	// Acá desde la GUI el empleado de Despacho despacha el pedido, con la fecha de
	// entrega y la empresa de transporte a cargo:
	public List<PedidoWebDTO> obtenerPedidosParaDespachar() {

		List<PedidoWebDTO> resultado = new ArrayList<PedidoWebDTO>();
		List<PedidoWeb> pedidosNegocio = new ArrayList<PedidoWeb>();
		pedidosNegocio = Despacho.getInstancia().obtenerPedidosParaDespachar();
		for (PedidoWeb p : pedidosNegocio) {
			// resultado.add(DTOMapper.getInstancia().pedidoWebToDTO(p));
			resultado.add(p.pedidoWebToDTO(p));
		}
		return resultado;
	}

	public List<PedidoWebDTO> obtenerPedidosParaProcesar() {

		List<PedidoWebDTO> resultado = new ArrayList<PedidoWebDTO>();
		List<PedidoWeb> pedidosNegocio = new ArrayList<PedidoWeb>();
		pedidosNegocio = Despacho.getInstancia().obtenerPedidosParaProcesar();
		for (PedidoWeb p : pedidosNegocio) {
			// resultado.add(DTOMapper.getInstancia().pedidoWebToDTO(p));
			resultado.add(p.pedidoWebToDTO(p));
		}
		return resultado;
	}

	// TODO: Add exception
	public List<OrdenCompraDTO> obtenerOrdenesdeCompraParaValidar() {

		List<OrdenCompraDTO> resultado = new ArrayList<OrdenCompraDTO>();
		List<OrdenCompra> ocsNegocio = new ArrayList<OrdenCompra>();
		ocsNegocio = Compras.getInstancia().obtenerOCParaValidar();
		for (OrdenCompra oc : ocsNegocio) {
			resultado.add(oc.ordenCompraToDTO(oc));
			// resultado.add(DTOMapper.getInstancia().ordenCompraToDTO(oc));
		}

		return resultado;
	}

	// TODO: Add exception
	public List<RemitoAlmacen> obtenerRemitosAlmacenParaProcesar() {
		return Almacen.getInstancia().obtenerRemitosParaProcesar();
	}

	public void procesarRemitoAlmacen(RemitoAlmacenDTO remito) throws RemitoAlmacenException {
		RemitoAlmacen ra = new RemitoAlmacen().dtoRemitoAlmacenToNegocio(remito);
		// RemitoAlmacen ra =
		// DTOMapper.getInstancia().dtoRemitoAlmacenToNegocio(remito);
		Almacen.getInstancia().ProcesarRemito(ra);
	}

	public void validarIngresoOrdenCompra(OrdenCompraDTO oc, EstadoOC estadoOC, LoteDTO lote)
			throws LoteException, UbicacionException, ArticuloException, ArticuloProveedorException, ProveedorException,
			OrdenCompraException {
		Almacen almacen = Almacen.getInstancia();
		Compras compras = Compras.getInstancia();
		Lote lot = null;

		// OrdenCompra ordenCompraNegocio =
		// DTOMapper.getInstancia().dtoOrdenCompraToNegocio(oc);
		OrdenCompra ordenCompraNegocio = new OrdenCompra().dtoOrdenCompraToNegocio(oc);
		if (lote == null) {
			lot = lot.dtoLotetoNegocio(lote);
		}

		// Compras valida el estado de la orden de compra:
		compras.validarOrdenCompra(ordenCompraNegocio, estadoOC, lot);

		if (ordenCompraNegocio.getEstado() == EstadoOC.Aceptada) {
			// Si es aceptada genero los movimientos correspondientes por cada artículo:
			MovimientoCompra mc = almacen.crearMovimiento(ordenCompraNegocio);
			mc.actualizarNovedadStock();

			almacen.asignarUbicacionesArticulos(ordenCompraNegocio); // Esto genera los remito almacen en pendiente y
																		// les asigna ubicaciones en el almacen.

			procesarPedidosWeb(ordenCompraNegocio); // Proceso los pedidos pendientes en base a la oc aceptada, para
													// verificar si ya se pueden completar:
		} else {
			// Si fue rechazada, creo una nueva OC igual a la rechazada (porque la necesidad
			// no desaparece):
			ItemPedido itemPedido = new ItemPedido(ordenCompraNegocio.getArticulo(), ordenCompraNegocio.getCantidad(),
					EstadoItemPedido.Sin_Stock);
			compras.crearOrdenesCompraPorItem(itemPedido, ordenCompraNegocio.getPedidoW());
		}

		// ordenCompraNegocio.save(); // Persistimos la OC

	}
	// Ingresar pago de cliente para poder actualizar su cuenta corriente

	public void ingresarPagoCliente(PagoClienteDTO pago) throws FacturaException {
		// PagoCliente p = DTOMapper.getInstancia().dtoPagoClienteToNegocio(pago);
		PagoCliente p = new PagoCliente().dtoPagoClienteToNegocio(pago);

		Facturacion.getInstancia().ingresarPagoCliente(p);
	}

	// En el bd vamos a recibir loteDTO, etc.
	public void ajustarInventario(int cantidad, CausaAjuste causa, int legajoOperador, int legajoAutorizante,
			LoteDTO lote, DestinoArticulos destino) throws ArticuloException, LoteException {
		// Lote lot = DTOMapper.getInstancia().dtoLotetoNegocio(lote);
		Lote lot = new Lote().dtoLotetoNegocio(lote);
		Almacen.getInstancia().actualizarInventario(cantidad, causa, legajoOperador, legajoAutorizante, lot, destino);
	}

	// Cada 30 días el Almacén controla automáticamente los vencimientos:
	public void controlarVencimientos() {
		Almacen.getInstancia().controlarVencimientos();
	}

	private void procesarPedidosWeb(OrdenCompra oc)
			throws ArticuloException, ArticuloProveedorException, ProveedorException {

		PedidoWeb pedidoOriginal = oc.getPedidoW();
		// Prioriza el pedido que originó la orden de compra.
		Despacho.getInstancia().procesarPedidoWeb(pedidoOriginal);

		// Si logró completar el pedido priorizado, verifico que otros pedidos puedo
		// despachar:
		if (pedidoOriginal.getEstadoPedido() != EstadoPedido.Pendiente_Despacho) {

			List<PedidoWeb> pedidosPendientes = oc.getArticulo().traerPedidosPendientes();

			for (PedidoWeb pw : pedidosPendientes) {
				Despacho.getInstancia().procesarPedidoWeb(pw);
			}
		}
	}

	// Lista de Articulos para que se pueda hacer el pedido
	public List<ArticuloDTO> obtenerArticulosParaPublicar() {

		List<ArticuloDTO> resultado = new ArrayList<ArticuloDTO>();
		List<Articulo> artsNegocio = new ArrayList<Articulo>();
		artsNegocio = Despacho.getInstancia().obtenerArticulosParaPublicar();
		for (Articulo a : artsNegocio) {
			resultado.add(a.articuloToDTO(a));
		}

		return resultado;
	}

	// Lista de Clientes para Asociar los Pagos / Factura

	public List<ClienteDTO> obtenerClientesParaPublicar() {

		List<ClienteDTO> resultado = new ArrayList<ClienteDTO>();
		List<Cliente> clientesNegocio = new ArrayList<Cliente>();
		clientesNegocio = Facturacion.getInstancia().obtenerClientesParaPublicar();
		for (Cliente c : clientesNegocio) {
			resultado.add(c.clienteToDTO(c));
		}

		return resultado;
	}
	// TODO: detalles de Pedido WEB

	public PedidoWebDTO obtenerPedidoWebParaPublicar(int idPedido) throws PedidoWebException {

		PedidoWeb p = PedidoWebDAO.getInstancia().findByCodigo(idPedido);
		PedidoWebDTO aux = PedidoWeb.pedidoWebToDTO(p);
		return aux;
	}

	// TODO: detalles de Orden de Compra
	public OrdenCompraDTO obtenerOrdenCompraParaPublicar(int idOC) throws OrdenCompraException {
		OrdenCompra oc = OrdenCompraDAO.getInstancia().findByCodigo(idOC);
		return OrdenCompra.ordenCompraToDTO(oc);
	}

	// TODO: detalles de Articulo por su Stock

	public ArticuloStockDTO obtenerDetalleStockdeArticulo(int codigoBarra) throws ArticuloException {

		Articulo a = ArticuloDAO.getInstancia().findByCodigo(codigoBarra);
		return Articulo.articuloStockToDTO(a);
	}

	public ArticuloDTO obtenerArticuloPorCodigo(int codigoBarra) throws ArticuloException {

		Articulo a = ArticuloDAO.getInstancia().findByCodigo(codigoBarra);
		return Articulo.articuloToDTO(a);
	}

}
