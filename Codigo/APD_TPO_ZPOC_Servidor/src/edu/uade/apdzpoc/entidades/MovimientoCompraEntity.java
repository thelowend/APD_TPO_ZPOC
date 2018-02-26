package edu.uade.apdzpoc.entidades;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Por Orden de Compra")

public class MovimientoCompraEntity extends MovimientoEntity {

	@OneToOne
	@JoinColumn(name="NroOC")
	private OrdenCompraEntity oc;

	@OneToOne
	@JoinColumn(name="NroLote")
	private LoteEntity lote;
	
	
	public MovimientoCompraEntity() {
		
	}


	public OrdenCompraEntity getOc() {
		return oc;
	}


	public void setOc(OrdenCompraEntity oc) {
		this.oc = oc;
	}


	public LoteEntity getLote() {
		return lote;
	}


	public void setLote(LoteEntity lote) {
		this.lote = lote;
	}

	
	
	
	
}
