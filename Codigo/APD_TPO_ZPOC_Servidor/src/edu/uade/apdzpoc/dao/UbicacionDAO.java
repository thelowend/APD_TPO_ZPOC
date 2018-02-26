package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.excepciones.UbicacionException;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;

public class UbicacionDAO {

	private static UbicacionDAO instancia;

	private UbicacionDAO() {
	}

	public static UbicacionDAO getInstancia() {
		if (instancia == null)
			instancia = new UbicacionDAO();
		return instancia;
	}

	public Ubicacion findrecuperadoByNro(Integer idUbicacion) throws UbicacionException {
		Ubicacion resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		UbicacionEntity aux = (UbicacionEntity) s.createQuery("select ue from UbicacionEntity ue where idUbicacion = ?")
				.setInteger(0, idUbicacion).uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (aux != null) {
			resultado = this.toNegocio(aux);
		} else {
			throw new UbicacionException("No se encontró la ubicación " + idUbicacion);
		}
		return resultado;
	}

	public List<Ubicacion> getAll() {
		List<Ubicacion> resultado = new ArrayList<Ubicacion>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<UbicacionEntity> aux = (List<UbicacionEntity>) s.createQuery("from UbicacionEntity").list();
		for (UbicacionEntity ue : aux) {
			resultado.add(this.toNegocio(ue));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public Ubicacion getUbicacionLibre() throws UbicacionException {
		Ubicacion resultado = new Ubicacion();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		UbicacionEntity aux = (UbicacionEntity) s.createQuery("from UbicacionEntity where estado = 'Libre'")
				.setFirstResult(0).setMaxResults(1).uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (aux != null) {
			resultado = this.toNegocio(aux);
		} else {
			throw new UbicacionException("¡El depósito está lleno!");
		}
		return resultado;
	}

	public List<Ubicacion> getAllFree() {
		List<Ubicacion> resultado = new ArrayList<Ubicacion>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<UbicacionEntity> aux = (List<UbicacionEntity>) s.createQuery("from UbicacionEntity where estado = 'Libre'")
				.list();
		for (UbicacionEntity ue : aux) {
			resultado.add(this.toNegocio(ue));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public void save(Ubicacion recuperada) {
		UbicacionEntity ubicacionAPersistir = this.toEntity(recuperada);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ubicacionAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public UbicacionEntity toEntity(Ubicacion ubicacionNegocio) {
		UbicacionEntity ubicacionAPersistir = new UbicacionEntity();
		ubicacionAPersistir.setIdUbicacion(ubicacionNegocio.getIdUbicacion());
		ubicacionAPersistir.setNombre(ubicacionNegocio.getNombre());
		ubicacionAPersistir.setBloque(ubicacionNegocio.getBloque());
		ubicacionAPersistir.setCalle(ubicacionNegocio.getCalle());
		ubicacionAPersistir.setCapacidad(ubicacionNegocio.getCapacidad());
		ubicacionAPersistir.setEstado(ubicacionNegocio.getEstado());
		ubicacionAPersistir.setEstante(ubicacionNegocio.getEstante());
		ubicacionAPersistir.setPosicion(ubicacionNegocio.getPosicion());
		ubicacionAPersistir.setCapacidadInicial(ubicacionNegocio.getCapacidadInicial());
		return ubicacionAPersistir;
	}

	public Ubicacion toNegocio(UbicacionEntity ubicacionRecuperada) {
		Ubicacion ubicacionNegocio = new Ubicacion();
		ubicacionNegocio.setIdUbicacion(ubicacionRecuperada.getIdUbicacion());
		ubicacionNegocio.setNombre(ubicacionRecuperada.getNombre());
		ubicacionNegocio.setBloque(ubicacionRecuperada.getBloque());
		ubicacionNegocio.setCalle(ubicacionRecuperada.getCalle());
		ubicacionNegocio.setCapacidad(ubicacionRecuperada.getCapacidad());
		ubicacionNegocio.setEstado(ubicacionRecuperada.getEstado());
		ubicacionNegocio.setEstante(ubicacionRecuperada.getEstante());
		ubicacionNegocio.setPosicion(ubicacionRecuperada.getPosicion());
		ubicacionNegocio.setCapacidadInicial(ubicacionRecuperada.getCapacidadInicial());
		return ubicacionNegocio;
	}

}
