package edu.uade.apdzpoc.dto;

import java.io.Serializable;
import java.util.Date;




public class OrdenCompraDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer idOC;
	private ProveedorDTO proveedor;
	private int cantidad;
	private String estado;
	private ArticuloDTO articulo;
	private LoteDTO lote;
	private PedidoWebDTO pedidoW;
	private Date fecha;
	
	
	
	
	
	
	public OrdenCompraDTO(Integer idOC, ProveedorDTO proveedor, int cantidad, String estado, ArticuloDTO articulo,
			LoteDTO lote, PedidoWebDTO pedidoW, Date fecha) {
		super();
		this.idOC = idOC;
		this.proveedor = proveedor;
		this.cantidad = cantidad;
		this.estado = estado;
		this.articulo = articulo;
		this.lote = lote;
		this.pedidoW = pedidoW;
		this.fecha = fecha;
	}






	public Integer getIdOC() {
		return idOC;
	}






	public void setIdOC(Integer idOC) {
		this.idOC = idOC;
	}






	public ProveedorDTO getProveedor() {
		return proveedor;
	}






	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}






	public int getCantidad() {
		return cantidad;
	}






	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}






	public String getEstado() {
		return estado;
	}






	public void setEstado(String estado) {
		this.estado = estado;
	}






	public ArticuloDTO getArticulo() {
		return articulo;
	}






	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}






	public LoteDTO getLote() {
		return lote;
	}






	public void setLote(LoteDTO lote) {
		this.lote = lote;
	}






	public PedidoWebDTO getPedidoW() {
		return pedidoW;
	}






	public void setPedidoW(PedidoWebDTO pedidoW) {
		this.pedidoW = pedidoW;
	}






	public Date getFecha() {
		return fecha;
	}






	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}






	public String toString() {
		return "OrdenCompraDTO [idOC=" + idOC + ", cantidad=" + cantidad + ", articulo=" + articulo + ", pedidoW="
				+ pedidoW + ", fecha=" + fecha + "]";
	}
	
	
	
	

}
