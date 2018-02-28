package edu.uade.apdzpoc.dto;

import java.util.List;

import edu.uade.apdzpoc.enums.EstadoRemito;
import edu.uade.apdzpoc.enums.TipoRemitoAlmacen;

public class RemitoAlmacenDTO {

	private Integer idRemito;
	private EstadoRemito estado;
	private List<ItemRemitoAlmacenDTO> itemsRemito;
	private TipoRemitoAlmacen tipo;
	private int nro;
	public RemitoAlmacenDTO(Integer idRemito, EstadoRemito estado, List<ItemRemitoAlmacenDTO> itemsRemito,
			TipoRemitoAlmacen tipo, int nro) {
		super();
		this.idRemito = idRemito;
		this.estado = estado;
		this.itemsRemito = itemsRemito;
		this.tipo = tipo;
		this.nro = nro;
	}
	public Integer getIdRemito() {
		return idRemito;
	}
	public void setIdRemito(Integer idRemito) {
		this.idRemito = idRemito;
	}
	public EstadoRemito getEstado() {
		return estado;
	}
	public void setEstado(EstadoRemito estado) {
		this.estado = estado;
	}
	public List<ItemRemitoAlmacenDTO> getItemsRemito() {
		return itemsRemito;
	}
	public void setItemsRemito(List<ItemRemitoAlmacenDTO> itemsRemito) {
		this.itemsRemito = itemsRemito;
	}
	public TipoRemitoAlmacen getTipo() {
		return tipo;
	}
	public void setTipo(TipoRemitoAlmacen tipo) {
		this.tipo = tipo;
	}
	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
		this.nro = nro;
	}
	@Override
	public String toString() {
		return "RemitoAlmacenDTO [idRemito=" + idRemito + ", itemsRemito=" + itemsRemito + ", nro=" + nro + "]";
	}

	
	
	
	
	
	
}
