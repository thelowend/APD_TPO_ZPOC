package edu.uade.apdzpoc.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.uade.apdzpoc.dto.ArticuloDTO;

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

public interface IArticuloRemote extends Remote, Serializable {
	public ArticuloDTO getArticulo(int idArticulo) throws RemoteException;
	
}
