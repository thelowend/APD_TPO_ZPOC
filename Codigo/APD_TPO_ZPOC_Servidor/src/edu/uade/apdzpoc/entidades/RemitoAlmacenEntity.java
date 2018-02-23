package edu.uade.apdzpoc.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Remito_Almacen")
public class RemitoAlmacenEntity {

	@Id
	@Column(name="Id_RemitoAlmacen")
	private int idRemito;
	
	@Column(name="IdEstado")
	private int estado;
	
	@OneToMany
	@JoinColumn(name="Id_ItemRemitoAlmacen")
	private List <ItemRemitoAlmacenEntity> itemsRemito;
	
	
	public RemitoAlmacenEntity() {
		
	}


	public int getIdRemito() {
		return idRemito;
	}


	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}


	public List<ItemRemitoAlmacenEntity> getItemsRemito() {
		return itemsRemito;
	}


	public void setItemsRemito(List<ItemRemitoAlmacenEntity> itemsRemito) {
		this.itemsRemito = itemsRemito;
	}
	
	
	

}
