package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
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

	public Lote findrecuperadoByNro(Integer nroLote) {
		Lote resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		LoteEntity aux = (LoteEntity) s
				.createQuery("select le from LoteEntity le inner join le.ubicaciones where nroLote = ?")
				.setInteger(0, nroLote).uniqueResult();
		resultado = this.toNegocio(aux);
		s.getTransaction().commit();
		s.close();
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
		loteAPersistir.setNroLote(loteNegocio.getNroLote());
		loteAPersistir.setVencimiento(loteNegocio.getVencimiento());

		loteAPersistir.setArticulo(ArticuloDAO.getInstancia().toEntity(loteNegocio.getArticulo()));

		List<UbicacionEntity> aux1 = new ArrayList<UbicacionEntity>();
		List<Ubicacion> ubicaciones = loteNegocio.getUbicaciones();
		for (Ubicacion u : ubicaciones)
			aux1.add(UbicacionDAO.getInstancia().toEntity(u));
		loteAPersistir.setUbicaciones(aux1);
		return loteAPersistir;

	}

	public Lote toNegocio(LoteEntity loteRecuperado) {

		Lote LoteNegocio = new Lote();
		LoteNegocio.setNroLote(loteRecuperado.getNroLote());
		LoteNegocio.setVencimiento(loteRecuperado.getVencimiento());

		LoteNegocio.setArticulo(ArticuloDAO.getInstancia().toNegocio(loteRecuperado.getArticulo()));

		List<Ubicacion> aux1 = new ArrayList<Ubicacion>();
		List<UbicacionEntity> ubicaciones = loteRecuperado.getUbicaciones();
		for (UbicacionEntity u : ubicaciones)
			aux1.add(UbicacionDAO.getInstancia().toNegocio(u));
		LoteNegocio.setUbicaciones(aux1);
		return LoteNegocio;

	}
	
	public List<Lote> getAllByArticulo(Articulo art) {
		List<Lote> resultado = new ArrayList<Lote>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<LoteEntity> aux = (List<LoteEntity>) s.createQuery("FROM LoteEntity le JOIN le.articulo ar WHERE ar.CodigoBarra = ? ORDER BY le.vencimiento ASC").setInteger(0, art.getCodigoBarra()).list();
		for (LoteEntity le : aux) {
			resultado.add(this.toNegocio(le));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

}
