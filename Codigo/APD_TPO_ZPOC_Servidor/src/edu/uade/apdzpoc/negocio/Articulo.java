/**
 *
 *
 * TPO: APDZPOC
 *
 * GRUPO 08
 * Integrantes:
 * 	LU:0119404	- Zapatero, Barbara Daniela
 * 	LU:1022185	- Pablos, Diego Maximiliano
 * 	LU:0133009	- Ojeda, Maria De Los Angeles
 *  LU:0127304	- Cavallaro, Cristian Alberto
 *
 *
 */

package edu.uade.apdzpoc.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.dao.ArticuloDAO;
import edu.uade.apdzpoc.dao.MovimientoAjusteDAO;
import edu.uade.apdzpoc.dao.MovimientoCompraDAO;
import edu.uade.apdzpoc.dao.MovimientoPedidoDAO;
import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.DestinoArticulos;

public class Articulo {

	private Integer codigoBarra;
	private String nombreArticulo;
	private String descripcion;
	private float precioVenta;
	private int cantidadCompra;
	private String presentacion;
	private String tamanio;

	private int stockFisico;
	private int stockVirtual;
	private int stockDisponible;
	private int stockPendienteEntrega;

	private List<Lote> lote;

	public Articulo(String nombreArticulo, String descripcion, float precioVenta, int cantidadCompra,
			String presentacion, String tamanio) {
		this.nombreArticulo = nombreArticulo;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.cantidadCompra = cantidadCompra;
		this.presentacion = presentacion;
		this.tamanio = tamanio;
		this.lote = new ArrayList<>();
	}

	public Articulo() {
	}

	public Integer getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(Integer codigoBarra) {
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

	public List<Lote> getLote() {
		return lote;
	}

	public void setLote(List<Lote> lote) {
		this.lote = lote;
	}

	public MovimientoPedido crearMovimientoPedido(int cantidad, PedidoWeb pw) {
		MovimientoPedido mp = new MovimientoPedido(pw.getFechaGeneracion(), this, cantidad, pw);
		MovimientoPedidoDAO.getInstancia().save(mp);
		return mp;
	}

	public MovimientoCompra crearMovimientoCompra(int cantidad, Date fechaGeneracion) {
		MovimientoCompra mc = new MovimientoCompra(fechaGeneracion, this, cantidad);
		MovimientoCompraDAO.getInstancia().save(mc);
		return mc;
	}

	public MovimientoCompra crearMovimientoCompra(OrdenCompra oc) {
		MovimientoCompra mc = new MovimientoCompra(new Date(), oc.getArticulo(), oc.getCantidad(), oc, oc.getLote());
		MovimientoCompraDAO.getInstancia().save(mc);
		return mc;
	}

	public void crearMovimientoAjuste(int cantidad, Date fechaGeneracion, CausaAjuste causa, int legajoOperador, int legajoAutorizante, DestinoArticulos destino, Lote lote) {
		MovimientoAjuste ma = new MovimientoAjuste(fechaGeneracion, this, cantidad, causa, legajoOperador, legajoAutorizante, destino, lote);
		MovimientoAjusteDAO.getInstancia().save(ma);
	}

	public void save() {
		ArticuloDAO.getInstancia().save(this);
	}





}
