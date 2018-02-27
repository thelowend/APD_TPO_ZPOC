package edu.uade.apdzpoc.entidades;

import javax.persistence.*;


@Entity
@Table(name="ItemsFactura")
public class ItemFacturaEntity {
	@Id
	@Column(name="IdItemFactura")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idItemFactura;
	
	@OneToOne
	@JoinColumn(name="CodigoBarra")
	private ArticuloEntity articulo;
	
	@Column(name="Cantidad")
	private int cantidad;
	
	@Column(name="Precio")
	private float precio;
	
	
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


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	
	
	
	
}
