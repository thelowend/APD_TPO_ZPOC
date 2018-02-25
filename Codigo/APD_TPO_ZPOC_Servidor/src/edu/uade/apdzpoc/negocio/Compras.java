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

import java.util.List;

import edu.uade.apdzpoc.dao.ArticuloDAO;
import edu.uade.apdzpoc.enums.EstadoItemPedido;
import edu.uade.apdzpoc.enums.EstadoOC;
import edu.uade.apdzpoc.enums.EstadoRemito;
import edu.uade.apdzpoc.enums.EstadoPedido;

public class Compras {
	private static Compras instancia;

	private Compras() {
	}

	public static Compras getInstancia() {
		if (instancia == null)
			instancia = new Compras();
		return instancia;
	}

	public void crearOrdenCompra(PedidoWeb pw) {
		// Recibo el PedidoWEB que va a generar la o las Ordenes de compra.
		// Recorro los items pedidos por cada uno que tenga estado "Sin_Stock" genero
		// una OC

		for (ItemPedido ip : pw.getItems()) {
			if (ip.getEstado() == EstadoItemPedido.Sin_Stock) {
				
				// Recupero el Articulo para saber que cantidad tenemos que pedir
				Articulo a = ArticuloDAO.getInstancia().findrecuperadoByCodigo(ip.getArticulo().getCodigoBarra());
				
				// Selecciono el mejor proveedor, el que tenga el precio mas bajo. El dao me va
				// a devolver el que tenga el menor precio.
				Proveedor p = this.seleccionarProveedor(a);
				OrdenCompra oc = new OrdenCompra(p, a.getCantidadCompra(), a, pw);
				oc.setEstado(EstadoOC.Pendiente);
			}
		}
	}

	private Proveedor seleccionarProveedor(Articulo a) {
		// Busco el mejor proveedor
		Proveedor p = null;
		return p;
	}

	public void aceptarOC(OrdenCompra oc) {
		oc.setEstado(EstadoOC.Aceptada);

		// RemitoAlmacen ra = new RemitoAlmacen(EstadoRemito.Pendiente, itemsRemito,
		// tipoDocumento, nroDocumento);

	}

	/*
	 * ArticuloProveedor ap= Articulo
	 * a=ArticuloDAO.getInstancia().findrecuperadoByCodigo(codigoBarra); OrdenCompra
	 * oc= new OrdenCompra(proveedor, a.getCantidadCompra(), a.getCodigoBarra(),
	 * lote, pedidoW)
	 */
}
