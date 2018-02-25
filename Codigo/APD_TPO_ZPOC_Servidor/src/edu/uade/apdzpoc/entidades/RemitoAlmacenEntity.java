package edu.uade.apdzpoc.entidades;

import java.util.List;

import javax.persistence.*;

import edu.uade.apdzpoc.enums.EstadoRemito;
import edu.uade.apdzpoc.enums.TipoRemitoAlmacen;

@Entity
@Table(name="Remito_Almacen")
public class RemitoAlmacenEntity {

	@Id
	@Column(name="Id_RemitoAlmacen")
	private int idRemito;
	
	@Column(name="Estado")
	@Enumerated(EnumType.STRING)
	private EstadoRemito estado;
	
	@OneToMany
	@JoinColumn(name="Id_ItemRemitoAlmacen")
	private List <ItemRemitoAlmacenEntity> itemsRemito;
	
	@Column(name="Tipo_Remito")
	@Enumerated(EnumType.STRING)
	private TipoRemitoAlmacen tipo;

	public RemitoAlmacenEntity() {
		
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


	public List<ItemRemitoAlmacenEntity> getItemsRemito() {
		return itemsRemito;
	}


	public void setItemsRemito(List<ItemRemitoAlmacenEntity> itemsRemito) {
		this.itemsRemito = itemsRemito;
	}


	public TipoRemitoAlmacen getTipo() {
		return tipo;
	}


	public void setTipo(TipoRemitoAlmacen tipo) {
		this.tipo = tipo;
	}


	
	
	

}
