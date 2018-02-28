package edu.uade.apdzpoc.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;


import edu.uade.apdzpoc.dto.ArticuloDTO;
import edu.uade.apdzpoc.dto.ArticuloStockDTO;
import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.dto.ItemPedidoDTO;
import edu.uade.apdzpoc.dto.LoteDTO;
import edu.uade.apdzpoc.dto.OrdenCompraDTO;
import edu.uade.apdzpoc.dto.PagoClienteDTO;
import edu.uade.apdzpoc.dto.PedidoWebDTO;
import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.DestinoArticulos;
import edu.uade.apdzpoc.enums.EstadoOC;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.ClienteException;
import edu.uade.apdzpoc.excepciones.FacturaException;
import edu.uade.apdzpoc.excepciones.LoteException;
import edu.uade.apdzpoc.excepciones.OrdenCompraException;
import edu.uade.apdzpoc.excepciones.PedidoWebException;
import edu.uade.apdzpoc.excepciones.ProveedorException;
import edu.uade.apdzpoc.excepciones.UbicacionException;

public interface InterfazRemota extends Remote, Serializable {

	public abstract int crearPedidoWeb(List<ItemPedidoDTO> articulosComprados, ClienteDTO cliente, String direccion) throws RemoteException, ArticuloException, ArticuloProveedorException, ProveedorException, ClienteException;

	public abstract List<PedidoWebDTO> obtenerPedidosParaProcesar() throws RemoteException;


	public abstract void procesarPedido(PedidoWebDTO pw) throws RemoteException, ArticuloException, ArticuloProveedorException, ProveedorException, PedidoWebException;
	

	public abstract List<PedidoWebDTO> obtenerPedidosParaDespachar() throws RemoteException;


	public abstract void despacharPedido(PedidoWebDTO pw, Date fechaEntrega, String empresaTransporte)throws RemoteException, PedidoWebException;


	public abstract List<OrdenCompraDTO> obtenerOrdenesdeCompraParaValidar() throws RemoteException;


	public abstract void validarIngresoOrdenCompra(OrdenCompraDTO oc, EstadoOC estadoOC, LoteDTO lote) throws RemoteException, LoteException, UbicacionException, ArticuloException, ArticuloProveedorException, ProveedorException, OrdenCompraException;


	public abstract void ajustarInventario(int cantidad, CausaAjuste causa, int legajoOperador, int legajoAutorizante, LoteDTO lote,
			DestinoArticulos destino) throws RemoteException, ArticuloException, LoteException;


	//public abstract void controlarVencimientos();

	public abstract void ingresarPagoCliente(PagoClienteDTO pago)throws RemoteException, FacturaException;


	//Lista de Articulos para que se pueda hacer el pedido
	public List<ArticuloDTO> obtenerArticulosParaPublicar() throws RemoteException;

	

	//Lista de Clientes para Asociar los Pagos / Factura
	
	
		public List<ClienteDTO> obtenerClientesParaPublicar() throws RemoteException;
					
	//TODO: detalles de Pedido WEB
		
		public PedidoWebDTO obtenerPedidoWebParaPublicar(int idPedido) throws RemoteException;
		
	
	//TODO: detalles de Orden de Compra

		public OrdenCompraDTO obtenerOrdenCompraParaPublicar(int idOC) throws RemoteException;
		
			
											
	//TODO: detalles de Articulo por su Stock
	
		public ArticuloStockDTO obtenerDetalleStockdeArticulo(int codigoBarra) throws RemoteException;
		
	
	
}
