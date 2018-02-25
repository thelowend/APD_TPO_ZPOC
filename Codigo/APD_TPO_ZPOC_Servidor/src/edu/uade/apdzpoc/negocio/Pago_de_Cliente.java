package edu.uade.apdzpoc.negocio;

import java.util.Date;

import edu.uade.apdzpoc.enums.Medio_de_Pago;

public class Pago_de_Cliente {

	private int IdPago;
	private Medio_de_Pago medioDePago;
	private Date fecha;
	private float monto;
	private Factura factura;
	
	
	
	public Pago_de_Cliente(int idPago, Medio_de_Pago medioDePago, Date fecha, float monto, Factura factura) {
		super();
		IdPago = idPago;
		this.medioDePago = medioDePago;
		this.fecha = fecha;
		this.monto = monto;
		this.factura = factura;
	}



	public Pago_de_Cliente() {
		// TODO Auto-generated constructor stub
	}



	public int getIdPago() {
		return IdPago;
	}



	public void setIdPago(int idPago) {
		IdPago = idPago;
	}



	public Medio_de_Pago getMedioDePago() {
		return medioDePago;
	}



	public void setMedioDePago(Medio_de_Pago medioDePago) {
		this.medioDePago = medioDePago;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public float getMonto() {
		return monto;
	}



	public void setMonto(float monto) {
		this.monto = monto;
	}



	public Factura getFactura() {
		return factura;
	}



	public void setFactura(Factura factura) {
		this.factura = factura;
	}



	

	
	
	
	
}
