package edu.uade.apdzpoc.entidades;

import javax.persistence.*;

import edu.uade.apdzpoc.enums.EstadoItemPedido;

@Entity
@Table(name="Item_Pedido")

public class ItemPedidoEntity {

	
	@Id
	@Column(name="Id_ItemPedido")
	private Integer idItemPedido;
	
	@OneToOne
	@JoinColumn(name="Codigo_Barra")
	private ArticuloEntity articulo;
	
	@Column(name="Cantidad")
	private Integer cantidad;
	
	@Column(name="Estado")
	@Enumerated(EnumType.STRING)
	private EstadoItemPedido estado;
	
	
	public ItemPedidoEntity() {
	}


	public Integer getIdItemPedido() {
		return idItemPedido;
	}


	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}


	public ArticuloEntity getArticulo() {
		return articulo;
	}


	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public EstadoItemPedido getEstado() {
		return estado;
	}


	public void setEstado(EstadoItemPedido estado) {
		this.estado = estado;
	}

	
	
}