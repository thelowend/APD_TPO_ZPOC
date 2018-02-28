package edu.uade.apdzpoc.remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.controlador.Controlador;
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

}
