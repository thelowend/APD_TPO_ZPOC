
package edu.uade.apdzpoc.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Articulos")
public class ArticuloEntity {

	@Id
	@Column(name="CodigoBarra")
	private Integer codigoBarra;

	@Column(name="NombreArticulo")
	private String nombreArticulo;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="PrecioVenta")
	private Float precioVenta;

	@Column(name="CantidadOC")
	private Integer cantidadCompra;

	@Column(name="Presentacion")
	private String presentacion;

	@Column(name="Tamano")
	private String tamanio;

	@Column(name="StockFisico")
	private Integer stockFisico;

	@Column(name="StockVirtual")
	private Integer stockVirtual;

	@Column(name="StockDisponible")
	private Integer stockDisponible;

	@Column(name="StockPendienteEntrega")
	private Integer stockPendienteEntrega;

	@OneToMany
	@JoinColumn(name="CodigoBarra")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoBarra == null) ? 0 : codigoBarra.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((nombreArticulo == null) ? 0 : nombreArticulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticuloEntity other = (ArticuloEntity) obj;
		if (codigoBarra == null) {
			if (other.codigoBarra != null)
				return false;
		} else if (!codigoBarra.equals(other.codigoBarra))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (nombreArticulo == null) {
			if (other.nombreArticulo != null)
				return false;
		} else if (!nombreArticulo.equals(other.nombreArticulo))
			return false;
		return true;
	}

}
