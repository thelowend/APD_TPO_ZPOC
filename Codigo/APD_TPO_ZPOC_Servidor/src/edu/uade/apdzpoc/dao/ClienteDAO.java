package edu.uade.apdzpoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;



public class ClienteDAO {
	
	
	private static ClienteDAO instancia;
	
	private ClienteDAO() {}
	
	public static ClienteDAO getInstancia(){
		if(instancia == null)
			instancia = new ClienteDAO();
		return instancia;
	}	
	
	
	public Cliente findByCodigo(Integer idCliente){
		Cliente resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		ClienteEntity aux = (ClienteEntity) s.createQuery("from ClienteEntity cl join cl.cuentaCorriente ctaCte  where idCliente = ?").setInteger(0, idCliente).uniqueResult();
		resultado = this.toNegocio(aux);
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	
	public List<Cliente> getAll(){
		List<Cliente> resultado = new ArrayList<Cliente>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ClienteEntity> aux = (List<ClienteEntity>) s.createQuery("from ClienteEntity").list();
		for(ClienteEntity cl : aux)
		{
			resultado.add(this.toNegocio(cl));
		}
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	
	public void save(Cliente recuperado){
		ClienteEntity clienteAPersistir = this.toEntity(recuperado);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(clienteAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	
	public ClienteEntity toEntity(Cliente clienteNegocio) {
		ClienteEntity clienteAPersistir = new ClienteEntity();
		clienteAPersistir.setIdCliente(clienteNegocio.getIdCliente());
		clienteAPersistir.setNombre(clienteNegocio.getNombre());
		clienteAPersistir.setDocumento(clienteNegocio.getDocumento());
		clienteAPersistir.setDescuento(clienteNegocio.getDescuento());
		clienteAPersistir.setResponsableInscripto(clienteNegocio.isResponsableInscripto());
		clienteAPersistir.setIvaInscripto(clienteNegocio.isIvaInscripto());
		clienteAPersistir.setDomicilioFacturacion(clienteNegocio.getDomicilioFacturacion());
		
		CuentaCorrienteEntity ctaCteAux = CuentaCorrienteDAO.getInstancia().toEntity(clienteNegocio.getCuentaCorriente());
		clienteAPersistir.setCuentaCorriente(ctaCteAux);
	
		return clienteAPersistir;
	}
	
	public Cliente toNegocio(ClienteEntity clienteRecuperado) {
		Cliente clienteNegocio = new Cliente();
		clienteNegocio.setIdCliente(clienteRecuperado.getIdCliente());
		clienteNegocio.setNombre(clienteRecuperado.getNombre());
		clienteNegocio.setDocumento(clienteRecuperado.getDocumento());
		clienteNegocio.setDescuento(clienteRecuperado.getDescuento());
		clienteNegocio.setResponsableInscripto(clienteRecuperado.getResponsableInscripto());
		clienteNegocio.setIvaInscripto(clienteRecuperado.getIvaInscripto());
		clienteNegocio.setDomicilioFacturacion(clienteRecuperado.getDomicilioFacturacion());
		
		CuentaCorriente ctaCteAux = CuentaCorrienteDAO.getInstancia().toNegocio(clienteRecuperado.getCuentaCorriente());
		clienteNegocio.setCuentaCorriente(ctaCteAux);
		
		
		
		return clienteNegocio;	
	}
	
	
	
	

}
