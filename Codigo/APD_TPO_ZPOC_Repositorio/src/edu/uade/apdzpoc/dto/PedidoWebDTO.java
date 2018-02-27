package edu.uade.apdzpoc.dto;

import java.util.Date;
import java.util.List;
import java.io.Serializable;



import edu.uade.apdzpoc.enums.EstadoPedido;
import edu.uade.apdzpoc.dto.*;



	public class PedidoWebDTO implements Serializable 
	{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Integer idPedido;
		private ClienteDTO cliente;
		private Date fechaGeneracion;
		private Date fechaDespacho;
		private Date fechaDeEntrega;
		private EstadoPedido estadoPedido;
		private String direccionPedido;
		private List<ItemPedidoDTO> items;
		
		
		public PedidoWebDTO(Integer idPedido, ClienteDTO cliente, Date fechaGeneracion, Date fechaDespacho,
				Date fechaDeEntrega, EstadoPedido estadoPedido, String direccionPedido, List<ItemPedidoDTO> items) {
			super();
			this.idPedido = idPedido;
			this.cliente = cliente;
			this.fechaGeneracion = fechaGeneracion;
			this.fechaDespacho = fechaDespacho;
			this.fechaDeEntrega = fechaDeEntrega;
			this.estadoPedido = estadoPedido;
			this.direccionPedido = direccionPedido;
			this.items = items;
		}


		public Integer getIdPedido() {
			return idPedido;
		}


		public void setIdPedido(Integer idPedido) {
			this.idPedido = idPedido;
		}


		public ClienteDTO getCliente() {
			return cliente;
		}


		public void setCliente(ClienteDTO cliente) {
			this.cliente = cliente;
		}


		public Date getFechaGeneracion() {
			return fechaGeneracion;
		}


		public void setFechaGeneracion(Date fechaGeneracion) {
			this.fechaGeneracion = fechaGeneracion;
		}


		public Date getFechaDespacho() {
			return fechaDespacho;
		}


		public void setFechaDespacho(Date fechaDespacho) {
			this.fechaDespacho = fechaDespacho;
		}


		public Date getFechaDeEntrega() {
			return fechaDeEntrega;
		}


		public void setFechaDeEntrega(Date fechaDeEntrega) {
			this.fechaDeEntrega = fechaDeEntrega;
		}


		public EstadoPedido getEstadoPedido() {
			return estadoPedido;
		}


		public void setEstadoPedido(EstadoPedido estadoPedido) {
			this.estadoPedido = estadoPedido;
		}


		public String getDireccionPedido() {
			return direccionPedido;
		}


		public void setDireccionPedido(String direccionPedido) {
			this.direccionPedido = direccionPedido;
		}


		public List<ItemPedidoDTO> getItems() {
			return items;
		}


		public void setItems(List<ItemPedidoDTO> items) {
			this.items = items;
		}


		@Override
		public String toString() {
			return "PedidoWebDT [idPedido=" + idPedido + ", cliente=" + cliente + ", fechaGeneracion=" + fechaGeneracion
					+ ", fechaDespacho=" + fechaDespacho + ", fechaDeEntrega=" + fechaDeEntrega + ", estadoPedido="
					+ estadoPedido + ", direccionPedido=" + direccionPedido + ", items=" + items + "]";
		}
		
	}


