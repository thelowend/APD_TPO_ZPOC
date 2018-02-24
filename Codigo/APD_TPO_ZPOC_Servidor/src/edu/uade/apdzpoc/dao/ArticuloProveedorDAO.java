package edu.uade.apdzpoc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;


public class ArticuloProveedorDAO {

	private static ArticuloProveedorDAO instancia;
	
	private ArticuloProveedorDAO() {}
	
	public static ArticuloProveedorDAO getInstancia(){
		if(instancia == null)
			instancia = new ArticuloProveedorDAO();
		return instancia;
	}
	
	
	public Proveedor findBestProveedorByArticulo(Integer codigo_barra){
		//Proveedor resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		ProveedorEntity aux = (ProveedorEntity) s.createQuery("select p from ArticuloProveedorEntity ape join ape.id.articulo a join ape.id.proveedor p where a.codigoBarra = ? Order by ape.precio ASC ").setInteger(0, codigo_barra).setFirstResult(0).setMaxResults(1).uniqueResult();
		//resultado = ProveedorDAO.getInstancia().toNegocio(aux);
		s.getTransaction().commit();
		s.close();
		return ProveedorDAO.getInstancia().toNegocio(aux);
	}
	
	
	
	
	
	
}
