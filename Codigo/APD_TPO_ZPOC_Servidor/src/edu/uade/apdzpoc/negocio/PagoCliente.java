package edu.uade.apdzpoc.negocio;

import java.util.Date;

import edu.uade.apdzpoc.dao.FacturaDAO;
import edu.uade.apdzpoc.dao.PagoClienteDAO;
import edu.uade.apdzpoc.dto.PagoClienteDTO;
import edu.uade.apdzpoc.enums.MedioPago;
import edu.uade.apdzpoc.excepciones.FacturaException;
import edu.uade.apdzpoc.util.DTOMapper;

public class PagoCliente {

	private Integer IdPago;
	private MedioPago medioDePago;
	private Date fecha;
	private float monto;
	private Factura factura;

	public PagoCliente(MedioPago medioDePago, Date fecha, float monto, Factura factura) {
		this.medioDePago = medioDePago;
		this.fecha = fecha;
		this.monto = monto;
		this.factura = factura;
	}
	
	public PagoCliente() {}

	
	public PagoCliente(Integer idPago, MedioPago medioDePago, Date fecha, float monto, Factura factura) {
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

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public void save() {
		PagoClienteDAO.getInstancia().save(this);
	}
	
	public PagoCliente dtoPagoClienteToNegocio (PagoClienteDTO pago) throws FacturaException
	{
		return DTOMapper.getInstancia().dtoPagoClienteToNegocio(pago);	
	}
	
	
}
