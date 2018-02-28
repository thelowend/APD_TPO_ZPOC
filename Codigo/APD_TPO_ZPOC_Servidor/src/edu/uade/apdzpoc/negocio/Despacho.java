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

import edu.uade.apdzpoc.enums.EstadoPedido;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.PedidoWebException;
import edu.uade.apdzpoc.excepciones.ProveedorException;

public class Despacho {
	private static Despacho instancia;

	private Despacho() {
	}

	public static Despacho getInstancia() {
		if (instancia == null)
			instancia = new Despacho();
		return instancia;
	}

	public void procesarPedidoWeb(PedidoWeb pw) throws ArticuloException, ArticuloProveedorException, ProveedorException {

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
				almacen.generarRemitos(pw); // Genera la lista de ubicaciones de los art�culos a retirar, CUANDO SE DESPACHE el pedido.
			}

			almacen.crearMovimientos(pw); // Paso por el almacen para generar los movimientos:
		}

		pw.save(); // Persistimos el pedido actualizado
	}

	public void despacharPedido(PedidoWeb pw, Date fechaEntrega, String empresaTransporte) {
		// Facturaci�n crea el Remito de Transporte
		Facturacion.getInstancia().crearRemitoTransporte(pw, empresaTransporte);
		pw.setFechaDeEntrega(fechaEntrega);
		pw.setEstadoPedido(EstadoPedido.Despachado);
		pw.save();
	}

	public List<PedidoWeb> obtenerPedidosParaDespachar() {
		return PedidoWeb.obtenerPedidosParaDespachar();
	}
	
	public PedidoWeb obtenerPedidoWeb(int id) throws PedidoWebException {
		return PedidoWeb.obtenerPedidoPorId(id);
	}
}
