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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.dao.FacturaDAO;
import edu.uade.apdzpoc.enums.EstadoFactura;

public class Factura {

	private int idFactura;
	private Cliente cliente;
	private Date fechaEmision;
	private Date fechaVencimiento;
	private String tipoFactura;
	private List<ItemFactura> itemsFactura;
	private float totalFactura;
	private EstadoFactura estado;

	public Factura(PedidoWeb pw) {
		this.cliente = pw.getCliente();
		this.fechaEmision = new Date();
		this.fechaVencimiento = Date.from(LocalDate.now().plusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant()); // 3 meses desde la fecha de creación
		this.tipoFactura = this.cliente.getTipoFactura();
		this.estado = EstadoFactura.Emitida;
		this.itemsFactura = this.generarItemsFactura(pw.getItems());
		this.totalFactura = this.calcularMontoTotal();
	}
	
	public Factura () {}
	
	private List<ItemFactura> generarItemsFactura(List<ItemPedido> items) {
		List<ItemFactura> itemsFactura = new ArrayList<>();
		for (ItemPedido item : items) {
			// Está bien guardar el calculartotal como campo de itemFactura al momento de crearlo, ya que en el futuro podría cambiar el precio del artículo, pero no debería cambiar el precio en la factura emitida.
			itemsFactura.add(new ItemFactura(item.getArticulo(), item.getCantidad(), item.calcularTotal()));
		}
		return itemsFactura;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public List<ItemFactura> getItemsFactura() {
		return itemsFactura;
	}

	public void setItemsFactura(List<ItemFactura> itemsFactura) {
		this.itemsFactura = itemsFactura;
	}

	public float getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(float totalFactura) {
		this.totalFactura = totalFactura;
	}

	private float calcularMontoTotal() {
		float montoTotal = 0f;
		for(ItemFactura item : this.itemsFactura) {
			montoTotal += item.getPrecio(); // porque precio ya tiene el cálculo hecho del costo del articulo x cantidad en el momento que se creó.
		}
		return montoTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public EstadoFactura getEstado() {
		return estado;
	}

	public void setEstado(EstadoFactura estado) {
		this.estado = estado;
	}
	public void save() {
		FacturaDAO.getInstancia().save(this);
	}

}
