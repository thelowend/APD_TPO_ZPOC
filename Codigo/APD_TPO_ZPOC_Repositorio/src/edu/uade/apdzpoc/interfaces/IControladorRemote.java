package edu.uade.apdzpoc.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.uade.apdzpoc.dto.ItemPedidoDTO;
import edu.uade.apdzpoc.dto.ClienteDTO;

public interface IControladorRemote extends Remote, Serializable {
	public int crearPedidoWeb(List<ItemPedidoDTO> articulos, ClienteDTO cliente) throws RemoteException;
}
