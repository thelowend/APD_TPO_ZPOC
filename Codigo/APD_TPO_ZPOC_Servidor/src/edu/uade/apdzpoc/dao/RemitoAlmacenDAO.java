package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.excepciones.RemitoAlmacenException;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;



public class RemitoAlmacenDAO {

	private static RemitoAlmacenDAO instancia;
	
	
	private RemitoAlmacenDAO() {}
	
	public static RemitoAlmacenDAO getInstancia(){
		if(instancia == null)
			instancia = new RemitoAlmacenDAO();
		return instancia;
	}
	
	public RemitoAlmacen findrecuperadoByNro(Integer idRemito) throws RemitoAlmacenException {
		RemitoAlmacen resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		RemitoAlmacenEntity aux = (RemitoAlmacenEntity) s.createQuery("from RemitoAlmacenEntity where idRemito = ?").setInteger(0, idRemito).uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (aux != null) {
			resultado = this.toNegocio(aux);
		} else {
			throw new RemitoAlmacenException("No se encontró el Remito de Almacen " + idRemito);
		}
		return resultado;
	}
	
	public List<RemitoAlmacen> getAll(){
		List<RemitoAlmacen> resultado = new ArrayList<RemitoAlmacen>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<RemitoAlmacenEntity> aux = (List<RemitoAlmacenEntity>) s.createQuery("from RemitoAlmacenEntity").list();
		for(RemitoAlmacenEntity rae : aux)
		{
			resultado.add(this.toNegocio(rae));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	public List<RemitoAlmacen> findByEstado(String estado){
		List<RemitoAlmacen> resultado = new ArrayList<RemitoAlmacen>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<RemitoAlmacenEntity> aux = (List<RemitoAlmacenEntity>)  s.createQuery("from RemitoAlmacenEntity where estado = ?").setString(0, estado).list();
		for(RemitoAlmacenEntity rae : aux)
		{
			resultado.add(this.toNegocio(rae));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	
	
	public void save(RemitoAlmacen recuperado){
		RemitoAlmacenEntity remitoAlmacenAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(remitoAlmacenAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	
	public RemitoAlmacenEntity toEntity(RemitoAlmacen remitoAlmacenNegocio) {
		RemitoAlmacenEntity remitoAlmacenEntityAPersistir = new RemitoAlmacenEntity();
		remitoAlmacenEntityAPersistir.setIdRemito(remitoAlmacenNegocio.getIdRemito());
		remitoAlmacenEntityAPersistir.setEstado(remitoAlmacenNegocio.getEstado());
		remitoAlmacenEntityAPersistir.setTipo(remitoAlmacenNegocio.getTipo());
		remitoAlmacenEntityAPersistir.setNumero(remitoAlmacenNegocio.getNro());	
		
		List <ItemRemitoAlmacenEntity> itemsAPersistir = new ArrayList<ItemRemitoAlmacenEntity>();
		List <ItemRemitoAlmacen> items = remitoAlmacenNegocio.getItemsRemito();
		
		for(ItemRemitoAlmacen ir: items)
			itemsAPersistir.add(ItemRemitoAlmacenDAO.getInstancia().toEntity(ir));
		
		remitoAlmacenEntityAPersistir.setItemsRemito(itemsAPersistir);
		
		return remitoAlmacenEntityAPersistir;
}

	public RemitoAlmacen toNegocio(RemitoAlmacenEntity remitoAlmacenRecuperado) {
				
		RemitoAlmacen remitoAlmacenNegocio = new RemitoAlmacen();		
		remitoAlmacenNegocio.setIdRemito(remitoAlmacenRecuperado.getIdRemito());
		remitoAlmacenNegocio.setEstado(remitoAlmacenRecuperado.getEstado());
		remitoAlmacenNegocio.setTipo(remitoAlmacenRecuperado.getTipo());
		remitoAlmacenNegocio.setNro(remitoAlmacenRecuperado.getNumero());
		
		
		List <ItemRemitoAlmacen> itemsANegocio = new ArrayList<ItemRemitoAlmacen>();
		List <ItemRemitoAlmacenEntity> items = remitoAlmacenRecuperado.getItemsRemito();
		
		for(ItemRemitoAlmacenEntity ir: items)
			itemsANegocio.add(ItemRemitoAlmacenDAO.getInstancia().toNegocio(ir));
		
		remitoAlmacenNegocio.setItemsRemito(itemsANegocio);
		
		return remitoAlmacenNegocio;	
	}
	
	
	
	
	
}
