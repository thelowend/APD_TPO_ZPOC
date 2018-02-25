package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.FacturaEntity;
import edu.uade.apdzpoc.entidades.ItemFacturaEntity;
import edu.uade.apdzpoc.entidades.PagoClienteEntity;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.Factura;
import edu.uade.apdzpoc.negocio.Pago_de_Cliente;

public class PagoClienteDAO {
	
private static PagoClienteDAO instancia;
	
	private PagoClienteDAO() {}
	
	public static PagoClienteDAO getInstancia(){
		if(instancia == null)
			instancia = new PagoClienteDAO();
		return instancia;
	}
	
public void save(Pago_de_Cliente pago) {
		
		PagoClienteEntity PagoAPersistir = this.toEntity(pago);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(PagoAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
		
	}
	
//PagoAPersistir = Corresponde a una Pago de Negocio que voy a guardar en bd

	public PagoClienteEntity toEntity(Pago_de_Cliente pago) {
		
		PagoClienteEntity PagoAPersistir = new PagoClienteEntity();
		PagoAPersistir.setFecha(pago.getFecha());
		PagoAPersistir.setIdPago(pago.getIdPago());
		PagoAPersistir.setMedioDePago(pago.getMedioDePago());
		PagoAPersistir.setMonto(pago.getMonto());
		
		
		FacturaEntity aux1 = new FacturaEntity();	
		Factura factura = pago.getFactura();
		aux1.add(FacturaDAO.getInstancia().toEntity(factura));
		PagoAPersistir.setFactura(aux1);

		return PagoAPersistir;
		
		
	}
	
	// Recupero de la BD y lo convierto en Negocio
	
	public Pago_de_Cliente toNegocio(PagoClienteEntity PagoRecuperado) {
		
		Pago_de_Cliente pago = new Pago_de_Cliente();
		pago.setFecha(pago.getFecha());
		pago.setIdPago(pago.getIdPago());
		pago.setMedioDePago(pago.getMedioDePago());
		pago.setMonto(pago.getMonto());
		
		Factura aux1 = new Factura();	
		FacturaEntity factura = PagoRecuperado.getFactura();
		
		aux1.add(FacturaDAO.getInstancia().toNegocio(factura));
		pago.setFactura(aux1);
		return pago;
	
	}
	



}
