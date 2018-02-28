package edu.uade.apdzpoc.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.dto.ItemPedidoDTO;
import edu.uade.apdzpoc.dto.LoteDTO;
import edu.uade.apdzpoc.dto.OrdenCompraDTO;
import edu.uade.apdzpoc.dto.PedidoWebDTO;
import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.EstadoOC;

public interface InterfazRemota extends Remote, Serializable {

	public abstract int crearPedidoWeb(List<ItemPedidoDTO> articulosComprados, ClienteDTO cliente) throws RemoteException;


	public abstract List<PedidoWebDTO> obtenerPedidosParaDespachar() throws RemoteException;


	public abstract void despacharPedido(PedidoWebDTO pw, Date fechaEntrega, String empresaTransporte)throws RemoteException;


	public abstract List<OrdenCompraDTO> obtenerOrdenesdeCompraParaValidar() throws RemoteException;


	public abstract void procesarOrdenCompraPendiente(OrdenCompraDTO oc, EstadoOC estadoOC, LoteDTO lote) throws RemoteException;


	public abstract void ajustarInventario(int cantidad, CausaAjuste causa, int legajoOperador, int legajoAutorizante, LoteDTO lote,
			String destino) throws RemoteException;


	public abstract void controlarVencimientos();


	public abstract List<PedidoWebDTO> obtenerPedidosParaProcesar() throws RemoteException;


	public abstract void procesarPedido(PedidoWebDTO pw) throws RemoteException;


}
