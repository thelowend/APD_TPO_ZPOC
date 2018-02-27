package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.excepciones.MovimientoException;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;

public class MovimientoPedidoDAO {

	private static MovimientoPedidoDAO instancia;

	private MovimientoPedidoDAO() {
	}

	public static MovimientoPedidoDAO getInstancia() {
		if (instancia == null)
			instancia = new MovimientoPedidoDAO();
		return instancia;
	}

	public MovimientoPedido findByNro(Integer idMovimiento) throws MovimientoException {
		MovimientoPedido resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		MovimientoPedidoEntity aux = (MovimientoPedidoEntity) s
				.createQuery("from MovimientoPedidoEntity where idMovimiento = ?").setInteger(0, idMovimiento)
				.uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (aux != null) {
			resultado = this.toNegocio(aux);
		} else {
			throw new MovimientoException("No se encontró movimiento " + idMovimiento);
		}
		return resultado;
	}

	@SuppressWarnings("null")
	public List<MovimientoPedido> findByPW(Integer idPedido) {
		List<MovimientoPedido> resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<MovimientoPedidoEntity> aux = (List<MovimientoPedidoEntity>) s
				.createQuery("from MovimientoPedidoEntity mpe join mpe.pw pw where pw.idPedido = ?")
				.setInteger(0, idPedido).list();
		for (MovimientoPedidoEntity mpe : aux) {
			resultado.add(this.toNegocio(mpe));
		}

		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public List<MovimientoPedido> getAll() {
		List<MovimientoPedido> resultado = new ArrayList<MovimientoPedido>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<MovimientoPedidoEntity> aux = (List<MovimientoPedidoEntity>) s.createQuery("from MovimientoPedidoEntity")
				.list();
		for (MovimientoPedidoEntity mpe : aux) {
			resultado.add(this.toNegocio(mpe));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public void save(MovimientoPedido recuperado) {
		MovimientoPedidoEntity movimientoPedidoAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(movimientoPedidoAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	public MovimientoPedido saveAndFetch(MovimientoPedido recuperado) {
		MovimientoPedidoEntity movimientoPedidoAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(movimientoPedidoAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
		return this.toNegocio(movimientoPedidoAPersistir);
	}

	public MovimientoPedidoEntity toEntity(MovimientoPedido movimientoPedidoNegocio) {
		MovimientoPedidoEntity movimientoPedidoEntityAPersistir = new MovimientoPedidoEntity();

		movimientoPedidoEntityAPersistir.setIdMovimiento(movimientoPedidoNegocio.getIdMovimiento());
		movimientoPedidoEntityAPersistir.setFecha(movimientoPedidoNegocio.getFecha());
		movimientoPedidoEntityAPersistir.setCantidad(movimientoPedidoNegocio.getCantidad());

		ArticuloEntity artAux = ArticuloDAO.getInstancia().toEntity(movimientoPedidoNegocio.getArticulo());
		movimientoPedidoEntityAPersistir.setArticulo(artAux);

		PedidoWebEntity pwAux = PedidoWebDAO.getInstancia().toEntity(movimientoPedidoNegocio.getPw());
		movimientoPedidoEntityAPersistir.setPw(pwAux);

		return movimientoPedidoEntityAPersistir;
	}

	public MovimientoPedido toNegocio(MovimientoPedidoEntity movimientoPedidoRecuperado) {

		MovimientoPedido movimientoPedidoNegocio = new MovimientoPedido();
		movimientoPedidoNegocio.setIdMovimiento(movimientoPedidoRecuperado.getIdMovimiento());
		movimientoPedidoNegocio.setFecha(movimientoPedidoRecuperado.getFecha());
		movimientoPedidoNegocio.setCantidad(movimientoPedidoRecuperado.getCantidad());

		Articulo artAux = ArticuloDAO.getInstancia().toNegocio(movimientoPedidoRecuperado.getArticulo());
		movimientoPedidoNegocio.setArticulo(artAux);

		PedidoWeb pwAux = PedidoWebDAO.getInstancia().toNegocio(movimientoPedidoRecuperado.getPw());
		movimientoPedidoNegocio.setPw(pwAux);

		return movimientoPedidoNegocio;
	}

}
