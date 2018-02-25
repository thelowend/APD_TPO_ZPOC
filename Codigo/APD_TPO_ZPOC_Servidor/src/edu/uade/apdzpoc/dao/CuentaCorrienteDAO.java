package edu.uade.apdzpoc.dao;



import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.entidades.*;
import edu.uade.apdzpoc.hbt.HibernateUtil;
import edu.uade.apdzpoc.negocio.*;





public class CuentaCorrienteDAO {

	private static CuentaCorrienteDAO instancia;
	
	private CuentaCorrienteDAO() {}
	
	public static CuentaCorrienteDAO getInstancia(){
		if(instancia == null)
			instancia = new CuentaCorrienteDAO();
		return instancia;
	}	
	
	public CuentaCorriente findByCodigo(Integer idCtaCorriente){
		CuentaCorriente resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		CuentaCorrienteEntity aux = (CuentaCorrienteEntity) s.createQuery("from CuentaCorrienteEntity where idCtaCorriente = ?").setInteger(0, idCtaCorriente).uniqueResult();
		resultado = this.toNegocio(aux);
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	
	public CuentaCorriente findByCliente(Integer idCliente){
		CuentaCorriente resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		CuentaCorrienteEntity aux = (CuentaCorrienteEntity) s.createQuery("from CuentaCorrienteEntity ct join c.cliente cl where cl.idCliente = ?").setInteger(0, idCliente).uniqueResult();
		resultado = this.toNegocio(aux);
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	public void save(CuentaCorriente recuperada){
		CuentaCorrienteEntity cuentaCorrienteAPersistir = this.toEntity(recuperada);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(cuentaCorrienteAPersistir);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	
	public CuentaCorrienteEntity toEntity(CuentaCorriente cuentaCorrienteNegocio) {
		CuentaCorrienteEntity cuentaCorrienteAPersistir = new CuentaCorrienteEntity();
		cuentaCorrienteAPersistir.setIdCtaCorriente(cuentaCorrienteNegocio.getIdCtaCorriente());
		cuentaCorrienteAPersistir.setLimMax(cuentaCorrienteNegocio.getLimMax());
		cuentaCorrienteAPersistir.setSaldo(cuentaCorrienteNegocio.getSaldo());
		
		ClienteEntity clAux = ClienteDAO.getInstancia().toEntity(cuentaCorrienteNegocio.getCliente());
		cuentaCorrienteAPersistir.setCliente(clAux);
		
		return cuentaCorrienteAPersistir;
	}
	
	public CuentaCorriente toNegocio(CuentaCorrienteEntity cuentaCorrienteRecuperado) {
		CuentaCorriente cuentaCorrienteNegocio = new CuentaCorriente();
		cuentaCorrienteNegocio.setIdCtaCorriente(cuentaCorrienteRecuperado.getIdCtaCorriente());
		cuentaCorrienteNegocio.setLimMax(cuentaCorrienteRecuperado.getLimMax());
		cuentaCorrienteNegocio.setSaldo(cuentaCorrienteRecuperado.getSaldo());
		
		Cliente clAux = ClienteDAO.getInstancia().toNegocio(cuentaCorrienteRecuperado.getCliente());
		cuentaCorrienteNegocio.setCliente(clAux);
			
		return cuentaCorrienteNegocio;	
	}
	
	
	
	
	
}
