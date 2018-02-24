package edu.uade.apdzpoc.entidades;

import javax.persistence.*;

import edu.uade.apdzpoc.enums.Medio_de_Pago;

@Entity
@Table(name="Pago_Cliente")
public class PagoClienteEntity {

	@Id
	@Column(name="IdComprobante")
	private Integer IdPago;
	
	@Enumerated(EnumType.STRING)
	private Medio_de_Pago medioDePago;
	
	@Column(name="Fecha_Pago")
	private java.sql.Date fecha;
	
	@Column(name="Monto")
	private Float monto;
	
	@OneToOne
	@JoinColumn(name="Nro_Factura")
	private FacturaEntity factura;
	
	
	
	public PagoClienteEntity() {
		// TODO Auto-generated constructor stub
	}



	public Integer getIdPago() {
		return IdPago;
	}



	public void setIdPago(Integer idPago) {
		IdPago = idPago;
	}



	public Medio_de_Pago getMedioDePago() {
		return medioDePago;
	}



	public void setMedioDePago(Medio_de_Pago medioDePago) {
		this.medioDePago = medioDePago;
	}



	public java.sql.Date getFecha() {
		return fecha;
	}



	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}



	public Float getMonto() {
		return monto;
	}



	public void setMonto(Float monto) {
		this.monto = monto;
	}



	public FacturaEntity getFactura() {
		return factura;
	}



	public void setFactura(FacturaEntity factura) {
		this.factura = factura;
	}

	
	
	
}
