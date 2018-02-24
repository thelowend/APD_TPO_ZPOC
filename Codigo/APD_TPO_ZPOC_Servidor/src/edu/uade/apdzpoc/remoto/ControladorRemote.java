package edu.uade.apdzpoc.remoto;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.dto.ItemPedidoDTO;
import edu.uade.apdzpoc.interfaces.IControladorRemote;

import edu.uade.apdzpoc.util.DTOMapper;

public class ControladorRemote extends UnicastRemoteObject implements IControladorRemote, Serializable {
	
	private static final long serialVersionUID = -176846155712729485L;
	
	protected ControladorRemote() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int crearPedidoWeb(List<ItemPedidoDTO> articulos, ClienteDTO cliente) throws RemoteException {
		// TODO Auto-generated method stub
		
		// DTOMapper
		return 0;
	}

}
