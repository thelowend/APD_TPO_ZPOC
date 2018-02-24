package edu.uade.apdzpoc.entidades;


import javax.persistence.*;

import edu.uade.apdzpoc.enums.DestinoArticulos;


@Entity
@DiscriminatorValue("Por Inventario")
public class MovimientoAjusteEntity extends MovimientoEntity {

	@Column(name="Legajo_Operador")
	private Integer legajoOperador;
	
	@Column(name="Legajo_Autorizante")
	private Integer legajoAutorizante;
	
	@Column(name="Destino")
	@Enumerated(EnumType.STRING)
	private DestinoArticulos destino;
	
	@Column(name="SubTipo")
	private String subtipo;
	
	@OneToOne
	@JoinColumn(name="Nro_Lote")
	private LoteEntity lote;
	
	
	public MovimientoAjusteEntity() {
	
	}


	public Integer getLegajoOperador() {
		return legajoOperador;
	}


	public void setLegajoOperador(Integer legajoOperador) {
		this.legajoOperador = legajoOperador;
	}


	public Integer getLegajoAutorizante() {
		return legajoAutorizante;
	}


	public void setLegajoAutorizante(Integer legajoAutorizante) {
		this.legajoAutorizante = legajoAutorizante;
	}


	public DestinoArticulos getDestino() {
		return destino;
	}


	public void setDestino(DestinoArticulos destino) {
		this.destino = destino;
	}


	public String getSubtipo() {
		return subtipo;
	}


	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}


	public LoteEntity getLote() {
		return lote;
	}


	public void setLote(LoteEntity lote) {
		this.lote = lote;
	}
	
	

}
