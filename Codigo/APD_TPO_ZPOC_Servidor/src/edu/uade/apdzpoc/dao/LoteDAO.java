package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.excepciones.LoteException;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;

public class LoteDAO {

	private static LoteDAO instancia;

	private LoteDAO() {
	}

	public static LoteDAO getInstancia() {
		if (instancia == null)
			instancia = new LoteDAO();
		return instancia;
	}

	public Lote findByNro(Integer idInterno) throws LoteException {
		Lote resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		LoteEntity aux = (LoteEntity) s
				.createQuery("select le from LoteEntity le where le.idInterno = ?")
				.setInteger(0, idInterno).uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (aux != null) {
			resultado = this.toNegocio(aux);
		} else {
			throw new LoteException("No se encontró el lote de número " + idInterno);
		}
		return resultado;
	}

	public List<Lote> getAll() {
		List<Lote> resultado = new ArrayList<Lote>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<LoteEntity> aux = (List<LoteEntity>) s.createQuery("from LoteEntity").list();
		for (LoteEntity le : aux) {
			resultado.add(this.toNegocio(le));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	public List<Lote> getAllByArticulo(Articulo art) {
		List<Lote> resultado = new ArrayList<Lote>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<LoteEntity> aux = (List<LoteEntity>) s
				.createQuery(
						"FROM LoteEntity le JOIN le.articulo ar WHERE ar.CodigoBarra = ? ORDER BY le.vencimiento ASC")
				.setInteger(0, art.getCodigoBarra()).list();
		for (LoteEntity le : aux) {
			resultado.add(this.toNegocio(le));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public List<Lote> getAllByVencimiento() {
		List<Lote> resultado = new ArrayList<Lote>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<LoteEntity> aux = (List<LoteEntity>) s.createQuery("FROM LoteEntity le ORDER BY le.vencimiento ASC")
				.list();
		for (LoteEntity le : aux) {
			resultado.add(this.toNegocio(le));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public void save(Lote recuperado) {
		LoteEntity loteAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(loteAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public LoteEntity toEntity(Lote loteNegocio) {

		LoteEntity loteAPersistir = new LoteEntity();
		loteAPersistir.setIdInterno(loteNegocio.getIdInterno());
		loteAPersistir.setNroLote(loteNegocio.getNroLote());
		java.sql.Date d = new Date(loteNegocio.getVencimiento().getTime());
		loteAPersistir.setVencimiento(d);
		// loteAPersistir.setArticulo(ArticuloDAO.getInstancia().toEntity(loteNegocio.getArticulo()));

		List<UbicacionEntity> aux1 = new ArrayList<UbicacionEntity>();
		List<Ubicacion> ubicaciones = loteNegocio.getUbicaciones();
		for (Ubicacion u : ubicaciones)
			aux1.add(UbicacionDAO.getInstancia().toEntity(u));
		loteAPersistir.setUbicaciones(aux1);
		return loteAPersistir;

	}

	public Lote toNegocio(LoteEntity loteRecuperado) {

		Lote LoteNegocio = new Lote();
		LoteNegocio.setIdInterno(loteRecuperado.getIdInterno());
		LoteNegocio.setNroLote(loteRecuperado.getNroLote());
		LoteNegocio.setVencimiento(loteRecuperado.getVencimiento());
		// LoteNegocio.setArticulo(ArticuloDAO.getInstancia().toNegocio(loteRecuperado.getArticulo()));

		List<Ubicacion> aux1 = new ArrayList<Ubicacion>();
		List<UbicacionEntity> ubicaciones = loteRecuperado.getUbicaciones();
		for (UbicacionEntity u : ubicaciones)
			aux1.add(UbicacionDAO.getInstancia().toNegocio(u));
		LoteNegocio.setUbicaciones(aux1);
		return LoteNegocio;

	}

}
