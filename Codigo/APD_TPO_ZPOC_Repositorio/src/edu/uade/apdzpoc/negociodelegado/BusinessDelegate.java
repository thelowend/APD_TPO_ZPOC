package edu.uade.apdzpoc.negociodelegado;

import edu.uade.apdzpoc.dto.*;
import edu.uade.apdzpoc.dto.ItemPedidoDTO;
import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.DestinoArticulos;
import edu.uade.apdzpoc.enums.EstadoOC;
import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.interfaces.*;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
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
	
	private InterfazRemota referenciaRemota;
		
	private static BusinessDelegate instancia;
	
	
	private BusinessDelegate() throws ComunicationException{
		try {
			referenciaRemota = (InterfazRemota) Naming.lookup("//localhost/Remoto");
		} catch (MalformedURLException e1) {
			throw new ComunicationException("La ubicacion del seridor es incorrecta");
		} catch (RemoteException e1) {
			throw new ComunicationException("Se produjo un error en la comunicación");
		} catch (NotBoundException e1) {
			throw new ComunicationException("No encontre a nadie que me responda");
		}
	}

	public static BusinessDelegate getInstancia() throws ComunicationException{
		if(instancia == null)
			instancia = new BusinessDelegate();
		return instancia;
	}
	
	
	public int crearPedidoWeb(List<ItemPedidoDTO> articulosComprados, ClienteDTO cliente) throws ComunicationException {
		try {
			return referenciaRemota.crearPedidoWeb(articulosComprados, cliente);
		} catch (RemoteException e) {
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
	}
	
	public List<PedidoWebDTO>  obtenerPedidosParaDespachar() throws ComunicationException{
		try{
			return referenciaRemota.obtenerPedidosParaDespachar();
		} catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
	}
	
	
	
	public void despacharPedido(PedidoWebDTO pw, Date fechaEntrega, String empresaTransporte) throws ComunicationException {
		try{
			referenciaRemota.despacharPedido(pw, fechaEntrega, empresaTransporte);
		} catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
	}
	
	public List<OrdenCompraDTO> obtenerOrdenesdeCompraParaValidar() throws ComunicationException{
		try{
			return referenciaRemota.obtenerOrdenesdeCompraParaValidar();
			
		}catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
		
	}
	
	
	
	public void procesarOrdenCompraPendiente(OrdenCompraDTO oc, EstadoOC estadoOC) throws ComunicationException{
		try{
			referenciaRemota.procesarOrdenCompraPendiente(oc, estadoOC);
		}catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
	}
	
	
	public void ajustarInventario(int cantidad, CausaAjuste causa, int legajoOperador, int legajoAutorizante, LoteDTO lote, String destino) throws ComunicationException{
		try {
			referenciaRemota.ajustarInventario(cantidad, causa, legajoOperador, legajoAutorizante, lote, destino);
		}catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
	}
	
	
	
	
	
	
}
	
