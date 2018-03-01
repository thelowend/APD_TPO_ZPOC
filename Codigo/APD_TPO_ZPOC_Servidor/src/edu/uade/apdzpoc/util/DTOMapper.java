package edu.uade.apdzpoc.util;

import java.util.ArrayList;
import java.util.List;

import edu.uade.apdzpoc.dao.ArticuloDAO;
import edu.uade.apdzpoc.dao.ClienteDAO;
import edu.uade.apdzpoc.dao.CuentaCorrienteDAO;
import edu.uade.apdzpoc.dao.FacturaDAO;
import edu.uade.apdzpoc.dao.LoteDAO;
import edu.uade.apdzpoc.dao.OrdenCompraDAO;
import edu.uade.apdzpoc.dao.PedidoWebDAO;
import edu.uade.apdzpoc.dao.RemitoAlmacenDAO;
import edu.uade.apdzpoc.dto.ArticuloDTO;
import edu.uade.apdzpoc.dto.ArticuloStockDTO;
import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.dto.CuentaCorrienteDTO;
import edu.uade.apdzpoc.dto.ItemPedidoDTO;
import edu.uade.apdzpoc.dto.ItemRemitoAlmacenDTO;
import edu.uade.apdzpoc.dto.LoteDTO;
import edu.uade.apdzpoc.dto.OrdenCompraDTO;
import edu.uade.apdzpoc.dto.PagoClienteDTO;
import edu.uade.apdzpoc.dto.PedidoWebDTO;
import edu.uade.apdzpoc.dto.ProveedorDTO;
import edu.uade.apdzpoc.dto.RemitoAlmacenDTO;
import edu.uade.apdzpoc.dto.UbicacionDTO;
import edu.uade.apdzpoc.negocio.Articulo;
import edu.uade.apdzpoc.negocio.Cliente;
import edu.uade.apdzpoc.negocio.CuentaCorriente;
import edu.uade.apdzpoc.negocio.Factura;
import edu.uade.apdzpoc.negocio.ItemPedido;
import edu.uade.apdzpoc.negocio.ItemRemitoAlmacen;
import edu.uade.apdzpoc.negocio.Lote;
import edu.uade.apdzpoc.negocio.OrdenCompra;
import edu.uade.apdzpoc.negocio.PagoCliente;
import edu.uade.apdzpoc.negocio.PedidoWeb;
import edu.uade.apdzpoc.negocio.Proveedor;
import edu.uade.apdzpoc.negocio.RemitoAlmacen;
import edu.uade.apdzpoc.negocio.Ubicacion;
import edu.uade.apdzpoc.enums.EstadoItemPedido;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ClienteException;
import edu.uade.apdzpoc.excepciones.FacturaException;
import edu.uade.apdzpoc.excepciones.LoteException;
import edu.uade.apdzpoc.excepciones.OrdenCompraException;
import edu.uade.apdzpoc.excepciones.PedidoWebException;
import edu.uade.apdzpoc.excepciones.RemitoAlmacenException;

public class DTOMapper {

	private static DTOMapper instancia;

	public static DTOMapper getInstancia() {
		if (instancia == null)
			instancia = new DTOMapper();
		return instancia;
	}

	private DTOMapper() {
	}

	// Cuando se busca en la base de datos es porque en esta parte del negocio
	// desarrollado no estamos
	// ingresando nuevos articulos. si ese fuese el caso deberiamos crear la clase
	// de negocio
	// e ir seteandole los atributos necesarios del DTO.

	public Articulo dtoArticuloToNegocio(ArticuloDTO artDTO) throws ArticuloException {

		Articulo art = Articulo.getArticulo(artDTO.getCodigoBarra());
		return art;
	}

	public ArticuloDTO articuloToDTO(Articulo articulo) {

		ArticuloDTO artDTO = new ArticuloDTO(articulo.getCodigoBarra(), articulo.getNombreArticulo(),
				articulo.getDescripcion(), articulo.getPrecioVenta(), articulo.getPresentacion(),
				articulo.getTamanio());

		return artDTO;
	}

	public ArticuloStockDTO articuloStockToDTO(Articulo articulo) {

		ArticuloStockDTO artStockDTO = new ArticuloStockDTO(articulo.getCodigoBarra(), articulo.getNombreArticulo(),
				articulo.getDescripcion(), articulo.getPrecioVenta(), articulo.getPresentacion(), articulo.getTamanio(),
				articulo.getStockFisico(), articulo.getStockVirtual(), articulo.getStockDisponible(),
				articulo.getStockPendienteEntrega());

		return artStockDTO;
	}

