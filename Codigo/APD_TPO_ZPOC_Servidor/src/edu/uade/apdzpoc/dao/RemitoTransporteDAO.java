package edu.uade.apdzpoc.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;


public class RemitoTransporteDAO {

	private static RemitoTransporteDAO instancia;
	
	private RemitoTransporteDAO() {}
	
	public static RemitoTransporteDAO getInstancia(){
		if(instancia == null)
			instancia = new RemitoTransporteDAO();
		return instancia;
	}	
	
	public RemitoTransporte findByCodigo(Integer idRemito){
		RemitoTransporte resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		RemitoTransporteEntity aux = (RemitoTransporteEntity) s.createQuery("select rte from RemitoTransporteEntity rte  where idRemito = ?").setInteger(0, idRemito).uniqueResult();
		resultado = this.toNegocio(aux);
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	public List<RemitoTransporte> getAll(){
		List<RemitoTransporte> resultado = new ArrayList<RemitoTransporte>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<RemitoTransporteEntity> aux = (List<RemitoTransporteEntity>) s.createQuery("from RemitoTransporteEntity").list();
		for(RemitoTransporteEntity rte : aux)
		{
			resultado.add(this.toNegocio(rte));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	

	public void save(RemitoTransporte recuperado){
		RemitoTransporteEntity remitoTransporteAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(remitoTransporteAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	
	public RemitoTransporteEntity toEntity(RemitoTransporte remitoTransporteNegocio) {
		RemitoTransporteEntity remitoTransporteEntityAPersistir = new RemitoTransporteEntity();
		remitoTransporteEntityAPersistir.setIdRemito(remitoTransporteNegocio.getIdRemito());
		remitoTransporteEntityAPersistir.setEmpresaTransporte(remitoTransporteNegocio.getEmpresaTransporte());
		
		PedidoWebEntity aux1 = PedidoWebDAO.getInstancia().toEntity(remitoTransporteNegocio.getPedido());
		return remitoTransporteEntityAPersistir;
}

	public RemitoTransporte toNegocio(RemitoTransporteEntity remitoTransporteRecuperado) {
		RemitoTransporte remitoTransporteNegocio = new RemitoTransporte();
		
		remitoTransporteNegocio.setIdRemito(remitoTransporteRecuperado.getIdRemito());
		remitoTransporteNegocio.getEmpresaTransporte(remitoTransporteRecuperado.getEmpresaTransporte());
		
		PedidoWeb aux1 = PedidoWebDAO.getInstancia().toNegocio(remitoTransporteRecuperado.getPedido());
				
		return remitoTransporteNegocio;	
	}
	

	
	
	
	
	
	
}
