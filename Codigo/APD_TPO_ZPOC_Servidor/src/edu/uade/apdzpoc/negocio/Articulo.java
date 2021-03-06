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
import edu.uade.apdzpoc.dto.ArticuloDTO;
import edu.uade.apdzpoc.dto.ArticuloStockDTO;
import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.DestinoArticulos;
import edu.uade.apdzpoc.enums.EstadoUbicacion;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.ProveedorException;
import edu.uade.apdzpoc.util.DTOMapper;

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

	private List<Lote> lotes;

	public Articulo(String nombreArticulo, String descripcion, float precioVenta, int cantidadCompra,
			String presentacion, String tamanio) {
		this.nombreArticulo = nombreArticulo;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.cantidadCompra = cantidadCompra;
		this.presentacion = presentacion;
		this.tamanio = tamanio;
		this.lotes = new ArrayList<>();
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

	public List<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}

	public boolean tieneStock(int cantidadRequerida) {
		boolean result = false;
		if (this.getStockDisponible() == 0) {
			result = cantidadRequerida <= (this.getStockPendienteEntrega() + this.getStockVirtual()); // Sumo porque el
																										// stock virtual
																										// es un �ndice
																										// negativo.
		} else {
			result = this.getStockDisponible() > cantidadRequerida;
		}
		return result;
	}

	public List<Ubicacion> obtenerUbicacionesItemsALiberar(int cantidadRequerida) {

		List<Ubicacion> ubicacionesItemsALiberar = new ArrayList<>();
		List<Lote> lotesArticulo = this.getLotes(); // Vienen ordenados por vencimiento m�s cercano.
		int cantidadRestante = cantidadRequerida;

		for (Lote lote : lotesArticulo) {

			while (cantidadRestante > 0) {

				Ubicacion u = lote.getMejorUbicacion(); // Busco la que tenga menos items para liberarlas m�s r�pido
				cantidadRestante = u.actualizarUbicacion(cantidadRestante);
				ubicacionesItemsALiberar.add(u); // Guardo la ubicaci�n para el remito

				if (u.getEstado() == EstadoUbicacion.Libre) {
					lote.removeUbicacion(u); // La libero de su asociaci�n al lote del Art�culo
				}
			}
		}

		return ubicacionesItemsALiberar;
	}

	public List<PedidoWeb> traerPedidosPendientes() {
		return PedidoWeb.obtenerPedidosPendientesStock(this);
	}

	public MovimientoPedido crearMovimientoPedido(int cantidad, PedidoWeb pw) {
		MovimientoPedido mp = new MovimientoPedido(pw.getFechaGeneracion(), this, cantidad, pw);
		return mp.saveAndFetch();
	}
	
	public MovimientoCompra crearMovimientoCompraPendiente(OrdenCompra oc) {
		MovimientoCompra mc = new MovimientoCompra(new Date(), oc.getArticulo(), oc.getCantidad(), oc);
		return mc.saveAndFetch();
	}
	public MovimientoCompra crearMovimientoCompra(OrdenCompra oc) {
		MovimientoCompra mc = new MovimientoCompra(new Date(), oc.getArticulo(), oc.getCantidad(), oc, oc.getLote());
		return mc.saveAndFetch();
	}

	public MovimientoAjuste crearMovimientoAjuste(int cantidad, CausaAjuste causa, int legajoOperador, int legajoAutorizante, DestinoArticulos destino, Lote lote) {
		MovimientoAjuste ma = new MovimientoAjuste(new Date(), this, cantidad, causa, legajoOperador, legajoAutorizante,
				destino, lote);
		this.setStockFisico(stockFisico + cantidad);
		this.setStockDisponible(stockDisponible + cantidad);
		return ma.saveAndFetch();
	}

	public void save() {
		ArticuloDAO.getInstancia().save(this);
	}

	public Proveedor obtenerMejorProveedor() throws ArticuloProveedorException, ProveedorException {
		return ArticuloProveedor.getMejorProveedorPorArticulo(this);
	}

	public static Articulo getArticulo(int codigoBarra) throws ArticuloException{
		Articulo a = ArticuloDAO.getInstancia().findByCodigo(codigoBarra);
		return a;
		
	}
	
	public Articulo dtoArticuloToNegocio(ArticuloDTO artDTO) throws ArticuloException {
		
		return DTOMapper.getInstancia().dtoArticuloToNegocio(artDTO);
		}
	
	
	public static ArticuloDTO articuloToDTO (Articulo articulo){
		
		return  DTOMapper.getInstancia().articuloToDTO(articulo);
	}

	public static ArticuloStockDTO articuloStockToDTO (Articulo articulo){
		
		ArticuloStockDTO artDTO =  DTOMapper.getInstancia().articuloStockToDTO(articulo);
		
		return artDTO;
	}

	
	
	public static List<Articulo> getArticulos() {
		List <Articulo> articulos = ArticuloDAO.getInstancia().getAll();
		return articulos;
	}
	
	
}
