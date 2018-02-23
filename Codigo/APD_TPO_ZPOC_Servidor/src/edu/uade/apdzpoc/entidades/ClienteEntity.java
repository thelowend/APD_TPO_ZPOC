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
	private CuentaCorriente cuentaCorriente;
	
	public ClienteEntity(){}

}
