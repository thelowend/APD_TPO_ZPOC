package edu.uade.apdzpoc.negocio;

public class RemitoTransporte {

	private int idRemito;
	private String empresaTransporte;
	private PedidoWeb pedido;
	
	public RemitoTransporte(String empresaTransporte, PedidoWeb pedido) {
		super();
		this.empresaTransporte = empresaTransporte;
		this.pedido = pedido;
	}

	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}

	public String getEmpresaTransporte() {
		return empresaTransporte;
	}

	public void setEmpresaTransporte(String empresaTransporte) {
		this.empresaTransporte = empresaTransporte;
	}

	public PedidoWeb getPedido() {
		return pedido;
	}

	public void setPedido(PedidoWeb pedido) {
		this.pedido = pedido;
	}

	
}
