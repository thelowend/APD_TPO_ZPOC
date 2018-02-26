package edu.uade.apdzpoc.entidades;

import javax.persistence.*;

@Entity
@Table(name="ItemsRemitoAlmacen")
public class ItemRemitoAlmacenEntity {
	@Id
	@Column(name="IdItemRemitoAlmacen")
	private Integer idItemRemitoAlmacen;
	
	@OneToOne
	@JoinColumn(name="CodigoBarra")
	private ArticuloEntity articulo;
	
	@Column(name="Cantidad")
	private int cantidad;
	
	@OneToOne
	@JoinColumn(name="IdUbicacion")
	private UbicacionEntity ubicacion;
	
	
	public ItemRemitoAlmacenEntity() {
		// TODO Auto-generated constructor stub
	}


	public Integer getIdItemRemitoAlmacen() {
		return idItemRemitoAlmacen;
	}


	public void setIdItemRemitoAlmacen(Integer idItemRemitoAlmacen) {
		this.idItemRemitoAlmacen = idItemRemitoAlmacen;
	}


	public ArticuloEntity getArticulo() {
		return articulo;
	}


	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public UbicacionEntity getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(UbicacionEntity ubicacion) {
		this.ubicacion = ubicacion;
	}

	
	
	
}
