/**
 * 
 * 
 * TPO: APDZPOC
 * 
 * GRUPO 08
 * Integrantes:
 * 	LU:0119404	- Zapatero, Barbara Daniela
 * 	LU:1022185	- Pablos, Diego Maximiliano
 * 	LU:0133009	- Ojeda, Maria De Los Angeles
 *  LU:0127304	- Cavallaro, Cristian Alberto
 *  
 *
 */
 
package edu.uade.apdzpoc.hbt;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	    private static final SessionFactory sessionFactory;
	   
	    static
	    {
	        try
	        {
	        	 AnnotationConfiguration config = new AnnotationConfiguration();
	         	 config.addAnnotatedClass(facturaEntity.class);
	         	   	            	
	             sessionFactory = config.buildSessionFactory();
	        }
	        catch (Throwable ex)
	        {
	        	//	quizas aca podemos hacer otro tipo de control por una excepcion especial.
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
	 
	    public static SessionFactory getSessionFactory()
	    {
	        return sessionFactory;
	    }
	}
	
	

