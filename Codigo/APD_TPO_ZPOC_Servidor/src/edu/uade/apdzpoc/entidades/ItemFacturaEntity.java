package edu.uade.apdzpoc.entidades;

import javax.persistence.*;


@Entity
@Table(name="Item_Factura")
public class ItemFacturaEntity {
	@Id
	@Column(name="Id_ItemFactura")
	private Integer idItemFactura;
	
	@OneToOne
	@JoinColumn(name="Codigo_Barra")
	private ArticuloEntity articulo;
	
	@Column(name="Cantidad")
	private Integer cantidad;
	
	@Column(name="Precio")
	private Float precio;
	
	
	public ItemFacturaEntity() {
		
	}


	public Integer getIdItemFactura() {
		return idItemFactura;
	}


	public void setIdItemFactura(Integer idItemFactura) {
		this.idItemFactura = idItemFactura;
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


	public Float getPrecio() {
		return precio;
	}


	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	
	
	
	
}
