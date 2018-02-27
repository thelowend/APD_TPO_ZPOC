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
import edu.uade.apdzpoc.dao.ArticuloProveedorDAO;
import edu.uade.apdzpoc.dao.OrdenCompraDAO;
import edu.uade.apdzpoc.enums.EstadoOC;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.ProveedorException;

public class Compras {
	private static Compras instancia;

	private Compras() {
	}

	public static Compras getInstancia() {
		if (instancia == null)
			instancia = new Compras();
		return instancia;
	}
	
	public void validarOrdenCompra(OrdenCompra oc, EstadoOC estado) {
		oc.setEstado(estado);
	}

	public List<OrdenCompra> crearOrdenesCompraPorItem(ItemPedido ip, PedidoWeb pw) throws ArticuloException, ArticuloProveedorException, ProveedorException {
		
		List<OrdenCompra> result = new ArrayList<>();
		
		// Recupero el Articulo para saber que cantidad tenemos que pedir
		Articulo articulo = ip.getArticulo();
		Proveedor proveedor = articulo.obtenerMejorProveedor();
		
		// Genero �rdenes de compra hasta cubrir los items pedidos
		for(int cantidadItem = ip.getCantidad(); cantidadItem > 0; cantidadItem -= articulo.getCantidadCompra()) {
			OrdenCompra oc = new OrdenCompra(proveedor, articulo, pw);
			result.add(oc);
			oc.save();
		}
		
		return result;
	}

	private Proveedor seleccionarProveedor(Articulo art) throws ArticuloProveedorException, ProveedorException {
		// Busco el mejor proveedor
		return ArticuloProveedorDAO.getInstancia().findBestProveedorByArticulo(art.getCodigoBarra());
	}

	public List<OrdenCompra> obtenerOCParaValidar() {
		return OrdenCompra.obtenerOCParaValidar();
		
	}
	


}
