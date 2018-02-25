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

import java.util.Date;
import java.util.List;

public class Factura {
	
	private int idFactura;
	private Cliente cliente;
	private Date fechaEmision;
	private Date fechaVencimiento;
	private String tipoFactura;
	private List<ItemFactura> itemsFactura;
	private float totalFactura;
	
	
	
	
	
	public Factura(Cliente cliente, Date fechaEmision, Date fechaVencimiento, String tipoFactura) {
		super();
		this.setCliente(cliente);
		this.fechaEmision = fechaEmision;
		this.fechaVencimiento = fechaVencimiento;
		this.tipoFactura = tipoFactura;
	}

	
	




	public Factura(Date fechaEmision, Date fechaVencimiento, String tipoFactura, List<ItemFactura> itemsFactura,
			float totalFactura) {
		super();
		this.fechaEmision = fechaEmision;
		this.fechaVencimiento = fechaVencimiento;
		this.tipoFactura = tipoFactura;
		this.itemsFactura = itemsFactura;
		this.totalFactura = totalFactura;
	}







	public Factura() {
		// TODO Auto-generated constructor stub
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







	public float calcularMontoTotal(PedidoWeb pw){
		return 0f;
	}







	public Cliente getCliente() {
		return cliente;
	}







	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	

}
