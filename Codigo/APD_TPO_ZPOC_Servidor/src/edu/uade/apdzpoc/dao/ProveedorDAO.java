package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;

public class ProveedorDAO {

	private static ProveedorDAO instancia;
	
	private ProveedorDAO() {}
	
	public static ProveedorDAO getInstancia(){
		if(instancia == null)
			instancia = new ProveedorDAO();
		return instancia;
	}
	
	
	public Proveedor findByNro(Integer idProveedor){
		Proveedor resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		ProveedorEntity aux = (ProveedorEntity) s.createQuery("select pe from ProveedorEntity ue where idProveedor = ?").setInteger(0, idProveedor).uniqueResult();
		resultado = this.toNegocio(aux);
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	public List<Proveedor> getAll(){
		List<Proveedor> resultado = new ArrayList<Proveedor>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ProveedorEntity> aux = (List<ProveedorEntity>) s.createQuery("from ProveedorEntity").list();
		for(ProveedorEntity pe : aux)
		{
			resultado.add(this.toNegocio(pe));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	public void save(Proveedor recuperado){
		ProveedorEntity proveedorAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(proveedorAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	
	public ProveedorEntity toEntity(Proveedor proveedorNegocio) {
		ProveedorEntity proveedorAPersistir = new ProveedorEntity();
		proveedorAPersistir.setIdProveedor(proveedorNegocio.getIdProveedor());
		proveedorAPersistir.setNombreProveedor(proveedorNegocio.getNombreProveedor());
		
		return proveedorAPersistir;
	}

	public Proveedor toNegocio(ProveedorEntity proveedorRecuperado) {
		Proveedor proveedorNegocio = new Proveedor();
		proveedorNegocio.setIdProveedor(proveedorRecuperado.getIdProveedor());
		proveedorNegocio.setNombreProveedor(proveedorRecuperado.getNombreProveedor());
		
		
		return proveedorNegocio;
	}
	
	
	
}
