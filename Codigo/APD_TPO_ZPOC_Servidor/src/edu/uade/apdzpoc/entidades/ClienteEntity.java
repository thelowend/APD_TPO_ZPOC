package edu.uade.apdzpoc.entidades;

import javax.persistence.*;

import edu.uade.apdzpoc.negocio.CuentaCorriente;


/**
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
@Entity
@Table(name = "Cliente")
public class ClienteEntity {
	@Id
	@Column (name="IdCliente")
	private Integer idCliente;
	
	@Column (name="Nombre")
	private String nombre;
	
	@Column (name="Documento")
	private Integer documento;
	
	@Column (name="Domicilio_Facturacion")
	private String domicilioFacturacion;
	
	@Column (name="Responsable_Inscripto")
	private Boolean responsableInscripto;
	
	@Column(name="IVA_Inscripto")
	private Boolean ivaInscripto;

	@Column (name="Descuento")
	private Float descuento;

	@OneToOne
	@JoinColumn(name="IdCliente")
	private CuentaCorrienteEntity cuentaCorriente;
	
	public ClienteEntity(){}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public String getDomicilioFacturacion() {
		return domicilioFacturacion;
	}

	public void setDomicilioFacturacion(String domicilioFacturacion) {
		this.domicilioFacturacion = domicilioFacturacion;
	}

	public Boolean getResponsableInscripto() {
		return responsableInscripto;
	}

	public void setResponsableInscripto(Boolean responsableInscripto) {
		this.responsableInscripto = responsableInscripto;
	}

	public Boolean getIvaInscripto() {
		return ivaInscripto;
	}

	public void setIvaInscripto(Boolean ivaInscripto) {
		this.ivaInscripto = ivaInscripto;
	}

	public Float getDescuento() {
		return descuento;
	}

	public void setDescuento(Float descuento) {
		this.descuento = descuento;
	}

	public CuentaCorrienteEntity getCuentaCorriente() {
		return cuentaCorriente;
	}

	public void setCuentaCorriente(CuentaCorrienteEntity cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}
	
	
	
	

}
