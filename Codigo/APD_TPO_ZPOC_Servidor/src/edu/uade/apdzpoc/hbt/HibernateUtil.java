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

import edu.uade.apdzpoc.entidades.ArticuloEntity;
import edu.uade.apdzpoc.entidades.ArticuloProveedorEntity;
import edu.uade.apdzpoc.entidades.ArticuloProveedorIDEntity;
import edu.uade.apdzpoc.entidades.ClienteEntity;
import edu.uade.apdzpoc.entidades.CuentaCorrienteEntity;
import edu.uade.apdzpoc.entidades.FacturaEntity;
import edu.uade.apdzpoc.entidades.ItemFacturaEntity;
import edu.uade.apdzpoc.entidades.ItemPedidoEntity;
import edu.uade.apdzpoc.entidades.ItemRemitoAlmacenEntity;
import edu.uade.apdzpoc.entidades.LoteEntity;
import edu.uade.apdzpoc.entidades.MovimientoAjusteEntity;
import edu.uade.apdzpoc.entidades.MovimientoCompraEntity;
import edu.uade.apdzpoc.entidades.MovimientoEntity;
import edu.uade.apdzpoc.entidades.MovimientoPedidoEntity;
import edu.uade.apdzpoc.entidades.OrdenCompraEntity;
import edu.uade.apdzpoc.entidades.PagoClienteEntity;
import edu.uade.apdzpoc.entidades.PedidoWebEntity;
import edu.uade.apdzpoc.entidades.ProveedorEntity;
import edu.uade.apdzpoc.entidades.RemitoAlmacenEntity;
import edu.uade.apdzpoc.entidades.RemitoTransporteEntity;
import edu.uade.apdzpoc.entidades.UbicacionEntity;


public class HibernateUtil {

	    private static final SessionFactory sessionFactory;
	   
	    static
	    {
	        try
	        {
	        	 AnnotationConfiguration config = new AnnotationConfiguration();
	         	 config.addAnnotatedClass(ArticuloEntity.class);
	         	config.addAnnotatedClass(ArticuloProveedorEntity.class);
	         	config.addAnnotatedClass(ArticuloProveedorIDEntity.class);
	         	config.addAnnotatedClass(ClienteEntity.class);
	         	config.addAnnotatedClass(CuentaCorrienteEntity.class);
	         	config.addAnnotatedClass(FacturaEntity.class);
	         	config.addAnnotatedClass(ItemFacturaEntity.class);
	         	config.addAnnotatedClass(ItemPedidoEntity.class);
	         	config.addAnnotatedClass(ItemRemitoAlmacenEntity.class);
	         	config.addAnnotatedClass(LoteEntity.class);
	         	config.addAnnotatedClass(MovimientoEntity.class);
	         	config.addAnnotatedClass(MovimientoAjusteEntity.class);
	         	config.addAnnotatedClass(MovimientoCompraEntity.class);
	         	config.addAnnotatedClass(MovimientoPedidoEntity.class);
	         	config.addAnnotatedClass(OrdenCompraEntity.class);
	         	config.addAnnotatedClass(PagoClienteEntity.class);
	         	config.addAnnotatedClass(PedidoWebEntity.class);
	         	config.addAnnotatedClass(ProveedorEntity.class);
	         	config.addAnnotatedClass(RemitoAlmacenEntity.class);
	         	config.addAnnotatedClass(RemitoTransporteEntity.class);
	         	config.addAnnotatedClass(UbicacionEntity.class);
	         	
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
	
	

