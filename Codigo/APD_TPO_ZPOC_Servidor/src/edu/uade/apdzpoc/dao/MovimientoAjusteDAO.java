package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.excepciones.MovimientoException;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;

public class MovimientoAjusteDAO {

	private static MovimientoAjusteDAO instancia;

	private MovimientoAjusteDAO() {
	}

	public static MovimientoAjusteDAO getInstancia() {
		if (instancia == null)
			instancia = new MovimientoAjusteDAO();
		return instancia;
	}

	public MovimientoAjuste findByNro(Integer idMovimiento) throws MovimientoException {
		MovimientoAjuste resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		MovimientoAjusteEntity aux = (MovimientoAjusteEntity) s
				.createQuery("from MovimientoAjusteEntity where idMovimiento = ?").setInteger(0, idMovimiento)
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

	public List<MovimientoAjuste> getAll() {
		List<MovimientoAjuste> resultado = new ArrayList<MovimientoAjuste>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<MovimientoAjusteEntity> aux = (List<MovimientoAjusteEntity>) s.createQuery("from MovimientoAjusteEntity")
				.list();
		for (MovimientoAjusteEntity mae : aux) {
			resultado.add(this.toNegocio(mae));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public List<MovimientoAjuste> findBySubtipo(String estado) {
		List<MovimientoAjuste> resultado = new ArrayList<MovimientoAjuste>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<MovimientoAjusteEntity> aux = (List<MovimientoAjusteEntity>) s
				.createQuery("from MovimientoAjusteEntity where subtipo = ?").setString(0, estado).list();
		for (MovimientoAjusteEntity mae : aux) {
			resultado.add(this.toNegocio(mae));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public void save(MovimientoAjuste recuperado) {
		MovimientoAjusteEntity movimientoAjusteAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(movimientoAjusteAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	public MovimientoAjuste saveAndFetch(MovimientoAjuste recuperado) {
		MovimientoAjusteEntity movimientoAjusteAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(movimientoAjusteAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
		return this.toNegocio(movimientoAjusteAPersistir);
	}

	public MovimientoAjusteEntity toEntity(MovimientoAjuste movimientoAjusteNegocio) {
		MovimientoAjusteEntity movimientoAjusteEntityAPersistir = new MovimientoAjusteEntity();

		movimientoAjusteEntityAPersistir.setIdMovimiento(movimientoAjusteNegocio.getIdMovimiento());
		movimientoAjusteEntityAPersistir.setFecha(movimientoAjusteNegocio.getFecha());
		movimientoAjusteEntityAPersistir.setCantidad(movimientoAjusteNegocio.getCantidad());
		movimientoAjusteEntityAPersistir.setLegajoOperador(movimientoAjusteNegocio.getLegajoOperador());
		movimientoAjusteEntityAPersistir.setLegajoAutorizante(movimientoAjusteNegocio.getLegajoAutorizante());
		movimientoAjusteEntityAPersistir.setCausa(movimientoAjusteNegocio.getCausa());
		;

		ArticuloEntity artAux = ArticuloDAO.getInstancia().toEntity(movimientoAjusteNegocio.getArticulo());
		movimientoAjusteEntityAPersistir.setArticulo(artAux);

		LoteEntity loteAux = LoteDAO.getInstancia().toEntity(movimientoAjusteNegocio.getLote());
		movimientoAjusteEntityAPersistir.setLote(loteAux);

		return movimientoAjusteEntityAPersistir;
	}

	public MovimientoAjuste toNegocio(MovimientoAjusteEntity movimientoAjusteRecuperado) {

		MovimientoAjuste movimientoAjusteNegocio = new MovimientoAjuste();
		movimientoAjusteNegocio.setIdMovimiento(movimientoAjusteRecuperado.getIdMovimiento());
		movimientoAjusteNegocio.setFecha(movimientoAjusteRecuperado.getFecha());
		movimientoAjusteNegocio.setCantidad(movimientoAjusteRecuperado.getCantidad());
		movimientoAjusteNegocio.setLegajoOperador(movimientoAjusteRecuperado.getLegajoOperador());
		movimientoAjusteNegocio.setLegajoAutorizante(movimientoAjusteRecuperado.getLegajoAutorizante());
		movimientoAjusteNegocio.setCausa(movimientoAjusteRecuperado.getCausa());
		;

		Articulo artAux = ArticuloDAO.getInstancia().toNegocio(movimientoAjusteRecuperado.getArticulo());
		movimientoAjusteNegocio.setArticulo(artAux);

		Lote loteAux = LoteDAO.getInstancia().toNegocio(movimientoAjusteRecuperado.getLote());
		movimientoAjusteNegocio.setLote(loteAux);

		return movimientoAjusteNegocio;
	}

}
