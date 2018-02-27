package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.enums.EstadoOC;
import edu.uade.apdzpoc.excepciones.OrdenCompraException;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;

public class OrdenCompraDAO {

	private static OrdenCompraDAO instancia;

	private OrdenCompraDAO() {
	}

	public static OrdenCompraDAO getInstancia() {
		if (instancia == null)
			instancia = new OrdenCompraDAO();
		return instancia;
	}

	public OrdenCompra findByCodigo(Integer idOC) throws OrdenCompraException {
		OrdenCompra resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		OrdenCompraEntity aux = (OrdenCompraEntity) s.createQuery("from OrdenCompraEntity where idOC = ?")
				.setInteger(0, idOC).uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (aux != null) {
			resultado = this.toNegocio(aux);
		} else {
			throw new OrdenCompraException("No se encontr� la orden de compra " + idOC);
		}
		return resultado;
	}

	public List<OrdenCompra> getAll() {
		List<OrdenCompra> resultado = new ArrayList<OrdenCompra>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<OrdenCompraEntity> aux = (List<OrdenCompraEntity>) s.createQuery("from  OrdenCompraEntity").list();
		for (OrdenCompraEntity oce : aux) {
			resultado.add(this.toNegocio(oce));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public List<OrdenCompra> findByEstado(EstadoOC estado) {
		List<OrdenCompra> resultado = new ArrayList<OrdenCompra>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<OrdenCompraEntity> aux = (List<OrdenCompraEntity>) s.createQuery("from OrdenCompraEntity where estado = ?")
				.setString(0, estado.toString()).list();
		for (OrdenCompraEntity oce : aux) {
			resultado.add(this.toNegocio(oce));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public void save(OrdenCompra ordenCompra) {

		OrdenCompraEntity ordenCompraAPersistir = this.toEntity(ordenCompra);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ordenCompraAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();

	}

	public OrdenCompraEntity toEntity(OrdenCompra ordenCompraNegocio) {
		OrdenCompraEntity ordenCompraEntityAPersistir = new OrdenCompraEntity();
		ordenCompraEntityAPersistir.setIdOC(ordenCompraNegocio.getIdOC());
		ordenCompraEntityAPersistir.setFecha(ordenCompraNegocio.getFecha());
		ordenCompraEntityAPersistir.setEstado(ordenCompraNegocio.getEstado());
		ordenCompraEntityAPersistir.setCantidad(ordenCompraNegocio.getCantidad());

		ArticuloEntity artAux = ArticuloDAO.getInstancia().toEntity(ordenCompraNegocio.getArticulo());
		ordenCompraEntityAPersistir.setArticulo(artAux);

		//TODO Verificar si es obligatorio o no
		if(ordenCompraNegocio.getLote() != null) {
            LoteEntity loteAux = LoteDAO.getInstancia().toEntity(ordenCompraNegocio.getLote());
            ordenCompraEntityAPersistir.setLote(loteAux);
        }

		PedidoWebEntity pedidoAux = PedidoWebDAO.getInstancia().toEntity(ordenCompraNegocio.getPedidoW());
		ordenCompraEntityAPersistir.setPedidoW(pedidoAux);

		return ordenCompraEntityAPersistir;
	}

	public OrdenCompra toNegocio(OrdenCompraEntity ordenCompraRecuperada) {
		OrdenCompra ordenCompraNegocio = new OrdenCompra();
		ordenCompraNegocio.setIdOC(ordenCompraRecuperada.getIdOC());
		ordenCompraNegocio.setFecha(ordenCompraNegocio.getFecha());
		ordenCompraNegocio.setEstado(ordenCompraNegocio.getEstado());
		ordenCompraNegocio.setCantidad(ordenCompraNegocio.getCantidad());

		Articulo artAux = ArticuloDAO.getInstancia().toNegocio(ordenCompraRecuperada.getArticulo());
		ordenCompraNegocio.setArticulo(artAux);

        //TODO Verificar si es obligatorio o no
		if(ordenCompraRecuperada.getLote() != null) {
            Lote loteAux = LoteDAO.getInstancia().toNegocio(ordenCompraRecuperada.getLote());
            ordenCompraNegocio.setLote(loteAux);
        }

		PedidoWeb pedidoAux = PedidoWebDAO.getInstancia().toNegocio(ordenCompraRecuperada.getPedidoW());
		ordenCompraNegocio.setPedidoW(pedidoAux);

		return ordenCompraNegocio;
	}

	
}
