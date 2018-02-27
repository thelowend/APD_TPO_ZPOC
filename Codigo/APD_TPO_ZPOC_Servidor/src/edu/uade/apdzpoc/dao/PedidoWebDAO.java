package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.enums.EstadoPedido;
import edu.uade.apdzpoc.excepciones.PedidoWebException;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;

public class PedidoWebDAO {

	private static PedidoWebDAO instancia;

	private PedidoWebDAO() {
	}

	public static PedidoWebDAO getInstancia() {
		if (instancia == null)
			instancia = new PedidoWebDAO();
		return instancia;
	}

	public PedidoWeb findByCodigo(int idPedido) throws PedidoWebException {
		PedidoWeb resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		PedidoWebEntity aux = (PedidoWebEntity) s.createQuery("select pe from PedidoWebEntity pe  where idPedido = ?").setInteger(0, idPedido).uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (aux != null) {
			resultado = this.toNegocio(aux);
		} else {
			throw new PedidoWebException("No se encontró el pedido de número " + idPedido);
		}
		return resultado;
	}

	public List<PedidoWeb> getAll() {
		List<PedidoWeb> resultado = new ArrayList<PedidoWeb>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<PedidoWebEntity> aux = (List<PedidoWebEntity>) s.createQuery("from PedidoWebEntity").list();
		for (PedidoWebEntity pe : aux) {
			resultado.add(this.toNegocio(pe));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public List<PedidoWeb> getAllDespachables() {
		List<PedidoWeb> resultado = new ArrayList<PedidoWeb>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<PedidoWebEntity> aux = (List<PedidoWebEntity>) s
				.createQuery("from PedidoWebEntity where estadoPedido = ? OrderBy fechaGeneracion ASC")
				.setString(0, "Pendiente_Despacho").list();
		for (PedidoWebEntity pe : aux) {
			resultado.add(this.toNegocio(pe));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public List<PedidoWeb> findByEstado(EstadoPedido estado) {
		List<PedidoWeb> resultado = new ArrayList<PedidoWeb>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<PedidoWebEntity> aux = (List<PedidoWebEntity>) s.createQuery("from PedidoWebEntity where estado = ?")
				.setString(0, estado.toString()).list();
		for (PedidoWebEntity oce : aux) {
			resultado.add(this.toNegocio(oce));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public List<PedidoWeb> getAllbyArticulo(Integer codigoBarra, EstadoPedido estado) {
		List<PedidoWeb> resultado = new ArrayList<PedidoWeb>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<PedidoWebEntity> aux = (List<PedidoWebEntity>) s.createQuery(
				"from PedidoWebEntity pe join pe.items.articulo a where a.codigoBarra = ? AND estado = ? OrderBy fechaGeneracion ASC")
				.setInteger(0, codigoBarra).setString(1, estado.toString()).list();
		for (PedidoWebEntity pe : aux) {
			resultado.add(this.toNegocio(pe));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public void save(PedidoWeb recuperado) {
		PedidoWebEntity pedidoWebAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(pedidoWebAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	
	
	public PedidoWeb saveAndFetch(PedidoWeb recuperado) {
		PedidoWebEntity pedidoWebAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(pedidoWebAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
		
		return this.toNegocio(pedidoWebAPersistir);
	}
	public PedidoWebEntity toEntity(PedidoWeb pedidoWebNegocio) {
		PedidoWebEntity pedidoWebEntityAPersistir = new PedidoWebEntity();
		pedidoWebEntityAPersistir.setIdPedido(pedidoWebNegocio.getIdPedido());
		pedidoWebEntityAPersistir.setDireccionPedido(pedidoWebNegocio.getDireccionPedido());
		pedidoWebEntityAPersistir.setEstadoPedido(pedidoWebNegocio.getEstadoPedido());
		pedidoWebEntityAPersistir.setFechaDeEntrega(pedidoWebNegocio.getFechaDeEntrega());
		pedidoWebEntityAPersistir.setFechaDespacho(pedidoWebNegocio.getFechaDespacho());
		pedidoWebEntityAPersistir.setFechaGeneracion(pedidoWebNegocio.getFechaGeneracion());

		ClienteEntity clAux = ClienteDAO.getInstancia().toEntity(pedidoWebNegocio.getCliente());
		pedidoWebEntityAPersistir.setCliente(clAux);

		List<ItemPedidoEntity> itemsAPersistir = new ArrayList<ItemPedidoEntity>();
		List<ItemPedido> items = pedidoWebNegocio.getItems();
		for (ItemPedido it : items)
			itemsAPersistir.add(ItemPedidoDAO.getInstancia().toEntity(it));

		pedidoWebEntityAPersistir.setItems(itemsAPersistir);

		return pedidoWebEntityAPersistir;
	}

	public PedidoWeb toNegocio(PedidoWebEntity pedidoWebRecuperado) {

		PedidoWeb pedidoWebNegocio = new PedidoWeb();
		pedidoWebNegocio.setIdPedido(pedidoWebRecuperado.getIdPedido());
		pedidoWebNegocio.setDireccionPedido(pedidoWebRecuperado.getDireccionPedido());
		pedidoWebNegocio.setEstadoPedido(pedidoWebRecuperado.getEstadoPedido());
		pedidoWebNegocio.setFechaDeEntrega(pedidoWebRecuperado.getFechaDeEntrega());
		pedidoWebNegocio.setFechaDespacho(pedidoWebRecuperado.getFechaDespacho());
		pedidoWebNegocio.setFechaGeneracion(pedidoWebRecuperado.getFechaGeneracion());

		Cliente clAux = ClienteDAO.getInstancia().toNegocio(pedidoWebRecuperado.getCliente());
		pedidoWebNegocio.setCliente(clAux);

		List<ItemPedido> itemsNegocio = new ArrayList<ItemPedido>();
		List<ItemPedidoEntity> items = pedidoWebRecuperado.getItems();
		for (ItemPedidoEntity it : items)
			itemsNegocio.add(ItemPedidoDAO.getInstancia().toNegocio(it));

		pedidoWebNegocio.setItems(itemsNegocio);

		return pedidoWebNegocio;
	}

}
