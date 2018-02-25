package edu.uade.apdzpoc.dao;



import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;





public class ItemRemitoAlmacenDAO {
	
	private static ItemRemitoAlmacenDAO instancia;
	
	private ItemRemitoAlmacenDAO() {}
	
	public static ItemRemitoAlmacenDAO getInstancia(){
		if(instancia == null)
			instancia = new ItemRemitoAlmacenDAO();
		return instancia;
	}	
	
	
	public void save(ItemRemitoAlmacen item) {
		
		ItemRemitoAlmacenEntity ItemAPersistir = this.toEntity(item);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ItemAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
		
	}
	
	
public ItemRemitoAlmacenEntity toEntity(ItemRemitoAlmacen item) {
		
		ItemRemitoAlmacenEntity itemAPersistir = new ItemRemitoAlmacenEntity();
		
		itemAPersistir.setIdItemRemitoAlmacen(item.getIdItemRemitoAlmacen());
		itemAPersistir.setCantidad(item.getCantidad());
	
		ArticuloEntity aux1 = ArticuloDAO.getInstancia().toEntity(item.getArticulo());
		itemAPersistir.setArticulo(aux1);
		
		UbicacionEntity ubicacionAux = UbicacionDAO.getInstancia().toEntity(item.getUbicacion());
		itemAPersistir.setUbicacion(ubicacionAux);
		
		return itemAPersistir;	
		
	}
	
	
	public ItemRemitoAlmacen toNegocio(ItemRemitoAlmacenEntity item) {
		
		ItemRemitoAlmacen itemNegocio = new ItemRemitoAlmacen();
		itemNegocio.setIdItemRemitoAlmacen(item.getIdItemRemitoAlmacen());
		itemNegocio.setCantidad(item.getCantidad());
	
		Articulo aux1 = ArticuloDAO.getInstancia().toNegocio(item.getArticulo());
		itemNegocio.setArticulo(aux1);
		
		Ubicacion ubicacionAux = UbicacionDAO.getInstancia().toNegocio(item.getUbicacion());
		itemNegocio.setUbicacion(ubicacionAux);
		
		return itemNegocio;
			
	}
	
	
	
	
	

}
