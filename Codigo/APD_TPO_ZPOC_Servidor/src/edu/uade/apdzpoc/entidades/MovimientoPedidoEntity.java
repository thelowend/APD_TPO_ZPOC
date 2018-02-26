package edu.uade.apdzpoc.entidades;

import javax.persistence.*;


@Entity
@DiscriminatorValue("Por PedidoWeb")
public class MovimientoPedidoEntity extends MovimientoEntity{

	@OneToOne
	@JoinColumn(name="IdPedido")
	private PedidoWebEntity pw;
	
	
	
	public MovimientoPedidoEntity() {
		
	}



	public PedidoWebEntity getPw() {
		return pw;
	}



	public void setPw(PedidoWebEntity pw) {
		this.pw = pw;
	}
	
	

}
