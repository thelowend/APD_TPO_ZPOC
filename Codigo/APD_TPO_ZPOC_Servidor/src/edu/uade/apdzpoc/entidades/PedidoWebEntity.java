package edu.uade.apdzpoc.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Type;

import edu.uade.apdzpoc.enums.EstadoPedido;


@Entity
@Table(name="PedidosWEB")
public class PedidoWebEntity {

	@Id
	@Column(name="IdPedido")
	private Integer idPedido;
	
	@OneToOne
	@JoinColumn(name="IdCliente")
	private ClienteEntity cliente;
	
	@Column(name="FechaGeneracion")
	private Date fechaGeneracion;
	
	@Column(name="FechaDespacho")
	@Type(type="date")
	private Date fechaDespacho;
	
	@Column(name="FechaEntrega")
	@Type(type="date")
	private Date fechaDeEntrega;
	
	@Column(name="EstadoPedido")
	@Enumerated(EnumType.STRING)
	private EstadoPedido estadoPedido;
	
	@Column(name="DireccionEnvio")
	private String direccionPedido;
	
	@OneToMany
	@JoinColumn(name="IdPedido")
	private List<ItemPedidoEntity> items;
	
	
	
	public PedidoWebEntity() {
		
	}



	public Integer getIdPedido() {
		return idPedido;
	}



	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}



	public ClienteEntity getCliente() {
		return cliente;
	}



	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}



	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}



	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}



	public Date getFechaDespacho() {
		return fechaDespacho;
	}



	public void setFechaDespacho(Date fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
	}



	public Date getFechaDeEntrega() {
		return fechaDeEntrega;
	}



	public void setFechaDeEntrega(Date fechaDeEntrega) {
		this.fechaDeEntrega = fechaDeEntrega;
	}



	public EstadoPedido getEstadoPedido() {
		return estadoPedido;
	}



	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}



	public String getDireccionPedido() {
		return direccionPedido;
	}



	public void setDireccionPedido(String direccionPedido) {
		this.direccionPedido = direccionPedido;
	}



	public List<ItemPedidoEntity> getItems() {
		return items;
	}



	public void setItems(List<ItemPedidoEntity> items) {
		this.items = items;
	}
	
	
	
	

}
