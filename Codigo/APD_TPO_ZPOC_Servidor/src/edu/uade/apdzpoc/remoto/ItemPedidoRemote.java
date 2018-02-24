package edu.uade.apdzpoc.remoto;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.uade.apdzpoc.dto.ArticuloDTO;
import edu.uade.apdzpoc.interfaces.IItemPedidoRemote;

public class ItemPedidoRemote extends UnicastRemoteObject implements IItemPedidoRemote, Serializable {
	
	private static final long serialVersionUID = 3453333676745547821L;
	
	protected ItemPedidoRemote() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public IItemPedidoRemote crearItemPedidoDTO(ArticuloDTO articuloDTO, int cantidad) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
