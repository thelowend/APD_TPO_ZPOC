package edu.uade.apdzpoc.entidades;
import javax.persistence.*;

@Entity
@Table(name="CuentasCorriente")
public class CuentaCorrienteEntity {

	@Id
	@Column(name="IdCtaCte")
	private Integer idCtaCorriente;
	
/*	
	@OneToOne
	@JoinColumn(name="IdCliente")
	private ClienteEntity cliente;
*/	
	@Column(name="SaldoTotal")
	private float saldo;
	
	@Column(name="LimiteMaximo")
	private float limMax;
	
	
	public CuentaCorrienteEntity() {
	}


	public Integer getIdCtaCorriente() {
		return idCtaCorriente;
	}


	public void setIdCtaCorriente(Integer idCtaCorriente) {
		this.idCtaCorriente = idCtaCorriente;
	}
/*

	public ClienteEntity getCliente() {
		return cliente;
	}


	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
*/

	public float getSaldo() {
		return saldo;
	}


	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}


	public float getLimMax() {
		return limMax;
	}


	public void setLimMax(Float limMax) {
		this.limMax = limMax;
	}

	
	
	
}
