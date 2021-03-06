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
import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.enums.EstadoOC;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.ProveedorException;

public class Compras {
	private static Compras instancia;

	private Compras() {}

	public static Compras getInstancia() {
		if (instancia == null)
			instancia = new Compras();
		return instancia;
	}
	
	public void validarOrdenCompra(OrdenCompra oc, EstadoOC estado, Lote lote) {
		oc.setEstado(estado);
		oc.setLote(lote);
		oc.save();
	}
	
	public List<OrdenCompra> crearOrdenesCompraPorItem(ItemPedido ip, PedidoWeb pw) throws ArticuloException, ArticuloProveedorException, ProveedorException {
		
		List<OrdenCompra> result = new ArrayList<>();
		
		// Recupero el Articulo para saber que cantidad tenemos que pedir:
		Articulo articulo = ip.getArticulo();
		Proveedor proveedor = articulo.obtenerMejorProveedor();
		
		// Genero ordenes de compra hasta cubrir los items pedidos
		for(int cantidadItem = ip.getCantidad(); cantidadItem > 0; cantidadItem -= articulo.getCantidadCompra()) {
			OrdenCompra oc = new OrdenCompra(proveedor, articulo, pw, new Date());
			result.add(oc);
			oc.save();
		}
		
		return result;
	}

	public List<OrdenCompra> obtenerOCParaValidar() {
		return OrdenCompra.obtenerOCParaValidar();
	}

	/*public void procesarOrdenCompraPendiente(OrdenCompra oc, EstadoOC estadoOC, Lote lote) {
		oc.setEstado(estadoOC);
		oc.setLote(lote);
		oc.save();
	}
	*/


}
