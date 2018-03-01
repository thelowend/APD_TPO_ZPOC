<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="edu.uade.apdzpoc.dto.PedidoWebDTO"%>
<%@ page import="java.util.Date"%>



<%-- 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>


Datos del PedidoWebDTO

	private Integer idPedido;
	private ClienteDTO cliente;
	private Date fechaGeneracion;
	private Date fechaDespacho;
	private Date fechaDeEntrega;
	private EstadoPedido estadoPedido;
	private String direccionPedido;
	private List<ItemPedidoDTO> items;



 --%>


<article class="container grid col-12 col-md-10 mt-2">
	<h1>PedidosWeb a Validar</h1>

	<section class="row col-12 seleccionar-articulos mt-4 mb-4">
		<table class="table table-striped table-light text-dark">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>					
					<th scope="col">Fecha Generacion</th>
					<th scope="col">Cliente</th>
					<th scope="col">Estado Pedido</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<PedidoWebDTO> pds = (List<PedidoWebDTO>) request.getAttribute("pedidos");
					PedidoWebDTO aux;
					String id;
					String fechagen;
					String clienteid;
					String estadoP;
					String pedidoJSONstr = "";
					
					for (Iterator<PedidoWebDTO> i = pds.iterator(); i.hasNext();) {
						aux = i.next();
						
						//id = aux.getIdPedido().toString();
						//fechagen = aux.getFechaGeneracion().toString();
						//clienteid = String.valueOf(aux.getCliente().getIdCliente());
						//pedidoJSONstr = aux.toJSONString();
				%>
				<tr class="pedido-row">
					<th scope="row"><%=aux%></th>
					<td><%=aux.getIdPedido()%></td>
					<td><%=aux.getFechaGeneracion().toString()%></td>
					<td><%=aux.getCliente().getNombre()%></td>
					<td><%=aux.getEstadoPedido().toString()%></td>
					<td><button class="btn btn-sm btn-warning btn-validar"
							data-pedido='<%=pedidoJSONstr%>'><i class="fas fa-check-square"></i> Validar</button>
				</tr>
				<% } %>
			</tbody>
		</table>
	</section>
</article>
<div class="modal fade" id="listarPedidosModal" tabindex="-1"
	role="dialog" aria-labelledby="listarPedidosModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="listarPedidosModalLabel">¿Validar PedidoWeb Ingresado?</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				(DATOS PEDIDO)
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-warning" data-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-dark" id="submitOrden">Validar
					Pedido</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/ListarPedidosParaValidar.js"></script>
