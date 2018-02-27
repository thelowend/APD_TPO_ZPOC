package edu.uade.apdzpoc.negocio;

import edu.uade.apdzpoc.dao.RemitoTransporteDAO;

public class RemitoTransporte {

	private Integer idRemito;
	private String empresaTransporte;
	private PedidoWeb pedido;

	public RemitoTransporte(String empresaTransporte, PedidoWeb pedido) {
		this.empresaTransporte = empresaTransporte;
		this.pedido = pedido;
	}

	public RemitoTransporte() {
		// TODO Auto-generated constructor stub
	}

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

	public PedidoWeb getPedido() {
		return pedido;
	}

	public void setPedido(PedidoWeb pedido) {
		this.pedido = pedido;
	}

	public void save() {
		RemitoTransporteDAO.getInstancia().save(this);
	}

}
