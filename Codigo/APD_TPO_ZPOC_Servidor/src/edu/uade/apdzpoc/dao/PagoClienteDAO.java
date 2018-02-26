package edu.uade.apdzpoc.dao;

import java.sql.Date;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.excepciones.PagoClienteException;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;


public class PagoClienteDAO {

	private static PagoClienteDAO instancia;
	
	private PagoClienteDAO() {}
	
	public static PagoClienteDAO getInstancia(){
		if(instancia == null)
			instancia = new PagoClienteDAO();
		return instancia;
	}
	
	public PagoCliente findrecuperadoByNro(Integer IdPago) throws PagoClienteException {
		PagoCliente resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		PagoClienteEntity aux = (PagoClienteEntity) s.createQuery("from PagoClienteEntity where IdPago = ?").setInteger(0, IdPago).uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (aux != null) {
			resultado = this.toNegocio(aux);
		} else {
			throw new PagoClienteException("No se encontró el pago de cliente de número " + IdPago);
		}
		return resultado;
	}
	
	
	public void save(PagoCliente recuperado){
		PagoClienteEntity pagoAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(pagoAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	
public PagoClienteEntity toEntity(PagoCliente pagoNegocio) {
		
		PagoClienteEntity pagoAPersistir = new PagoClienteEntity();
		pagoAPersistir.setIdPago(pagoNegocio.getIdPago());
		pagoAPersistir.setMedioDePago(pagoNegocio.getMedioDePago());
		pagoAPersistir.setMonto(pagoNegocio.getMonto());
		pagoAPersistir.setFecha((Date) pagoNegocio.getFecha());
		
		FacturaEntity facturaAux = FacturaDAO.getInstancia().toEntity(pagoNegocio.getFactura());
		pagoAPersistir.setFactura(facturaAux);
		
		return pagoAPersistir;
		
		
	}

	public PagoCliente toNegocio(PagoClienteEntity pagoRecuperado) {
		
		PagoCliente pagoNegocio = new PagoCliente();
		pagoNegocio.setIdPago(pagoRecuperado.getIdPago());
		pagoNegocio.setMedioDePago(pagoRecuperado.getMedioDePago());
		pagoNegocio.setMonto(pagoRecuperado.getMonto());
		pagoNegocio.setFecha((Date) pagoRecuperado.getFecha());
		
		Factura facturaAux = FacturaDAO.getInstancia().toNegocio(pagoRecuperado.getFactura());
		pagoNegocio.setFactura(facturaAux);
		
		return pagoNegocio;
		
	}
	
	
	
	
	
}
