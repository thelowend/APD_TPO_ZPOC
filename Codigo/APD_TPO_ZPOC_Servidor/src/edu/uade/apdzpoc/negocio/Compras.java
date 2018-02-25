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

import java.util.ArrayList;
import java.util.List;

import edu.uade.apdzpoc.dao.ArticuloDAO;
import edu.uade.apdzpoc.enums.EstadoOC;

public class Compras {
	private static Compras instancia;

	private Compras() {
	}

	public static Compras getInstancia() {
		if (instancia == null)
			instancia = new Compras();
		return instancia;
	}

	public List<OrdenCompra> crearOrdenesCompra(ItemPedido ip, PedidoWeb pw) {
		
		List<OrdenCompra> result = new ArrayList<>();
		
		// Recupero el Articulo para saber que cantidad tenemos que pedir
		Articulo a = ArticuloDAO.getInstancia().findrecuperadoByCodigo(ip.getArticulo().getCodigoBarra());

		// Selecciono el mejor proveedor, el que tenga el precio mas bajo. El dao me va
		// a devolver el que tenga el menor precio.
		Proveedor p = this.seleccionarProveedor(a);
		
		// Genero órdenes de compra hasta cubrir los items pedidos
		for(int cantidadItem = ip.getCantidad(); cantidadItem > 0; cantidadItem -= a.getCantidadCompra()) {
			OrdenCompra oc = new OrdenCompra(p, a, pw);
			oc.setEstado(EstadoOC.Pendiente);
			result.add(oc);
		}
		
		return result;
	}

	private Proveedor seleccionarProveedor(Articulo a) {
		// Busco el mejor proveedor
		Proveedor p = null;
		return p;
	}
	
	public void validarOrdenCompra(OrdenCompra oc, EstadoOC estado) {
		oc.setEstado(estado);
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
