package edu.uade.apdzpoc.entidades;

import edu.uade.apdzpoc.negocio.PedidoWeb;
import javax.persistence.*;

@Entity
@Table (name="Remito_Trasporte")
public class RemitoTransporteEntity {
	
	
	@Id
	@Column(name="Id_RemitoT")
	private Integer idRemito;
	
	@Column(name="Empresa")
	private String empresaTransporte;
	
	@OneToOne
	@JoinColumn(name="Id_Pedido")
	private PedidoWebEntity pedido;
	
	
	
	public RemitoTransporteEntity() {}



	public Integer getIdRemito() {
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
