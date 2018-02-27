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

import edu.uade.apdzpoc.dao.PedidoWebDAO;
import edu.uade.apdzpoc.enums.EstadoPedido;

public class PedidoWeb {

	private Integer idPedido;
	private Cliente cliente;
	private Date fechaGeneracion;
	private Date fechaDespacho;
	private Date fechaDeEntrega;
	private EstadoPedido estadoPedido;
	private String direccionPedido;
	private List<ItemPedido> items;

	public PedidoWeb(Cliente cliente, EstadoPedido estadoPedido, String direccionPedido, List<ItemPedido> items) {
		this.cliente = cliente;
		this.estadoPedido = estadoPedido;
		this.direccionPedido = direccionPedido;
		this.items = items;
		this.fechaGeneracion = new Date();
	}

	public PedidoWeb() {
		this.items = new ArrayList<>();
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Date getFechaDespacho() {
		return fechaDespacho;
	}

	public void setFechaDespacho(Date fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
	}

	public Date getFechaDeEntrega() {
		return fechaDeEntrega;
	}

	public void setFechaDeEntrega(Date fechaDeEntrega) {
		this.fechaDeEntrega = fechaDeEntrega;
	}

	public EstadoPedido getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public String getDireccionPedido() {
		return direccionPedido;
	}

	public void setDireccionPedido(String direccionPedido) {
		this.direccionPedido = direccionPedido;
	}

	public List<ItemPedido> getItems() {
		return items;
	}

	public void setItems(List<ItemPedido> items) {
		this.items = items;
	}

	public void addItem(ItemPedido itemPedido){
		this.items.add(itemPedido);
	}

	public void save() {
		PedidoWebDAO.getInstancia().save(this);
	}
	
	public float calcularTotal() {
		float totalCompra = 0f;

		for (ItemPedido item : this.getItems()) {
			totalCompra += item.calcularTotal();
		}

		return totalCompra;
	}
	
	public boolean hayStockDeItems() {
		boolean hayStockDeTodosLosItems = true; // Si al iterar sucede que no hay stock de todos los items del pedido, quedar� en true.
		
		for (ItemPedido item : this.getItems()) {
			if(!item.hayStock()) {
				hayStockDeTodosLosItems = false;
			}
		}
		return hayStockDeTodosLosItems;
	}
	
	public static List<PedidoWeb> obtenerPedidosParaDespachar() {
		return PedidoWebDAO.getInstancia().findByEstado(EstadoPedido.Pendiente_Despacho);
	}

}
