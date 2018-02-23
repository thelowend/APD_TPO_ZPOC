
package edu.uade.apdzpoc.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@Table (name="Articulo")
public class ArticuloEntity {

	@Id
	@Column(name="Codigo_Barra")
	private int codigoBarra;
	
	@Column(name="Nombre_Articulo")
	private String nombreArticulo;
	
	@Column(name="Descripcion")
	private String descripcion;
	
	@Column(name="Precio_Venta")
	private float precioVenta;
	
	@Column(name="Cantidad_Compra")
	private int cantidadCompra;
	
	@Column(name="Presentacion")
	private String presentacion;
	
	@Column(name="Tamano")
	private String tamanio;
	
	@Column(name="Stock_Fisico")
	private int stockFisico;
	
	@Column(name="Stock_Virtual")
	private int stockVirtual;
	
	@Column(name="Stock_Disponible")
	private int stockDisponible;
	
	@Column(name="Stock_PendienteEntrega")
	private int stockPendienteEntrega;
	
	@OneToMany
	@JoinColumn(name="Codigo_Barra")
	private List<LoteEntity> lotes;
	
	
	public ArticuloEntity() {
	
	}


	public int getCodigoBarra() {
		return codigoBarra;
	}


	public void setCodigoBarra(int codigoBarra) {
		this.codigoBarra = codigoBarra;
	}


	public String getNombreArticulo() {
		return nombreArticulo;
	}


	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public float getPrecioVenta() {
		return precioVenta;
	}


	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}


	public int getCantidadCompra() {
		return cantidadCompra;
	}


	public void setCantidadCompra(int cantidadCompra) {
		this.cantidadCompra = cantidadCompra;
	}


	public String getPresentacion() {
		return presentacion;
	}


	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}


	public String getTamanio() {
		return tamanio;
	}


	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}


	public int getStockFisico() {
		return stockFisico;
	}


	public void setStockFisico(int stockFisico) {
		this.stockFisico = stockFisico;
	}


	public int getStockVirtual() {
		return stockVirtual;
	}


	public void setStockVirtual(int stockVirtual) {
		this.stockVirtual = stockVirtual;
	}


	public int getStockDisponible() {
		return stockDisponible;
	}


	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}


	public int getStockPendienteEntrega() {
		return stockPendienteEntrega;
	}


	public void setStockPendienteEntrega(int stockPendienteEntrega) {
		this.stockPendienteEntrega = stockPendienteEntrega;
	}


	public List<LoteEntity> getLotes() {
		return lotes;
	}


	public void setLotes(List<LoteEntity> lotes) {
		this.lotes = lotes;
	}

}
