package edu.uade.apdzpoc.negociodelegado;

import edu.uade.apdzpoc.dto.*;
import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.DestinoArticulos;
import edu.uade.apdzpoc.enums.EstadoOC;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.ClienteException;
import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.excepciones.FacturaException;
import edu.uade.apdzpoc.excepciones.LoteException;
import edu.uade.apdzpoc.excepciones.OrdenCompraException;
import edu.uade.apdzpoc.excepciones.PedidoWebException;
import edu.uade.apdzpoc.excepciones.ProveedorException;
import edu.uade.apdzpoc.excepciones.UbicacionException;
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
	
	
	public int crearPedidoWeb(List<ItemPedidoDTO> articulosComprados, ClienteDTO cliente, String direccion) throws ComunicationException, ArticuloException, ArticuloProveedorException, ProveedorException, ClienteException {
		try {
			return referenciaRemota.crearPedidoWeb(articulosComprados, cliente, direccion);
		} catch (RemoteException e) {
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
	}
	
public List<PedidoWebDTO>  obtenerPedidosParaProcesar() throws ComunicationException{
		try{
			return referenciaRemota.obtenerPedidosParaProcesar();
		} catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
	}
	
	
	
	public void procesarPedido(PedidoWebDTO pw) throws ComunicationException, ArticuloException, ArticuloProveedorException, ProveedorException, PedidoWebException {
		try{
			referenciaRemota.procesarPedido(pw);
		} catch (RemoteException e){
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
	
	
	
	public void despacharPedido(PedidoWebDTO pw, Date fechaEntrega, String empresaTransporte) throws ComunicationException, PedidoWebException {
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
	
	
	public void validarIngresoOrdenCompra(OrdenCompraDTO oc, EstadoOC estadoOC, LoteDTO lote) throws ComunicationException, LoteException, UbicacionException, ArticuloException, ArticuloProveedorException, ProveedorException, OrdenCompraException{
		try{
			referenciaRemota.validarIngresoOrdenCompra(oc, estadoOC, lote);
		}catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
	}
	
	
	public void ajustarInventario(int cantidad, CausaAjuste causa, int legajoOperador, int legajoAutorizante, LoteDTO lote, DestinoArticulos destino) throws ComunicationException, ArticuloException, LoteException{
		try {
			referenciaRemota.ajustarInventario(cantidad, causa, legajoOperador, legajoAutorizante, lote, destino);
		}catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
	}
	
	public void ingresarPagoCliente(PagoClienteDTO pago) throws ComunicationException, FacturaException {
		try {
			referenciaRemota.ingresarPagoCliente(pago);
		}catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
	}
	
	// Cada 30 días el Almacén controla automáticamente los vencimientos:
/*	public void controlarVencimientos() throws ComunicationException {
		referenciaRemota.controlarVencimientos();
	}
	*/
	
	

	//Lista de Articulos para que se pueda hacer el pedido
	public List<ArticuloDTO> obtenerArticulosParaPublicar() throws RemoteException, ComunicationException{
		try {
			return referenciaRemota.obtenerArticulosParaPublicar();
		}catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
	}
	
	

	//Lista de Clientes para Asociar los Pagos / Factura
	
	
		public List<ClienteDTO> obtenerClientesParaPublicar() throws RemoteException, ComunicationException{
			try {
			return referenciaRemota.obtenerClientesParaPublicar();
		}catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
		}		
					
	//TODO: detalles de Pedido WEB
		
		public PedidoWebDTO obtenerPedidoWebParaPublicar(int idPedido) throws RemoteException, ComunicationException, PedidoWebException{
			try {
			return referenciaRemota.obtenerPedidoWebParaPublicar(idPedido);
		}catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}
		}
	//TODO: detalles de Orden de Compra

		public OrdenCompraDTO obtenerOrdenCompraParaPublicar(int idOC) throws RemoteException, ComunicationException, OrdenCompraException{
			try{
			return referenciaRemota.obtenerOrdenCompraParaPublicar(idOC);
			}catch (RemoteException e){
				throw new ComunicationException("Se produjo un error en la comunicación.");
			}
		}
											
	//TODO: detalles de Articulo por su Stock
	
		public ArticuloStockDTO obtenerDetalleStockdeArticulo(int codigoBarra) throws RemoteException, ComunicationException, ArticuloException{
		try{
			return referenciaRemota.obtenerDetalleStockdeArticulo(codigoBarra);
		
		}catch (RemoteException e){
			throw new ComunicationException("Se produjo un error en la comunicación.");
		}		
		}
	
	
	
	
	
	
	
}
	
