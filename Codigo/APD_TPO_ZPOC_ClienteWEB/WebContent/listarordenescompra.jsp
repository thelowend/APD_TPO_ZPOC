<article class="container grid col-12 col-md-10 mt-2">
	<h1>&Oacute;rdenes de Compra:</h1>

	<section class="row col-12 seleccionar-articulos mt-4 mb-4">
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Orden</th>
					<th scope="col">Descripci&oacuten</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<OrdenCompraDTO> ocs = (List<OrdenCompraDTO>) request.getAttribute("ordenesCompra");
					OrdenCompraDTO aux;
					String id;
					String fechagen;
					String clienteid;
					String ordenJSONstr;
					
					for (Iterator<OrdenCompraDTO> i = ocs.iterator(); i.hasNext();) {
						aux = i.next();
						
						//id = aux.getIdPedido().toString();
						//fechagen = aux.getFechaGeneracion().toString();
						//clienteid = String.valueOf(aux.getCliente().getIdCliente());
						//pedidoJSONstr = aux.toJSONString();
				%>
				<tr class="orden-row">
					<th scope="row"><%=aux%></th>
					<td><%=aux.getCliente().getNombre()%></td>
					<td><%=fechagen%></td>
					<td><button class="btn btn-sm btn-warning btn-validar"
							data-pedido='<%=ordenJSONstr%>'><i class="fas fa-cubes"></i> Validar</button>
				</tr>
				<% } %>
			</tbody>
		</table>
	</section>
</article>
<div class="modal fade" id="listarOrdenesModal" tabindex="-1"
	role="dialog" aria-labelledby="listarOrdenesModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="listarOrdenesModalLabel">¿Validar Orden de Compra?</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				(DATOS ORDEN)
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-warning" data-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-dark" id="submitOrden">Validar
					Orden</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/listarordenes.js"></script>