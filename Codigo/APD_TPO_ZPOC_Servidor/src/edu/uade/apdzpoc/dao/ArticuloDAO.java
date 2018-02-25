package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;

public class ArticuloDAO {

	private static ArticuloDAO instancia;

	private ArticuloDAO() {
	}

	public static ArticuloDAO getInstancia() {
		if (instancia == null)
			instancia = new ArticuloDAO();
		return instancia;
	}

	public Articulo findrecuperadoByCodigo(Integer codigoBarras) {
		Articulo resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		ArticuloEntity aux = (ArticuloEntity) s
				.createQuery("select ae from ArticuloEntity ae inner join ae.lotes where codigoBarra = ?")
				.setInteger(0, codigoBarras).uniqueResult();
		resultado = this.toNegocio(aux);
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public List<Articulo> getAll() {
		List<Articulo> resultado = new ArrayList<Articulo>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ArticuloEntity> aux = (List<ArticuloEntity>) s.createQuery("from ArticuloEntity").list();
		for (ArticuloEntity ae : aux) {
			resultado.add(this.toNegocio(ae));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}

	public void save(Articulo recuperado) {
		ArticuloEntity ArticuloAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ArticuloAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public ArticuloEntity toEntity(Articulo articuloNegocio) {
		ArticuloEntity articuloAPersistir = new ArticuloEntity();
		articuloAPersistir.setCodigoBarra(articuloNegocio.getCodigoBarra());
		articuloAPersistir.setNombreArticulo(articuloNegocio.getNombreArticulo());
		articuloAPersistir.setDescripcion(articuloNegocio.getDescripcion());
		articuloAPersistir.setCantidadCompra(articuloNegocio.getCantidadCompra());
		articuloAPersistir.setPrecioVenta(articuloNegocio.getPrecioVenta());
		articuloAPersistir.setTamanio(articuloNegocio.getTamanio());
		articuloAPersistir.setStockDisponible(articuloNegocio.getStockDisponible());
		articuloAPersistir.setStockFisico(articuloNegocio.getStockFisico());
		articuloAPersistir.setStockPendienteEntrega(articuloNegocio.getStockPendienteEntrega());
		articuloAPersistir.setStockVirtual(articuloNegocio.getStockVirtual());

		List<LoteEntity> aux1 = new ArrayList<LoteEntity>();
		List<Lote> lotes = articuloNegocio.getLote();
		for (Lote l : lotes)
			aux1.add(LoteDAO.getInstancia().toEntity(l));
		articuloAPersistir.setLotes(aux1);
		return articuloAPersistir;
	}

	public Articulo toNegocio(ArticuloEntity articuloRecuperado) {
		Articulo articuloNegocio = new Articulo();

		articuloNegocio.setCodigoBarra(articuloRecuperado.getCodigoBarra());
		articuloNegocio.setNombreArticulo(articuloRecuperado.getNombreArticulo());
		articuloNegocio.setDescripcion(articuloRecuperado.getDescripcion());
		articuloNegocio.setCantidadCompra(articuloRecuperado.getCantidadCompra());
		articuloNegocio.setPrecioVenta(articuloRecuperado.getPrecioVenta());
		articuloNegocio.setTamanio(articuloRecuperado.getTamanio());
		articuloNegocio.setStockDisponible(articuloRecuperado.getStockDisponible());
		articuloNegocio.setStockFisico(articuloRecuperado.getStockFisico());
		articuloNegocio.setStockPendienteEntrega(articuloRecuperado.getStockPendienteEntrega());
		articuloNegocio.setStockVirtual(articuloRecuperado.getStockVirtual());

		List<Lote> aux1 = new ArrayList<Lote>();
		List<LoteEntity> lotes = articuloRecuperado.getLotes();
		for (LoteEntity l : lotes)
			aux1.add(LoteDAO.getInstancia().toNegocio(l));
		articuloNegocio.setLote(aux1);

		return articuloNegocio;
	}

}
