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

import java.util.List;

import edu.uade.apdzpoc.enums.EstadoRemito;

public class RemitoAlmacen {

	private int idRemito;
	private EstadoRemito estado;
	private List <ItemRemitoAlmacen> itemsRemito;
	private String tipoDocumento;
	private int nroDocumento;


	
	
	public RemitoAlmacen(int idRemito, EstadoRemito estado, List<ItemRemitoAlmacen> itemsRemito, String tipoDocumento,
			int nroDocumento) {
		super();
		this.idRemito = idRemito;
		this.estado = estado;
		this.itemsRemito = itemsRemito;
		this.setTipoDocumento(tipoDocumento);
		this.setNroDocumento(nroDocumento);
	}
	public int getIdRemito() {
		return idRemito;
	}
	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}
	public EstadoRemito getEstado() {
		return estado;
	}
	public void setEstado(EstadoRemito estado) {
		this.estado = estado;
	}
	public List<ItemRemitoAlmacen> getItemsRemito() {
		return itemsRemito;
	}
	public void setItemsRemito(List<ItemRemitoAlmacen> itemsRemito) {
		this.itemsRemito = itemsRemito;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public int getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

}
