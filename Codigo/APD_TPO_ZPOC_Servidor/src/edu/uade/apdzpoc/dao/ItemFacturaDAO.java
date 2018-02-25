	
	package edu.uade.apdzpoc.dao;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;

	import edu.uade.apdzpoc.entidades.ArticuloEntity;
	import edu.uade.apdzpoc.entidades.ItemFacturaEntity;
	import edu.uade.apdzpoc.hbt.HibernateUtil;
	import edu.uade.apdzpoc.negocio.Articulo;

	import edu.uade.apdzpoc.negocio.ItemFactura;

	public class ItemFacturaDAO {

		private static ItemFacturaDAO instancia;
		
		private ItemFacturaDAO() {}
		
		public static ItemFacturaDAO getInstancia(){
			if(instancia == null)
				instancia = new ItemFacturaDAO();
			return instancia;
		}
		
		public void save(ItemFactura item) {
			
			ItemFacturaEntity ItemAPersistir = this.toEntity(item);
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			session.saveOrUpdate(ItemAPersistir);
			session.flush();
			session.getTransaction().commit();
			session.close();
			
		}
		
	//ItemAPersistir = Corresponde a una Item de Negocio que voy a guardar en bd	
		
	public ItemFacturaEntity toEntity(ItemFactura item) {
			
			ItemFacturaEntity ItemAPersistir = new ItemFacturaEntity();
			ItemAPersistir.setCantidad(item.getCantidad());
			ItemAPersistir.setPrecio(item.getPrecio());
			
			ArticuloEntity aux1 = ArticuloDAO.getInstancia().toEntity(item.getArticulo());
			ItemAPersistir.setArticulo(aux1);
			
			return ItemAPersistir;	
			
		}
		
	//----------
	//ItemAPersistir = Corresponde a una Item de Negocio que voy traer de la bd	
	public ItemFactura toNegocio(ItemFacturaEntity item) {
		
		ItemFactura itemFactura = new ItemFactura();
		itemFactura.setCantidad(item.getCantidad());
		itemFactura.setPrecio(item.getPrecio());
		
		Articulo aux1 = ArticuloDAO.getInstancia().toNegocio(item.getArticulo());
		itemFactura.setArticulo(aux1);
		
		return itemFactura;
			
	}

		
		
		
	

}
