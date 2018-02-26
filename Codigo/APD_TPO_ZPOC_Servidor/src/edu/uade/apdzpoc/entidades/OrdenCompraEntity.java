package edu.uade.apdzpoc.entidades;

import java.util.Date;
import javax.persistence.*;

import edu.uade.apdzpoc.enums.EstadoOC;


@Entity
@Table(name="OrdenesCompra")
public class OrdenCompraEntity {

	@Id
	@Column(name="NroOC")
	private Integer idOC;
	
	@ManyToOne
	@JoinColumn(name="IdProveedor")
	private ProveedorEntity proveedor;
	
	@Column (name="Cantidad")
	private int cantidad;
	
	@Column(name="EstadoOC")
	@Enumerated(EnumType.STRING)
	private EstadoOC estado;
	
	@OneToOne
	@JoinColumn(name="CodigoBarra")
	private ArticuloEntity articulo;
	
	@OneToOne
	@JoinColumn(name="NroLote")
	private LoteEntity lote;
	
	@OneToOne
	@JoinColumn(name="IdPedido")
	private PedidoWebEntity pedidoW;
	
	@Column(name="Fecha")
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


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public EstadoOC getEstado() {
		return estado;
	}


	public void setEstado(EstadoOC estado) {
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
