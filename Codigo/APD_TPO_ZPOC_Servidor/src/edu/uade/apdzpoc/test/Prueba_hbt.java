package edu.uade.apdzpoc.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.controlador.Controlador;
import edu.uade.apdzpoc.dao.ArticuloDAO;
import edu.uade.apdzpoc.dao.FacturaDAO;
import edu.uade.apdzpoc.dao.LoteDAO;
import edu.uade.apdzpoc.dao.OrdenCompraDAO;
import edu.uade.apdzpoc.dao.PedidoWebDAO;
import edu.uade.apdzpoc.dao.ProveedorDAO;
import edu.uade.apdzpoc.excepciones.FacturaException;
import edu.uade.apdzpoc.excepciones.LoteException;
import edu.uade.apdzpoc.excepciones.ProveedorException;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.Articulo;
import edu.uade.apdzpoc.negocio.Factura;
import edu.uade.apdzpoc.negocio.ItemPedido;
import edu.uade.apdzpoc.negocio.Lote;
import edu.uade.apdzpoc.negocio.OrdenCompra;
import edu.uade.apdzpoc.negocio.PedidoWeb;
import edu.uade.apdzpoc.negocio.Proveedor;

public class Prueba_hbt {

	public static void main(String[] args) throws LoteException, ProveedorException, FacturaException {
		

		// Aca es para poner las llamadas al Controlador
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();

		/*Factura f= FacturaDAO.getInstancia().findByCodigo(2);
		
		System.out.println(f.getCliente().getNombre());
		
		*/
		//OrdenCompra oc= OrdenCompraDAO.getInstancia().findByCodigo(7);
		//System.out.println(oc.getIdOC());
		//Articulo a=ArticuloDAO.getInstancia().findByCodigo(10000);
		//System.out.println(a.getCodigoBarra());
		
		
		
		//PedidoWeb pw= PedidoWebDAO.getInstancia().findByCodigo(3);
		//System.out.println(pw.getIdPedido());	
		
		/*
		Lote l = LoteDAO.getInstancia().findByNro(1254);
		
		System.out.println(l.getNroLote());
	
		
		
		Proveedor p = ProveedorDAO.getInstancia().findByNro(3);
		
		System.out.println(p.getNombreProveedor());
		
		/*Articulo aux = ArticuloDAO.getInstancia().findByCodigo(10800);
		
		System.out.println(aux.getNombreArticulo());
		System.out.println(aux.getPrecioVenta());
		
		aux.setPrecioVenta(345);
		ArticuloDAO.getInstancia().save(aux);
		
		Articulo aux2 = ArticuloDAO.getInstancia().findByCodigo(10800);
		
		System.out.println(aux2.getNombreArticulo());
		System.out.println(aux2.getPrecioVenta());
	*/
	
	
	}
	
	
	
	
	
	
	
	
	
	


}
