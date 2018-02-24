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

}
