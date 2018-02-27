/**
 * 
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

package edu.uade.apdzpoc.negocio;

import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.dao.PedidoWebDAO;
import edu.uade.apdzpoc.enums.EstadoPedido;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.ProveedorException;

public class Despacho {
	private static Despacho instancia;

	private Despacho() {}

	public static Despacho getInstancia() {
		if (instancia == null)
			instancia = new Despacho();
		return instancia;
	}
	
	public void despacharPedido(PedidoWeb pw, Date fechaEntrega, String empresaTransporte) {
		// Facturación crea el Remito de Transporte
		Facturacion.getInstancia().crearRemitoTransporte(pw, empresaTransporte);
		
		pw.setFechaDeEntrega(fechaEntrega);
		pw.setEstadoPedido(EstadoPedido.Despachado);
		pw.save();
	}
	
	public void procesarPedidoWeb(PedidoWeb pw) throws ArticuloException, ArticuloProveedorException, ProveedorException {

		pw.procesar();
		
		// pw.save();
	}
	
	public List<PedidoWeb> obtenerPedidosADespachar() {
		return PedidoWebDAO.getInstancia().findByEstado(EstadoPedido.Pendiente_Despacho);
	}
}
