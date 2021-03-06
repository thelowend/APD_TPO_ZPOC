package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.excepciones.MovimientoException;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;

public class MovimientoCompraDAO {

	private static MovimientoCompraDAO instancia;

	private MovimientoCompraDAO() {
	}

	public static MovimientoCompraDAO getInstancia() {
		if (instancia == null)
			instancia = new MovimientoCompraDAO();
		return instancia;
	}

	public MovimientoCompra findByNro(Integer idMovimiento) throws MovimientoException {
		MovimientoCompra resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		MovimientoCompraEntity aux = (MovimientoCompraEntity) s
				.createQuery("from MovimientoCompraEntity where idMovimiento = ?").setInteger(0, idMovimiento)
				.uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (aux != null) {
			resultado = this.toNegocio(aux);
		} else {
			throw new MovimientoException("No se encontr� movimiento " + idMovimiento);
		}
		return resultado;
	}

	@SuppressWarnings("null")
	public List<MovimientoCompra> findByOC(Integer idOC) {
		List<MovimientoCompra> resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<MovimientoCompraEntity> aux = (List<MovimientoCompraEntity>) s
				.createQuery("from MovimientoCompraEntity mce join mce.oc oc where oc.idOC = ?").setInteger(0, idOC)
				.list();
		for (MovimientoCompraEntity mce : aux) {
			resultado.add(this.toNegocio(mce));
		}

		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public List<MovimientoCompra> getAll() {
		List<MovimientoCompra> resultado = new ArrayList<MovimientoCompra>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<MovimientoCompraEntity> aux = (List<MovimientoCompraEntity>) s.createQuery("from MovimientoCompraEntity")
				.list();
		for (MovimientoCompraEntity mce : aux) {
			resultado.add(this.toNegocio(mce));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public void save(MovimientoCompra recuperado) {
		MovimientoCompraEntity movimientoCompraAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(movimientoCompraAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	public MovimientoCompra saveAndFetch(MovimientoCompra recuperado) {
		MovimientoCompraEntity movimientoCompraAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(movimientoCompraAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
		return this.toNegocio(movimientoCompraAPersistir);
	}
	
	public MovimientoCompraEntity toEntity(MovimientoCompra movimientoCompraNegocio) {
		MovimientoCompraEntity movimientoCompraEntityAPersistir = new MovimientoCompraEntity();

		movimientoCompraEntityAPersistir.setIdMovimiento(movimientoCompraNegocio.getIdMovimiento());
		movimientoCompraEntityAPersistir.setFecha(movimientoCompraNegocio.getFecha());
		movimientoCompraEntityAPersistir.setCantidad(movimientoCompraNegocio.getCantidad());

		ArticuloEntity artAux = ArticuloDAO.getInstancia().toEntity(movimientoCompraNegocio.getArticulo());
		movimientoCompraEntityAPersistir.setArticulo(artAux);
		
		Lote lote = movimientoCompraNegocio.getLote();
		LoteEntity loteAux = null;
		if(lote != null) {
			loteAux = LoteDAO.getInstancia().toEntity(lote);
		}

		//si no hay lote que no lo persista
		movimientoCompraEntityAPersistir.setLote(loteAux);

		OrdenCompraEntity ocAux = OrdenCompraDAO.getInstancia().toEntity(movimientoCompraNegocio.getOc());
		movimientoCompraEntityAPersistir.setOc(ocAux);

		return movimientoCompraEntityAPersistir;
	}

	public MovimientoCompra toNegocio(MovimientoCompraEntity movimientoCompraRecuperado) {

		MovimientoCompra movimientoCompraNegocio = new MovimientoCompra();
		movimientoCompraNegocio.setIdMovimiento(movimientoCompraRecuperado.getIdMovimiento());
		movimientoCompraNegocio.setFecha(movimientoCompraRecuperado.getFecha());
		movimientoCompraNegocio.setCantidad(movimientoCompraRecuperado.getCantidad());

		Articulo artAux = ArticuloDAO.getInstancia().toNegocio(movimientoCompraRecuperado.getArticulo());
		movimientoCompraNegocio.setArticulo(artAux);

		Lote loteAux = LoteDAO.getInstancia().toNegocio(movimientoCompraRecuperado.getLote());
		movimientoCompraNegocio.setLote(loteAux);

		OrdenCompra ocAux = OrdenCompraDAO.getInstancia().toNegocio(movimientoCompraRecuperado.getOc());
		movimientoCompraNegocio.setOc(ocAux);

		return movimientoCompraNegocio;
	}

}
