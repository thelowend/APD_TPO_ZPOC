package edu.uade.apdzpoc.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.controlador.Controlador;
import edu.uade.apdzpoc.dao.ArticuloDAO;
import edu.uade.apdzpoc.dao.LoteDAO;
import edu.uade.apdzpoc.dao.ProveedorDAO;
import edu.uade.apdzpoc.excepciones.LoteException;
import edu.uade.apdzpoc.excepciones.ProveedorException;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.Articulo;
import edu.uade.apdzpoc.negocio.Lote;
import edu.uade.apdzpoc.negocio.Proveedor;

public class Prueba_hbt {

	public static void main(String[] args) throws LoteException, ProveedorException {
		

		// Aca es para poner las llamadas al Controlador
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		
		
		Lote l = LoteDAO.getInstancia().findrecuperadoByNro(1254);
		
		System.out.println(l.getNroLote());
	
		
		
		Proveedor p = ProveedorDAO.getInstancia().findByNro(3);
		
		System.out.println(p.getNombreProveedor());
		
		/*Articulo aux = ArticuloDAO.getInstancia().findrecuperadoByCodigo(10800);
		
		System.out.println(aux.getNombreArticulo());
		System.out.println(aux.getPrecioVenta());
		
		aux.setPrecioVenta(345);
		ArticuloDAO.getInstancia().save(aux);
		
		Articulo aux2 = ArticuloDAO.getInstancia().findrecuperadoByCodigo(10800);
		
		System.out.println(aux2.getNombreArticulo());
		System.out.println(aux2.getPrecioVenta());
	*/
	
	
	
	}
	
	
	
	
	
	
	
	
	
	


}
