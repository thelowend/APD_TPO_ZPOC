package edu.uade.apdzpoc.dto;

import java.io.Serializable;
import java.util.Date;

import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.DestinoArticulos;


public class MovimientoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idMovimiento;
	private Date fecha;
	private ArticuloDTO articulo;
	private int cantidad;
	private String estado;
	
	private OrdenCompraDTO oc;
	private PedidoWebDTO pw;
	private LoteDTO lote;
	private int legajoOperador;
	private int legajoAutorizante;
	private DestinoArticulos destino;
	private CausaAjuste causa;
	
	
	
	
	
	public MovimientoDTO(Integer idMovimiento, Date fecha, ArticuloDTO articulo, int cantidad) {
		super();
		this.idMovimiento = idMovimiento;
		this.fecha = fecha;
		this.articulo = articulo;
		this.cantidad = cantidad;
	}
	
	
	
	public MovimientoDTO(Integer idMovimiento, Date fecha, ArticuloDTO articulo, int cantidad, OrdenCompraDTO oc,
			LoteDTO lote) {
		super();
		this.idMovimiento = idMovimiento;
		this.fecha = fecha;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.oc = oc;
		this.lote = lote;
	}
	
	



	public MovimientoDTO(Integer idMovimiento, Date fecha, ArticuloDTO articulo, int cantidad, int legajoOperador,
			int legajoAutorizante, DestinoArticulos destino, CausaAjuste causa) {
		super();
		this.idMovimiento = idMovimiento;
		this.fecha = fecha;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.legajoOperador = legajoOperador;
		this.legajoAutorizante = legajoAutorizante;
		this.destino = destino;
		this.causa = causa;
	}



	public Integer getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public ArticuloDTO getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
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
	
	
	public OrdenCompraDTO getOc() {
		return oc;
	}
	public void setOc(OrdenCompraDTO oc) {
		this.oc = oc;
	}
	public PedidoWebDTO getPw() {
		return pw;
	}
	public void setPw(PedidoWebDTO pw) {
		this.pw = pw;
	}
	public LoteDTO getLote() {
		return lote;
	}
	public void setLote(LoteDTO lote) {
		this.lote = lote;
	}
	public int getLegajoOperador() {
		return legajoOperador;
	}
	public void setLegajoOperador(int legajoOperador) {
		this.legajoOperador = legajoOperador;
	}
	public int getLegajoAutorizante() {
		return legajoAutorizante;
	}
	public void setLegajoAutorizante(int legajoAutorizante) {
		this.legajoAutorizante = legajoAutorizante;
	}
	public DestinoArticulos getDestino() {
		return destino;
	}
	public void setDestino(DestinoArticulos destino) {
		this.destino = destino;
	}
	public CausaAjuste getCausa() {
		return causa;
	}
	public void setCausa(CausaAjuste causa) {
		this.causa = causa;
	}

	public String toStringMovimientoPedido() {
		return "MovimientoDTO [idMovimiento=" + idMovimiento + ", fecha=" + fecha + ", articulo=" + articulo
				+ ", cantidad=" + cantidad + ", pw=" + pw + "]";
	}

	public String toStringMovimientoOrdenCompra() {
		return "MovimientoDTO [idMovimiento=" + idMovimiento + ", fecha=" + fecha + ", articulo=" + articulo
				+ ", cantidad=" + cantidad + ", oc=" + oc + ", lote=" + lote + "]";
	}
	public String toStringMovimientoAjuste() {
		return "MovimientoDTO [idMovimiento=" + idMovimiento + ", fecha=" + fecha + ", articulo=" + articulo
				+ ", cantidad=" + cantidad + ", legajoOperador=" + legajoOperador + ", legajoAutorizante="
				+ legajoAutorizante + ", destino=" + destino + ", causa=" + causa + "]";
	}
	
	
	
	
	
	
	
}
