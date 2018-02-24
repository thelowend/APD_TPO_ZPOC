package edu.uade.apdzpoc.entidades;

import javax.persistence.*;


@Entity
@DiscriminatorValue("Por PedidoWeb")
public class MovimientoPedidoEntity {

	@OneToOne
	@JoinColumn(name="Nro_Pedido")
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