	public Proveedor dtoProveedorToNegocio(ProveedorDTO provDTO) {
		Proveedor proveedorNegocio = new edu.uade.apdzpoc.negocio.Proveedor();
		proveedorNegocio.setIdProveedor(provDTO.getIdProveedor());
		proveedorNegocio.setNombreProveedor(provDTO.getNombreProveedor());
		return proveedorNegocio;

	}

	public ProveedorDTO proveedorToDTO(Proveedor proveedor) {
		ProveedorDTO provDTO = new ProveedorDTO(proveedor.getIdProveedor(), proveedor.getNombreProveedor());
		return provDTO;
	}

	public Cliente dtoClienteToNegocio(ClienteDTO clienteDTO) throws ClienteException {
		Cliente cliente = ClienteDAO.getInstancia().findByCodigo(clienteDTO.getIdCliente());
		return cliente;
	}

	public ClienteDTO clienteToDTO(Cliente cliente) {
		ClienteDTO clDTO = new ClienteDTO(cliente.getIdCliente(), cliente.getDocumento(), cliente.getNombre());
		return clDTO;
	}

	public CuentaCorriente dtoCtaCteToNegocio(CuentaCorrienteDTO ctaCte) throws ClienteException {
		CuentaCorriente cc = CuentaCorrienteDAO.getInstancia().findByCliente(ctaCte.getIdCtaCorriente());
		return cc;
	}

	public CuentaCorrienteDTO ccDTO(CuentaCorriente cuenta) {
		CuentaCorrienteDTO ccDTO = new CuentaCorrienteDTO(cuenta.getIdCtaCorriente(), cuenta.getSaldo(),
				cuenta.getLimMax());
		return ccDTO;

	}

	public Lote dtoLotetoNegocio(LoteDTO l) throws LoteException, ArticuloException {
		//// VER ESTO
		Lote loteNegocio = new Lote();
		loteNegocio.setNroLote(l.getNroLote());
		loteNegocio.setVencimiento(l.getVencimiento());
		Articulo aux = this.dtoArticuloToNegocio(l.getArticulo());
		loteNegocio.setArticulo(aux);
		return loteNegocio;
	}

	public LoteDTO loteToDTO(Lote lote) {
		LoteDTO lDTO = new LoteDTO(lote.getNroLote(), lote.getVencimiento(), this.articuloToDTO(lote.getArticulo()));
		return lDTO;

	}

	public OrdenCompra dtoOrdenCompraToNegocio(OrdenCompraDTO oc) throws OrdenCompraException {
		OrdenCompra ordenCompraNegocio = OrdenCompraDAO.getInstancia().findByCodigo(oc.getIdOC());
		return ordenCompraNegocio;

	}

	public OrdenCompraDTO ordenCompraToDTO(OrdenCompra oc) {
		// oc.getLote puede ser null!
		Lote lote = oc.getLote();
		LoteDTO loteDTO = null; // Para el caso de las ordenes de compra que aún no llegaron, por lo que no sabemos a que lote corresponden
		if(lote != null) {
			loteDTO = this.loteToDTO(oc.getLote());
		} 
		OrdenCompraDTO ocDTO = new OrdenCompraDTO(oc.getIdOC(), this.proveedorToDTO(oc.getProveedor()),
				oc.getCantidad(), String.valueOf(oc.getEstado()), this.articuloToDTO(oc.getArticulo()),
				loteDTO, this.pedidoWebToDTO(oc.getPedidoW()), oc.getFecha());
		return ocDTO;
	}

	public RemitoAlmacen dtoRemitoAlmacenToNegocio(RemitoAlmacenDTO ra) throws RemitoAlmacenException {
		return RemitoAlmacenDAO.getInstancia().findByNro(ra.getIdRemito());
	}

	public RemitoAlmacenDTO remitoAlmacenToDTO(RemitoAlmacen remitoAlmacen) {

		List<ItemRemitoAlmacen> itemsRA = remitoAlmacen.getItemsRemito();
		List<ItemRemitoAlmacenDTO> items = new ArrayList<ItemRemitoAlmacenDTO>();

		for (ItemRemitoAlmacen i : itemsRA) {
			items.add(this.itemRemitoAlmacenToDTO(i));
		}

		RemitoAlmacenDTO raDTO = new RemitoAlmacenDTO(remitoAlmacen.getIdRemito(), remitoAlmacen.getEstado(), items,
				remitoAlmacen.getTipo(), remitoAlmacen.getNro());
		return raDTO;
	}

	public ItemRemitoAlmacenDTO itemRemitoAlmacenToDTO(ItemRemitoAlmacen ira) {
		ItemRemitoAlmacenDTO iraDTO = new ItemRemitoAlmacenDTO(this.articuloToDTO(ira.getArticulo()), ira.getCantidad(),
				(UbicacionDTO) this.ubicacionToDTO(ira.getUbicacion()));
		return iraDTO;

	}

