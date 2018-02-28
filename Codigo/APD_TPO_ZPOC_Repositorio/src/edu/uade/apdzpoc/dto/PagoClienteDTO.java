package edu.uade.apdzpoc.dto;

import java.io.Serializable;
import java.util.Date;

import edu.uade.apdzpoc.enums.MedioPago;


public class PagoClienteDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer IdPago;
	private MedioPago medioDePago;
	private Date fecha;
	private float monto;
	private FacturaDTO factura;
	
	
	public PagoClienteDTO(Integer idPago, MedioPago medioDePago, Date fecha, float monto, FacturaDTO factura) {
		super();
		IdPago = idPago;
		this.medioDePago = medioDePago;
		this.fecha = fecha;
		this.monto = monto;
		this.factura = factura;
	}
	public Integer getIdPago() {
		return IdPago;
	}
	public void setIdPago(Integer idPago) {
		IdPago = idPago;
	}
	public MedioPago getMedioDePago() {
		return medioDePago;
	}
	public void setMedioDePago(MedioPago medioDePago) {
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
	public FacturaDTO getFactura() {
		return factura;
	}
	public void setFactura(FacturaDTO factura) {
		this.factura = factura;
	}
	@Override
	public String toString() {
		return "PagoClienteDTO [IdPago=" + IdPago + ", medioDePago=" + medioDePago + ", fecha=" + fecha + ", monto="
				+ monto + "]";
	}
	
	


}
