package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;


public class FacturaDAO {

	private static FacturaDAO instancia;
	
	private FacturaDAO() {}
	
	public static FacturaDAO getInstancia(){
		if(instancia == null)
			instancia = new FacturaDAO();
		return instancia;
	}

	
	public Factura findByCodigo(Integer idFactura){
		Factura resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		FacturaEntity aux = (FacturaEntity) s.createQuery("from FacturaEntity where idFactura = ?").setInteger(0, idFactura).uniqueResult();
		resultado = this.toNegocio(aux);
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	
	
	
	// Ya casteado en To Entity
	
	public void save(Factura factura) {
		
		FacturaEntity facturaAPersistir = this.toEntity(factura);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(facturaAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
		
	}
	
	//facturaAPersistir = Corresponde a una factura de Negocio que voy a guardar en bd
	
	public FacturaEntity toEntity(Factura factura) {
		
		FacturaEntity facturaAPersistir = new FacturaEntity();
		facturaAPersistir.setIdFactura(factura.getIdFactura());
		facturaAPersistir.setFechaVencimiento(factura.getFechaVencimiento());
		facturaAPersistir.setFechaEmision(factura.getFechaEmision());
		facturaAPersistir.setTotalFactura(factura.getTotalFactura());
		
		List<ItemFacturaEntity> aux1 = new ArrayList<ItemFacturaEntity>();	
		
		List <ItemFactura> facturas = factura.getItemsFactura();
		for(ItemFactura f : facturas)
			aux1.add(ItemFacturaDAO.getInstancia().toEntity(f));
		facturaAPersistir.setItemsFactura(aux1);

		return facturaAPersistir;
		
		
	}
	
	// Recupero de la BD y lo convierto en Negocio
	
	public Factura toNegocio(FacturaEntity facturaRecuperada) {
		
		Factura factura = new Factura();
		factura.setIdFactura(facturaRecuperada.getIdFactura());
		factura.setFechaVencimiento(facturaRecuperada.getFechaVencimiento());
		factura.setFechaEmision(facturaRecuperada.getFechaEmision());
		factura.setTotalFactura(facturaRecuperada.getTotalFactura());
		
		
		List<ItemFactura> aux1 = new ArrayList<ItemFactura>();	
		
		List <ItemFacturaEntity> items = facturaRecuperada.getItemsFactura();
		for(ItemFacturaEntity i : items)
			aux1.add(ItemFacturaDAO.getInstancia().toNegocio(i));
		factura.setItemsFactura(aux1);
		return factura;
	
	}
	

	
	
}
