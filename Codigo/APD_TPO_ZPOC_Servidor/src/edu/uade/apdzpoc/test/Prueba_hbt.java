package edu.uade.apdzpoc.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.uade.apdzpoc.controlador.Controlador;
import edu.uade.apdzpoc.hbt.HibernateUtil;

public class Prueba_hbt {

	public static void main(String[] args) {
		

		// Aca es para poner las llamadas al Controlador
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		
		
	}


}
