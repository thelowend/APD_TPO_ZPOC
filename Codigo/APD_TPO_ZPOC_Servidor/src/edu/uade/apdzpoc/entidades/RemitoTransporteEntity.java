package edu.uade.apdzpoc.entidades;

import javax.persistence.*;

@Entity
@Table (name="RemitosTrasporte")
public class RemitoTransporteEntity {
	
	
	@Id
	@Column(name="IdRemitoT")
	private Integer idRemito;
	
	@Column(name="Empresa")
	private String empresaTransporte;
	
	@OneToOne
	@JoinColumn(name="IdPedido")
	private PedidoWebEntity pedido;
	
	
	
	public RemitoTransporteEntity() {}



	public int getIdRemito() {
		return idRemito;
	}



	public void setIdRemito(Integer idRemito) {
		this.idRemito = idRemito;
	}



	public String getEmpresaTransporte() {
		return empresaTransporte;
	}



	public void setEmpresaTransporte(String empresaTransporte) {
		this.empresaTransporte = empresaTransporte;
	}



	public PedidoWebEntity getPedido() {
		return pedido;
	}



	public void setPedido(PedidoWebEntity pedido) {
		this.pedido = pedido;
	}

	
	
	
}
