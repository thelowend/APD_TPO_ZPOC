package edu.uade.apdzpoc.entidades;

import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name="Orden_Compra")
public class OrdenCompraEntity {

	@Id
	@Column(name="Nro_OC")
	private Integer idOC;
	
	@ManyToOne
	@JoinColumn(name="Id_Proveedor")
	private ProveedorEntity proveedor;
	
	@Column
	private Integer cantidad;
	
	@Column(name="IdEstadoOC")
	private Integer estado;
	
	@OneToOne
	@JoinColumn(name="Codigo_Barra")
	private ArticuloEntity articulo;
	
	@OneToOne
	@JoinColumn(name="Nro_Lote")
	private LoteEntity lote;
	
	@OneToOne
	@JoinColumn(name="Id_Pedido")
	private PedidoWebEntity pedidoW;
	
	@OneToOne
	@JoinColumn
	private Date fecha;
	
	
	public OrdenCompraEntity() {
		
	}


	public Integer getIdOC() {
		return idOC;
	}


	public void setIdOC(Integer idOC) {
		this.idOC = idOC;
	}


	public ProveedorEntity getProveedor() {
		return proveedor;
	}


	public void setProveedor(ProveedorEntity proveedor) {
		this.proveedor = proveedor;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Integer getEstado() {
		return estado;
	}


	public void setEstado(Integer estado) {
		this.estado = estado;
	}


	public ArticuloEntity getArticulo() {
		return articulo;
	}


	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}


	public LoteEntity getLote() {
		return lote;
	}


	public void setLote(LoteEntity lote) {
		this.lote = lote;
	}


	public PedidoWebEntity getPedidoW() {
		return pedidoW;
	}


	public void setPedidoW(PedidoWebEntity pedidoW) {
		this.pedidoW = pedidoW;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	
	
}
