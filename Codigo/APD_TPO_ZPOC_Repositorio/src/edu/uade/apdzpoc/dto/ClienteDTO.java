package edu.uade.apdzpoc.dto;
import java.io.Serializable;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -549331267945849902L;
	private int idCliente;
	private int documento;
	private String nombre;
	
	public ClienteDTO (int idCliente, int documento, String nombre) {
		this.setIdCliente(idCliente);
		this.setDocumento(documento);
		this.setNombre(nombre);
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}


