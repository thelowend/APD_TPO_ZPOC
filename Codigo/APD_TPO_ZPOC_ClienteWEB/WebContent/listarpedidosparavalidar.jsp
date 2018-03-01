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
	<h1>Validar Pedidos</h1>

	<section class="row col-12 seleccionar-articulos mt-4 mb-4">
		<table class="table table-striped table-light text-dark">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Cliente</th>			
					<th scope="col">Fecha Generacion</th>
					<th scope="col">Estado Pedido</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<PedidoWebDTO> pds = (List<PedidoWebDTO>) request.getAttribute("pedidos");
					PedidoWebDTO aux;
					String pedidoJSONstr = "";
					
					for (Iterator<PedidoWebDTO> i = pds.iterator(); i.hasNext();) {
						aux = i.next();
						pedidoJSONstr = aux.toJSONString();
				%>
				<tr class="pedido-row">
					<th scope="row"><%=aux.getIdPedido()%></th>
					<td><%=aux.getCliente().getNombre()%></td>
					<td><%=aux.getFechaGeneracion().toString()%></td>
					<td><%=aux.getEstadoPedido().toString()%></td>
					<td><button class="btn btn-sm btn-warning btn-validar"
							data-pedido='<%=pedidoJSONstr%>'><i class="fas fa-check-square"></i> Validar</button>
				</tr>
				<% } %>
			</tbody>
		</table>
	</section>
</article>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/listarpedidosvalidacion.js"></script>
