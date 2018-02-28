package edu.uade.apdzpoc.test;

import java.util.ArrayList;
import java.util.List;

import edu.uade.apdzpoc.controlador.Controlador;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.ClienteException;
import edu.uade.apdzpoc.excepciones.PedidoWebException;
import edu.uade.apdzpoc.excepciones.ProveedorException;
import edu.uade.apdzpoc.negocio.Articulo;
import edu.uade.apdzpoc.negocio.Cliente;
import edu.uade.apdzpoc.negocio.ItemPedido;
import edu.uade.apdzpoc.negocio.Lote;
import edu.uade.apdzpoc.negocio.PedidoWeb;

public class NegocioTest {

	public static void main(String[] args) throws Exception {
		int pedidoGenerado = generarPedidoWeb();
		procesarPedidoWeb(pedidoGenerado);
		listarPedidosADespachar();
		procesarOrdenCompra();
	}

	// TODO Generar un pedido web de mercaderia
	private static int generarPedidoWeb() throws ArticuloProveedorException, ProveedorException {
		int idPedido = -1;
		HibernateTest ht = new HibernateTest();
		List<ItemPedido> listaIp = new ArrayList<>();
		try {

			Cliente cl = ht.recuperarCliente(1);

			Articulo a1 = ht.recuperarArticulo(1);
			ItemPedido ip1 = new ItemPedido(a1, 10, null);
			listaIp.add(ip1);

			Articulo a2 = ht.recuperarArticulo(2);
			ItemPedido ip2 = new ItemPedido(a2, 5, null);
			listaIp.add(ip2);

			Articulo a3 = ht.recuperarArticulo(3);
			ItemPedido ip3 = new ItemPedido(a2, 5, null);
			listaIp.add(ip3);
			
			idPedido = Controlador.getInstancia().crearPedidoWeb(listaIp, cl, "Boedo 156");
			System.out.println(idPedido);
			
			
		} catch (ArticuloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idPedido;
	}
	
	private static void procesarPedidoWeb(int idPedido) throws PedidoWebException, ArticuloException, ArticuloProveedorException, ProveedorException {
		PedidoWeb pw = Controlador.getInstancia().obtenerPedidoWeb(idPedido);
		Controlador.getInstancia().procesarPedidoWeb(pw);
	}
	
	// TODO Listar los pedidos a despachar
	private static void listarPedidosADespachar() {
		HibernateTest ht = new HibernateTest();
		List <PedidoWeb> lista= new ArrayList<>();
		lista=ht.mostrarPedidosADespachar();
		
		for (PedidoWeb p: lista) {
			System.out.println("Numero de Pedido:"+p.getIdPedido()+"");
			}
		
		
	}

	// TODO Procesar una orden de compra cuando llega
	private static void procesarOrdenCompra() {
	}
}
