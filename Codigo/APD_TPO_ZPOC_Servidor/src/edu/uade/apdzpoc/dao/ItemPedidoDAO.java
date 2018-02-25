package edu.uade.apdzpoc.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;

import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;





public class ItemPedidoDAO {

	private static ItemPedidoDAO instancia;
	
	private ItemPedidoDAO() {}
	
	public static ItemPedidoDAO getInstancia(){
		if(instancia == null)
			instancia = new ItemPedidoDAO();
		return instancia;
	}	
	
	
	
	
	
	
	
	public void save(ItemPedido item) {
		
		ItemPedidoEntity ItemAPersistir = this.toEntity(item);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ItemAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public ItemPedidoEntity toEntity(ItemPedido item) {
		
		ItemPedidoEntity ItemAPersistir = new ItemPedidoEntity();
		ItemAPersistir.setIdItemPedido(item.getIdItemPedido());
		ItemAPersistir.setCantidad(item.getCantidad());
		ItemAPersistir.setEstado(item.getEstado());
		
		
		
		ArticuloEntity aux1 = ArticuloDAO.getInstancia().toEntity(item.getArticulo());
		ItemAPersistir.setArticulo(aux1);
		
		return ItemAPersistir;	
		
	}
	
	
	public ItemPedido toNegocio(ItemPedidoEntity item) {
		
		ItemPedido itemNegocio = new ItemPedido();
		itemNegocio.setIdItemPedido(item.getIdItemPedido());
		itemNegocio.setCantidad(item.getCantidad());
		itemNegocio.setEstado(item.getEstado());;
		
		Articulo aux1 = ArticuloDAO.getInstancia().toNegocio(item.getArticulo());
		itemNegocio.setArticulo(aux1);
		
		return itemNegocio;
			
	}
	
	
	
	
}
