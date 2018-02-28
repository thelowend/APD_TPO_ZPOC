<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="edu.uade.apdzpoc.dto.PedidoWebDTO"%>
<%@ page import="edu.uade.apdzpoc.dto.ClienteDTO"%>
<%@ page import="edu.uade.apdzpoc.enums.EstadoPedido"%>
<%@ page import="java.util.Date"%>

<article class="container grid col-12 col-md-10 mt-2">
	<h1>Despachar Pedidos:</h1>

	<section class="row col-12 seleccionar-articulos mt-4 mb-4">
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Cliente</th>
					<th scope="col">Fecha de Generaci&oacute;n</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<PedidoWebDTO> pedidos = (List<PedidoWebDTO>) request.getAttribute("pedidosPendientes");
					PedidoWebDTO aux;
					String id;
					String fechagen;
					String clienteid;
					String pedidoJSONstr;
					
					for (Iterator<PedidoWebDTO> i = pedidos.iterator(); i.hasNext();) {
						aux = i.next();
						id = aux.getIdPedido().toString();
						fechagen = aux.getFechaGeneracion().toString();
						clienteid = String.valueOf(aux.getCliente().getIdCliente());
						pedidoJSONstr = aux.toJSONString();
				%>
				<tr>
					<th scope="row"><%=aux.getIdPedido()%></th>
					<td><%=aux.getCliente().getNombre()%></td>
					<td><%=fechagen%></td>
					<td><button class="btn btn-sm btn-dark btn-despachar"
							data-pedido='<%=pedidoJSONstr%>'><i class="fas fa-cubes"></i> Despachar</button>
				</tr>
				<% } %>
			</tbody>
		</table>
	</section>
</article>
<div class="modal fade" id="dispatchPedidoModal" tabindex="-1"
	role="dialog" aria-labelledby="dispatchPedidoModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="dispatchPedidoModalLabel">¿Despachar
					Pedido?</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form novalidate>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="fechaentrega-addon">Entrega</span>
						</div>

						<input type="date" class="form-control needs-validation"
							placeholder="Fecha de Entrega" id="fechaentrega"
							aria-label="fechaentrega" aria-describedby="fechaentrega-addon">
						<div class="invalid-feedback">Seleccione una fecha de entrega
							v&aacute;lida.</div>
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<label class="input-group-text" for="empresaTransporteSelect">Transporte</label>
						</div>
						<select class="form-control custom-select needs-validation"
							id="empresaTransporteSelect">
							<option selected>Seleccionar Empresa de Transporte...</option>
							<option value="Correo Argentino">Correo Argentino</option>
							<option value="OCA">OCA</option>
							<option value="Yo en bici">Yo en bici</option>
						</select>
						<div class="invalid-feedback">Debe seleccionar una empresa
							de transporte</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-warning" data-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-dark" id="submitPedido">Confirmar
					Despacho</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/listarpedidos.js"></script>