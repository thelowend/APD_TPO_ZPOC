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
import edu.uade.apdzpoc.dto.PedidoWebDTO;
import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.EstadoOC;
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
	public int crearPedidoWeb(List<ItemPedidoDTO> articulosComprados, ClienteDTO cliente) throws RemoteException {
		// TODO Auto-generated method stub
		return Controlador.getInstancia().crearPedidoWeb(articulosComprados, cliente);
	}

	@Override
	public List<PedidoWebDTO> obtenerPedidosParaDespachar() throws RemoteException {
		// TODO Auto-generated method stub
		return Controlador.getInstancia().obtenerPedidosParaDespachar();
	}

	@Override
	public void despacharPedido(PedidoWebDTO pw, Date fechaEntrega, String empresaTransporte) throws RemoteException {
		Controlador.getInstancia().despacharPedido(pw, fechaEntrega, empresaTransporte);

	}
	
	
	@Override
	public List<PedidoWebDTO> obtenerPedidosParaProcesar() throws RemoteException {
		// TODO Auto-generated method stub
		return Controlador.getInstancia().obtenerPedidosParaDespachar();
	}

	@Override
	public void procesarPedido(PedidoWebDTO pw) throws RemoteException {
		Controlador.getInstancia().despacharPedido(pw, fechaEntrega, empresaTransporte);

	}


	
	
	
	
	
	

	@Override
	public List<OrdenCompraDTO> obtenerOrdenesdeCompraParaValidar() throws RemoteException {
		// TODO Auto-generated method stub
		return Controlador.getInstancia().obtenerOrdenesdeCompraParaValidar();
	}

	@Override
	public void procesarOrdenCompraPendiente(OrdenCompraDTO oc, EstadoOC estadoOC) throws RemoteException {
		Controlador.getInstancia().ingresarCompra(oc, estadoOC);

	}

	@Override
	public void ajustarInventario(int cantidad, CausaAjuste causa, int legajoOperador, int legajoAutorizante,
			LoteDTO lote, String destino) throws RemoteException {
		Controlador.getInstancia().controlarInventario(cantidad, causa, legajoOperador, legajoAutorizante, lote,
				destino);

	}

}
