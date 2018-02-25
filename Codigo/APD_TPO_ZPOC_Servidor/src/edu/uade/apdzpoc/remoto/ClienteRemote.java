package edu.uade.apdzpoc.remoto;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.interfaces.IClienteRemote;

public class ClienteRemote extends UnicastRemoteObject implements IClienteRemote, Serializable {
	
	private static final long serialVersionUID = -8438810957925687459L;
	
	protected ClienteRemote() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ClienteDTO getCliente(int documento) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
