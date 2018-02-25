/**
 * 
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
 
package edu.uade.apdzpoc.negocio;

public class CuentaCorriente {

	private int idCtaCorriente;
	private float saldo;
	private float limMax;
	public CuentaCorriente(float limMax) {
		this.limMax = limMax;
	}
	public int getIdCtaCorriente() {
		return idCtaCorriente;
	}
	public void setIdCtaCorriente(int idCtaCorriente) {
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
	
	
	
}
