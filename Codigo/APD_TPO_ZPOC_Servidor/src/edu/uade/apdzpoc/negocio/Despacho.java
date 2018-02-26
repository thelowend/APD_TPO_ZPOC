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
	
	public void procesarPedidoWeb(PedidoWeb pw) {
		// El despacho actualizará el estado:
		Facturacion facturacion = Facturacion.getInstancia();
		Almacen almacen = Almacen.getInstancia();
		
		if (!facturacion.alcanzaLimiteCTA(pw)) {

			pw.setEstadoPedido(EstadoPedido.Rechazado);
			
		} else {
			if (!almacen.alcanzaStockPedido(pw)) {
				pw.setEstadoPedido(EstadoPedido.Pendiente_Stock);
			} else {
				pw.setEstadoPedido(EstadoPedido.Pendiente_Despacho);
				facturacion.crearFactura(pw);
				almacen.buscarUbicacionesArticulos(pw);
			}
			
			// Paso por el almacen para generar los movimientos:
			List<Movimiento> lm = almacen.crearMovimientos(pw);
			
			for (Movimiento m : lm) {
				m.actualizarNovedadStock();
				m.getArticulo().save(); // Guardo el artículo con el stock actualizado y los movimientos nuevos
			}	
		}
		
		pw.save(); // Guardamos el pedido
	}
	
	public List<PedidoWeb> obtenerPedidosADespachar() {
		return PedidoWebDAO.getInstancia().findByEstado(EstadoPedido.Pendiente_Despacho);
	}
}
