package edu.uade.apdzpoc.negociodelegado;

import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.dto.ItemPedidoDTO;
import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.interfaces.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
/**
 * 
 * TPO: APDZPOC
 * 
 * GRUPO 08
 * Integrantes:
 * 	LU:0119404	- Zapatero, Barbara Daniela
 * 	LU:1022185	- Pablos, Diego Maximiliano
 * 	LU:0133009	- Ojeda, Maria De Los Angeles
 *  LU:0127304	- Cavallaro, Cristian Alberto
 *  
 *
 */



public class BusinessDelegate {
	
	private IControladorRemote controladorRemote;
	
//	private IClienteRemote clienteRemote;
//	private IArticuloRemote articuloRemote;
//	private IItemPedidoRemote itemPedidoRemote;
	
	private static BusinessDelegate instancia;
	
	public static BusinessDelegate getInstancia() throws ComunicationException {
		if (instancia == null) {
			instancia = new BusinessDelegate();
		}
		return instancia;
	}
	
	private BusinessDelegate() {
		try {
			
			controladorRemote = (IControladorRemote) Naming.lookup("//localhost/ControladorRemote");
			
//			clienteRemote = (IClienteRemote) Naming.lookup("//localhost/ClienteRemote");
//			articuloRemote = (IArticuloRemote) Naming.lookup("//localhost/ArticuloRemote");
//			itemPedidoRemote = (IItemPedidoRemote) Naming.lookup("//localhost/ItemPedidoRemote");
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public int crearPedidoWeb(List<ItemPedidoDTO> articulosComprados, ClienteDTO cliente) throws ComunicationException {
//		try {
//			return controladorRemote.crearPedidoWeb(List<ItemPedidoDTO> articulosComprados, ClienteDTO cliente);
//		} catch (RemoteException e) {
//			throw new ComunicacionException("Se produjo un error en la comunicación.");
//		}
//	}
}
