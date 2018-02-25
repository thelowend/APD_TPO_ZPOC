package edu.uade.apdzpoc.remoto;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.uade.apdzpoc.dto.ArticuloDTO;
import edu.uade.apdzpoc.interfaces.IArticuloRemote;

public class ArticuloRemote extends UnicastRemoteObject implements IArticuloRemote, Serializable {
	
	private static final long serialVersionUID = 6008149192318691185L;
	
	protected ArticuloRemote() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArticuloDTO getArticulo(int idArticulo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
