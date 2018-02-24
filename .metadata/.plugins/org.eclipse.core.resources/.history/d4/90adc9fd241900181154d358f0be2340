package edu.uade.apdzpoc.entidades;
import javax.persistence.*;

@Entity
@Table(name="Cuenta_Corriente")
public class CuentaCorrienteEntity {

	@Id
	@Column(name="IdCtaCte")
	private Integer idCtaCorriente;
	
	
	@OneToOne
	@JoinColumn(name="IdCtaCte")
	private ClienteEntity cliente;
	
	@Column(name="SaldoTotal")
	private Float saldo;
	
	@Column(name="Limite_Maximo")
	private Float limMax;
	
	
	public CuentaCorrienteEntity() {
	}


	public Integer getIdCtaCorriente() {
		return idCtaCorriente;
	}


	public void setIdCtaCorriente(Integer idCtaCorriente) {
		this.idCtaCorriente = idCtaCorriente;
	}


	public ClienteEntity getCliente() {
		return cliente;
	}


	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}


	public Float getSaldo() {
		return saldo;
	}


	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}


	public Float getLimMax() {
		return limMax;
	}


	public void setLimMax(Float limMax) {
		this.limMax = limMax;
	}

	
	
	
}
