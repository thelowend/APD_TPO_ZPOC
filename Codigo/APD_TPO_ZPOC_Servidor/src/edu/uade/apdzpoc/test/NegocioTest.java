package edu.uade.apdzpoc.test;

import edu.uade.apdzpoc.excepciones.ArticuloException;

public class NegocioTest {

    public static void main(String[] args) throws Exception {
        generarPedidoWeb();
        listarPedidosADespachar();
        procesarOrdenCompra();
    }

    //TODO Generar un pedido web de mercaderia
    private static void generarPedidoWeb() {
    	Prueba_hbt ph= new Prueba_hbt();
    	try {
			ph.recuperarArticulo(10000);
			ph.recuperarArticulo(10200);
			ph.recuperarArticulo(10800);
			
		} catch (ArticuloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    //TODO Listar los pedidos a despachar
    private static void listarPedidosADespachar() {
    }

    //TODO Procesar una orden de compra cuando llega
    private static void procesarOrdenCompra() {
    }
}
