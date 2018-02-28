package edu.uade.apdzpoc.dto;

import java.io.Serializable;

public class CuentaCorrienteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idCtaCorriente;
	private float saldo;
	private float limMax;
	
	public CuentaCorrienteDTO(Integer idCtaCorriente, float saldo, float limMax) {
		super();
		this.idCtaCorriente = idCtaCorriente;
		this.saldo = saldo;
		this.limMax = limMax;
	}

	
	
	public Integer getIdCtaCorriente() {
		return idCtaCorriente;
	}



	public void setIdCtaCorriente(Integer idCtaCorriente) {
		this.idCtaCorriente = idCtaCorriente;
	}



	public float getSaldo() {
		return saldo;
	}



	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}



	public float getLimMax() {
		return limMax;
	}



	public void setLimMax(float limMax) {
		this.limMax = limMax;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "CuentaCorrienteDTO [idCtaCorriente=" + idCtaCorriente + ", saldo=" + saldo + ", limMax=" + limMax + "]";
	}
	
	
	
}