	public ItemRemitoAlmacen dtoItemRemitoToNegocio(ItemRemitoAlmacenDTO itemr) throws ArticuloException {
		Articulo articulo = this.dtoArticuloToNegocio(itemr.getArticulo());
		Ubicacion ubicacion = this.dtoUbicacionToNegocio(itemr.getUbicacion());

		return new ItemRemitoAlmacen(articulo, itemr.getCantidad(), ubicacion);
	}

	private Ubicacion dtoUbicacionToNegocio(UbicacionDTO ubicacion) {
		Ubicacion u = new Ubicacion();
		u.setIdUbicacion(ubicacion.getIdUbicacion());
		u.setNombre(ubicacion.getNombre());
		u.setCalle(ubicacion.getCalle());
		u.setBloque(ubicacion.getBloque());
		u.setEstante(ubicacion.getBloque());
		u.setPosicion(ubicacion.getPosicion());
		u.setEstado(ubicacion.getEstado());
		u.setCapacidad(ubicacion.getCapacidad());
		u.setCapacidadInicial(ubicacion.getCapacidadInicial());
		return u;
	}

	private Object ubicacionToDTO(Ubicacion ubicacion) {
		UbicacionDTO u = new UbicacionDTO();
		u.setIdUbicacion(ubicacion.getIdUbicacion());
		u.setNombre(ubicacion.getNombre());
		u.setCalle(ubicacion.getCalle());
		u.setBloque(ubicacion.getBloque());
		u.setEstante(ubicacion.getBloque());
		u.setPosicion(ubicacion.getPosicion());
		u.setEstado(ubicacion.getEstado());
		u.setCapacidad(ubicacion.getCapacidad());
		u.setCapacidadInicial(ubicacion.getCapacidadInicial());
		return u;
	}

	public PedidoWebDTO pedidoWebToDTO(PedidoWeb pw) {

		List<ItemPedido> itemsPW = pw.getItems();
		List<ItemPedidoDTO> items = new ArrayList<ItemPedidoDTO>();

		for (ItemPedido i : itemsPW) {
			items.add(this.itemPedidoToDTO(i));
		}

		ClienteDTO clienteDTO = this.clienteToDTO(pw.getCliente());

		PedidoWebDTO pedidoDTO = new PedidoWebDTO(pw.getIdPedido(), clienteDTO, pw.getFechaGeneracion(),
				pw.getFechaDespacho(), pw.getFechaDeEntrega(), pw.getEstadoPedido(), pw.getDireccionPedido(), items);
		return pedidoDTO;
	}

	public PedidoWeb dtoPedidoWebToNegocio(PedidoWebDTO pwDTO) throws PedidoWebException {
		/*
		 * List<ItemPedidoDTO> itemsPWDTO = pwDTO.getItems(); List<ItemPedido> items =
		 * new ArrayList<ItemPedido>();
		 * 
		 * for(ItemPedidoDTO i: itemsPWDTO){ items.add(this.dtoItemPedidoToNegocio(i));
		 * }
		 * 
		 * Cliente cliente = this.dtoClienteToNegocio(pwDTO.getCliente());
		 * 
		 * PedidoWeb pw = new PedidoWeb(pwDTO.getIdPedido(), cliente,
		 * pwDTO.getFechaGeneracion(), pwDTO.getFechaDespacho(),
		 * pwDTO.getFechaDeEntrega(), pwDTO.getEstadoPedido(),
		 * pwDTO.getDireccionPedido(), items);
		 */
		return PedidoWebDAO.getInstancia().findByCodigo(pwDTO.getIdPedido());

	}

	public ItemPedido dtoItemPedidoToNegocio(ItemPedidoDTO ipDTO) throws ArticuloException {
		Articulo articulo = this.dtoArticuloToNegocio(ipDTO.getArticuloDTO());
		return new ItemPedido(articulo, ipDTO.getCantidad(), EstadoItemPedido.Con_Stock); // Acá podríamos poner una
																							// versión del constructor
																							// que no requiera el estado
																							// en la inicialización
	}

	public ItemPedidoDTO itemPedidoToDTO(ItemPedido item) {
		ItemPedidoDTO ipDTO = new ItemPedidoDTO(this.articuloToDTO(item.getArticulo()), item.getCantidad());
		return ipDTO;
	}

	public PagoCliente dtoPagoClienteToNegocio(PagoClienteDTO pago) throws FacturaException {
		Factura f = FacturaDAO.getInstancia().findByCodigo(pago.getFactura().getIdFactura());

		PagoCliente pagoNegocio = new PagoCliente(pago.getIdPago(), pago.getMedioDePago(), pago.getFecha(),
				pago.getMonto(), f);
		return pagoNegocio;
	}

}
