package edu.uade.apdzpoc.entidades;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import edu.uade.apdzpoc.enums.MedioPago;

import java.util.Date;

@Entity
@Table(name = "PagosCliente")
public class PagoClienteEntity {

	@Id
	@Column(name = "IdComprobante")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdPago;

	@Enumerated(EnumType.STRING)
	@Column(name="MedioPago")
	private MedioPago medioDePago;

	@Column(name = "FechaPago")
	@Type(type="date")
	private Date fecha;

	@Column(name = "Monto")
	private float monto;

	@OneToOne
	@JoinColumn(name = "NroFactura")
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
