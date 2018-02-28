package edu.uade.apdzpoc.remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import edu.uade.apdzpoc.controlador.Controlador;
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
import edu.uade.apdzpoc.interfaces.InterfazRemota;
import edu.uade.apdzpoc.negocio.Articulo;
import edu.uade.apdzpoc.negocio.Cliente;
import edu.uade.apdzpoc.negocio.Despacho;
import edu.uade.apdzpoc.negocio.Facturacion;
import edu.uade.apdzpoc.negocio.OrdenCompra;
import edu.uade.apdzpoc.negocio.PedidoWeb;

/**
 * 
 * TPO: APDZPOC
 * 
 * GRUPO 08 Integrantes: LU:0119404 - Zapatero, Barbara Daniela LU:1022185 -
 * Pablos, Diego Maximiliano LU:0133009 - Ojeda, Maria De Los Angeles LU:0127304
 * - Cavallaro, Cristian Alberto
 * 
 *
 */

public class ObjetoRemoto extends UnicastRemoteObject implements InterfazRemota {

	public ObjetoRemoto() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -6961672630964242179L;

	@Override
	public int crearPedidoWeb(List<ItemPedidoDTO> articulosComprados, ClienteDTO cliente, String direccion) throws RemoteException, ArticuloException, ArticuloProveedorException, ProveedorException, ClienteException {
		
			return Controlador.getInstancia().crearPedidoWeb(articulosComprados, cliente, direccion);
		
	}

	@Override
	public List<PedidoWebDTO> obtenerPedidosParaDespachar() throws RemoteException {
		// TODO Auto-generated method stub
		return Controlador.getInstancia().obtenerPedidosParaDespachar();
	}

	@Override
	public void despacharPedido(PedidoWebDTO pw, Date fechaEntrega, String empresaTransporte) throws RemoteException, PedidoWebException {
		Controlador.getInstancia().despacharPedido(pw, fechaEntrega, empresaTransporte);

	}
	
	
	@Override
	public List<PedidoWebDTO> obtenerPedidosParaProcesar() throws RemoteException {
		// TODO Auto-generated method stub
		return Controlador.getInstancia().obtenerPedidosParaDespachar();
	}

	@Override
	public void procesarPedido(PedidoWebDTO pw) throws RemoteException, ArticuloException, ArticuloProveedorException, ProveedorException, PedidoWebException {
		Controlador.getInstancia().procesarPedidoWeb(pw);

	}


		

	@Override
	public List<OrdenCompraDTO> obtenerOrdenesdeCompraParaValidar() throws RemoteException {
		// TODO Auto-generated method stub
		return Controlador.getInstancia().obtenerOrdenesdeCompraParaValidar();
	}

	@Override
	public void validarIngresoOrdenCompra(OrdenCompraDTO oc, EstadoOC estadoOC, LoteDTO lote) throws RemoteException, LoteException, UbicacionException, ArticuloException, ArticuloProveedorException, ProveedorException, OrdenCompraException {
		Controlador.getInstancia().validarIngresoOrdenCompra(oc, estadoOC, lote);

	}

	@Override
	public void ajustarInventario(int cantidad, CausaAjuste causa, int legajoOperador, int legajoAutorizante,
			LoteDTO lote, DestinoArticulos destino) throws RemoteException, ArticuloException, LoteException {
		Controlador.getInstancia().ajustarInventario(cantidad, causa, legajoOperador, legajoAutorizante, lote,
				destino);

	}

	@Override
	public void ingresarPagoCliente(PagoClienteDTO pago) throws RemoteException, FacturaException {
		Controlador.getInstancia().ingresarPagoCliente(pago);
		
	}
	
	
	//Lista de Articulos para que se pueda hacer el pedido
		public List<ArticuloDTO> obtenerArticulosParaPublicar() throws RemoteException {
			return Controlador.getInstancia().obtenerArticulosParaPublicar();
					}

		

		//Lista de Clientes para Asociar los Pagos / Factura
		
		
			public List<ClienteDTO> obtenerClientesParaPublicar() throws RemoteException {
				return Controlador.getInstancia().obtenerClientesParaPublicar();
						}
		//TODO: detalles de Pedido WEB
			
			public PedidoWebDTO obtenerPedidoWebParaPublicar(int idPedido) throws RemoteException, PedidoWebException {
				return Controlador.getInstancia().obtenerPedidoWebParaPublicar(idPedido);
			}
		
		//TODO: detalles de Orden de Compra

			public OrdenCompraDTO obtenerOrdenCompraParaPublicar(int idOC) throws RemoteException, OrdenCompraException {
				return Controlador.getInstancia().obtenerOrdenCompraParaPublicar(idOC);
			}	
				
												
		//TODO: detalles de Articulo por su Stock
		
			public ArticuloStockDTO obtenerDetalleStockdeArticulo(int codigoBarra) throws RemoteException, ArticuloException {
				return Controlador.getInstancia().obtenerDetalleStockdeArticulo(codigoBarra);
			}	
			
	

}
