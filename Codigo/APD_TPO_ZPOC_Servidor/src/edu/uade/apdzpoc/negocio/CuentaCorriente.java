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

import edu.uade.apdzpoc.dao.CuentaCorrienteDAO;

public class CuentaCorriente {

	private int idCtaCorriente;
	private float saldo;
	private float limMax;
	//private Cliente cliente;

/*	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}*/

	public CuentaCorriente(float limMax) {
		this.limMax = limMax;
	}

	public CuentaCorriente() {
		// TODO Auto-generated constructor stub
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

	public void save() {
		CuentaCorrienteDAO.getInstancia().save(this);
	}

}
