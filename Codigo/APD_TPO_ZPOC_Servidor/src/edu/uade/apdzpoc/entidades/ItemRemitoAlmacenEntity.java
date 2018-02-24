package edu.uade.apdzpoc.entidades;

import javax.persistence.*;

@Entity
@Table(name="Item_RemitoAlmacen")
public class ItemRemitoAlmacenEntity {
	@Id
	@Column(name="Id_ItemRemitoAlmacen")
	private Integer idItemRemitoAlmacen;
	
	@OneToOne
	@JoinColumn(name="Codigo_Barra")
	private ArticuloEntity articulo;
	
	@Column(name="Cantidad")
	private Integer cantidad;
	
	@OneToOne
	@JoinColumn(name="Id_Ubicacion")
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


	public Integer getCantidad() {
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
