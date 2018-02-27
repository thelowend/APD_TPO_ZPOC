package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.excepciones.ArticuloException;
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

	public Articulo findByCodigo(Integer codigoBarras) throws ArticuloException {
		Articulo resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		ArticuloEntity aux;
		try {
			aux = (ArticuloEntity) s
					.createQuery("select ae from ArticuloEntity ae where ae.codigoBarra = ?")
					.setInteger(0, codigoBarras).uniqueResult();
			
			s.getTransaction().commit();
			s.close();
			
			if (aux != null) {
				resultado = this.toNegocio(aux);
			} else {
				throw new ArticuloException("No se encontr� el art�culo de c�digo: '" + codigoBarras + "'.");
			}

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		articuloAPersistir.setPresentacion(articuloNegocio.getPresentacion());
		articuloAPersistir.setTamanio(articuloNegocio.getTamanio());
		articuloAPersistir.setStockDisponible(articuloNegocio.getStockDisponible());
		articuloAPersistir.setStockFisico(articuloNegocio.getStockFisico());
		articuloAPersistir.setStockPendienteEntrega(articuloNegocio.getStockPendienteEntrega());
		articuloAPersistir.setStockVirtual(articuloNegocio.getStockVirtual());

		List<LoteEntity> aux1 = new ArrayList<LoteEntity>();
		List<Lote> lotes = articuloNegocio.getLotes();
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
		articuloNegocio.setLotes(aux1);

		return articuloNegocio;
	}

}
