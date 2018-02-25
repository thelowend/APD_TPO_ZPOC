package edu.uade.apdzpoc.util;

import edu.uade.apdzpoc.dto.ArticuloDTO;
import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.dto.ItemPedidoDTO;
import edu.uade.apdzpoc.negocio.Articulo;
import edu.uade.apdzpoc.negocio.Cliente;
import edu.uade.apdzpoc.negocio.ItemPedido;
import edu.uade.apdzpoc.enums.EstadoItemPedido;

public class DTOMapper {
	private static DTOMapper instancia;

	public static DTOMapper getInstancia() {
		if (instancia == null)
			instancia = new DTOMapper();
		return instancia;
	}

	private DTOMapper() {}
	
	public Articulo get(ArticuloDTO artDTO) {
		// TODO: get articulo completo con la info de articuloDTO:
		// Articulo art = Almacen.getArticulo(artDTO.getCodigoBarra());
		// return art;
		return new Articulo();
	}
	
	public ItemPedido get(ItemPedidoDTO ipDTO) {
		Articulo articulo = this.get(ipDTO.getArticuloDTO());
		return new ItemPedido(articulo, ipDTO.getCantidad(), EstadoItemPedido.Con_Stock); // Acá podríamos poner una versión del constructor que no requiera el estado en la inicialización
	}
	
	public Cliente get(ClienteDTO cDTO) {
		// TODO: get cliente completo con la info de clienteDTO:
		// Cliente cli = Compras.getCliente(cDTO.getIdCliente());
		// return cli;
		return new Cliente();
	}
}
