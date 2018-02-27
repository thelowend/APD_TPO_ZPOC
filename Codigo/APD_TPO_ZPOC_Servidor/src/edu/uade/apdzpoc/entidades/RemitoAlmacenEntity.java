package edu.uade.apdzpoc.entidades;

import java.util.List;

import javax.persistence.*;

import edu.uade.apdzpoc.enums.EstadoRemito;
import edu.uade.apdzpoc.enums.TipoRemitoAlmacen;

@Entity
@Table(name="RemitosAlmacen")
public class RemitoAlmacenEntity {

	@Id
	@Column(name="IdRemitoAlmacen")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRemito;

	@Column(name="Estado")
	@Enumerated(EnumType.STRING)
	private EstadoRemito estado;

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="IdRemitoAlmacen")
	private List<ItemRemitoAlmacenEntity> itemsRemito;

	@Column(name="TipoRemito")
	@Enumerated(EnumType.STRING)
	private TipoRemitoAlmacen tipo;

	@Column(name="Numero")
	private int numero;

	public int getNumero() {
		return numero;
	}


	public void setNumero(Integer numero) {
		this.numero = numero;
	}


	public RemitoAlmacenEntity() {

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
