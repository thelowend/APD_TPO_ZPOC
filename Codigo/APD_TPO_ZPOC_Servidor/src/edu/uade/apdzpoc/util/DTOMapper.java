package edu.uade.apdzpoc.util;

import edu.uade.apdzpoc.dao.ArticuloDAO;
import edu.uade.apdzpoc.dao.ClienteDAO;
import edu.uade.apdzpoc.dao.CuentaCorrienteDAO;
import edu.uade.apdzpoc.dao.LoteDAO;
import edu.uade.apdzpoc.dao.OrdenCompraDAO;
import edu.uade.apdzpoc.dao.RemitoAlmacenDAO;
import edu.uade.apdzpoc.dto.ArticuloDTO;
import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.dto.CuentaCorrienteDTO;
import edu.uade.apdzpoc.dto.ItemPedidoDTO;
import edu.uade.apdzpoc.dto.LoteDTO;
import edu.uade.apdzpoc.dto.OrdenCompraDTO;
import edu.uade.apdzpoc.dto.ProveedorDTO;
import edu.uade.apdzpoc.dto.RemitoAlmacenDTO;
import edu.uade.apdzpoc.negocio.Articulo;
import edu.uade.apdzpoc.negocio.Cliente;
import edu.uade.apdzpoc.negocio.CuentaCorriente;
import edu.uade.apdzpoc.negocio.ItemPedido;
import edu.uade.apdzpoc.negocio.Lote;
import edu.uade.apdzpoc.negocio.OrdenCompra;
import edu.uade.apdzpoc.negocio.Proveedor;
import edu.uade.apdzpoc.negocio.RemitoAlmacen;
import edu.uade.apdzpoc.enums.EstadoItemPedido;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ClienteException;
import edu.uade.apdzpoc.excepciones.LoteException;
import edu.uade.apdzpoc.excepciones.OrdenCompraException;
import edu.uade.apdzpoc.excepciones.RemitoAlmacenException;

public class DTOMapper {
	
	private static DTOMapper instancia;

	public static DTOMapper getInstancia() {
		if (instancia == null)
			instancia = new DTOMapper();
		return instancia;
	}

	private DTOMapper() {}
	
	
	// Cuando se busca en la base de datos es  porque en esta parte del negocio desarrollado no estamos 
	// ingresando nuevos articulos. si ese fuese el caso deberiamos crear la clase de negocio
	// e ir seteandole los atributos necesarios del DTO.
	
	public Articulo dtoArticuloToNegocio(ArticuloDTO artDTO) throws ArticuloException {
		
		Articulo art = Articulo.getArticulo(artDTO.getCodigoBarra());
		return art;
		}
	
	public Proveedor dtoProveedorToNegocio (ProveedorDTO provDTO){
		Proveedor proveedorNegocio = new edu.uade.apdzpoc.negocio.Proveedor();
		proveedorNegocio.setIdProveedor(provDTO.getIdProveedor());
		proveedorNegocio.setNombreProveedor(provDTO.getNombreProveedor());
		return proveedorNegocio;
		
	}

	public Cliente dtoClienteToNegocio(ClienteDTO clienteDTO) throws ClienteException {
		Cliente cliente = ClienteDAO.getInstancia().findByCodigo(clienteDTO.getIdCliente());
		return cliente;
		}

	public CuentaCorriente dtoCtaCteToNegocio(CuentaCorrienteDTO ctaCte) throws ClienteException{
		CuentaCorriente cc = CuentaCorrienteDAO.getInstancia().findByCliente(ctaCte.getIdCtaCorriente());
		return cc;
	}
	
	
	public ItemPedido dtoItemPedidoToNegocio(ItemPedidoDTO ipDTO) throws ArticuloException {
		Articulo articulo = this.dtoArticuloToNegocio(ipDTO.getArticuloDTO());
		return new ItemPedido(articulo, ipDTO.getCantidad(), EstadoItemPedido.Con_Stock); // Acá podríamos poner una versión del constructor que no requiera el estado en la inicialización
	}
	
	public Cliente get(ClienteDTO cDTO) throws ClienteException {
		 Cliente cli = ClienteDAO.getInstancia().findByCodigo(cDTO.getIdCliente());
		 return cli;
		}
	
	public OrdenCompra dtoOrdenCompraToNegocio (OrdenCompraDTO oc) throws OrdenCompraException{
		OrdenCompra ordenCompraNegocio = OrdenCompraDAO.getInstancia().findByCodigo(oc.getIdOC());
		return ordenCompraNegocio;
		
	}
	
	public Lote dtoLotetoNegocio (LoteDTO l) throws LoteException{
		Lote loteNegocio = LoteDAO.getInstancia().findByNro(l.getNroLote());
		
		////
		
		if (!loteNegocio )
		{
			loteNegocio.setNroLote(l.getNroLote());
			loteNegocio.setVencimiento(l.getVencimiento());
			Articulo aux = this.dtoArticuloToNegocio(l.getArticulo());
			loteNegocio.setArticulo(aux);
			
			
		}
		
		return loteNegocio;
	}
	
	public RemitoAlmacen dtoRemitoAlmacenToNegocio (RemitoAlmacenDTO ra) throws RemitoAlmacenException{
		return  RemitoAlmacenDAO.getInstancia().findByNro(ra.getIdRemito());
	}
	
	
	
}
