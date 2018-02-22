package edu.uade.apdzpoc.entidades;

import javax.persistence.*;


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
	//Si no voy a persistir el cliente, esto, va??)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="IdCliente")
	private int idCliente;
	
	@Column (name="Nombre")
	private String nombre;
	
	@Column (name="Documento")
	private int documento;
	
	@Column (name="Domicilio_Facturacion")
	private String domicilioFacturacion;
	
	@Column (name="Responsable_Inscripto")
	private boolean responsableInscripto;
	
	@Column(name="IVA_Inscripto")
	private boolean ivaInscripto;

	@Column (name="Descuento")
	private float descuento;
//este siempre tiene que estar, no?
	
	public ClienteEntity(){}

}
