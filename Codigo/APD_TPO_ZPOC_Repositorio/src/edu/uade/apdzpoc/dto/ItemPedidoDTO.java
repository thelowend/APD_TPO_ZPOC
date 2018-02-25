package edu.uade.apdzpoc.dto;

import java.io.Serializable;

public class ItemPedidoDTO implements Serializable{
	
	private static final long serialVersionUID = -416468356618777675L;
	private ArticuloDTO articuloDTO;
	private int cantidad;
	
	public ItemPedidoDTO (ArticuloDTO articuloDTO, int cantidad) {
		this.setArticuloDTO(articuloDTO);
		this.setCantidad(cantidad);
	}

	public ArticuloDTO getArticuloDTO() {
		return articuloDTO;
	}

	public void setArticuloDTO(ArticuloDTO articuloDTO) {
		this.articuloDTO = articuloDTO;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}