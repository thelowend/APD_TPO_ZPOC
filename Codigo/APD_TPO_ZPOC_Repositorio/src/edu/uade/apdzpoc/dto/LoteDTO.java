package edu.uade.apdzpoc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class LoteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer nroLote;
	private Date vencimiento;
	private ArticuloDTO articulo;
	private List<UbicacionDTO> ubicaciones;
	
	public LoteDTO(Integer nroLote, Date vencimiento, ArticuloDTO articulo, List<UbicacionDTO> ubicaciones) {
		super();
		this.nroLote = nroLote;
		this.vencimiento = vencimiento;
		this.articulo = articulo;
		this.ubicaciones = ubicaciones;
	}
	public Integer getNroLote() {
		return nroLote;
	}
	public void setNroLote(Integer nroLote) {
		this.nroLote = nroLote;
	}
	public Date getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	public ArticuloDTO getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}
	public List<UbicacionDTO> getUbicaciones() {
		return ubicaciones;
	}
	public void setUbicaciones(List<UbicacionDTO> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	@Override
	public String toString() {
		return "LoteDTO [nroLote=" + nroLote + ", vencimiento=" + vencimiento + ", articulo=" + articulo + "]";
	}

	
	
	
	

}
