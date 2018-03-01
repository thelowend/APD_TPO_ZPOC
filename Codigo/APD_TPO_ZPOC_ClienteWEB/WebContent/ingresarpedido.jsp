<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="edu.uade.apdzpoc.dto.ArticuloDTO"%>
<%@ page import="edu.uade.apdzpoc.dto.ClienteDTO"%>
<%@ page import="java.util.Date"%>



<article class="container grid col-12 col-md-10 mt-2">
	<h1>Ingresar Pedido</h1>

	<section class="row col-12 seleccionar-articulos mt-4 mb-4">
		<table class="table table-striped table-light text-dark">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Art&iacute;culo</th>
					<th scope="col">Descripci&oacuten</th>
					<th scope="col">Precio Venta</th>
					<th scope="col">Tama&ntilde;o</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<ArticuloDTO> articulos = (List<ArticuloDTO>) request.getAttribute("articulos");
					List<ClienteDTO> clientes = (List<ClienteDTO>) request.getAttribute("clientes");	
					ArticuloDTO aux;
					String codigoBarra;

					for (Iterator<ArticuloDTO> i = articulos.iterator(); i.hasNext();) {
						aux = i.next();
						codigoBarra = String.valueOf(aux.getCodigoBarra());
				%>
				<tr>
					<th scope="row"><%=codigoBarra%></th>
					<td><%=aux.getNombreArticulo()%></td>
					<td><%=aux.getDescripcion()%></td>
					<td>$<%=aux.getPrecioVenta()%></td>
					<td><%=aux.getTamanio()%></td>
					
					<td><button class="btn btn-sm btn-warning btn-anadir"
							data-articulo='{ "codigoBarra": "<%=codigoBarra%>", "nombre": "<%= aux.getNombreArticulo() %>", "desc": "<%=aux.getDescripcion()%>", "cant": 1 }'><i class="fas fa-cart-plus"></i> Agregar</button>
				</tr>
				<% } %>

				</tr>
			</tbody>
		</table>
	</section>
	<section class="row col-12 carrito-pedido p-4 bg-dark text-light">
		<div class="carro-left col-12 col-sm-6">
			<div class="form-group">
				<label for="cliente">Cliente</label> 
					<select class="form-control custom-select needs-validation" id="cliente">
						<option selected>Seleccionar Cliente</option>
					<% ClienteDTO auxC;
						for (Iterator<ClienteDTO> i = clientes.iterator(); i.hasNext();) {
						auxC = i.next();
					
					%>
						<option value='{ "id": <%= auxC.getIdCliente() %>, "documento": <%= auxC.getDocumento() %>, "nombre": "<%= auxC.getNombre() %>"}'><%= auxC.getNombre() %></option>
					<% } %>
					</select>
					<div class="invalid-feedback">Debe seleccionar un cliente</div>
			</div>
			<div class="form-group">
				<label for="direccion">Direcci&oacute;n de Entrega:</label> <input
					type="text" class="form-control needs-validation" id="direccion"
					placeholder="(Ej: Av. Pueyrredón 1472, 2do &quot;A&quot;)" name="direccion">
				<div class="invalid-feedback">Ingrese una dirección válida</div>
			</div>
		</div>
		<div class="carro-right col-12 col-sm-6 bg-light text-dark p-2">
			<div class="carrito-articulos container-fluid" data-articulos='[]'>
			</div>
		</div>
		<div class="carro-pedir col-12">
			<button id="hacerPedido" type="button" class="btn btn-light">Hacer Pedido</button>
		</div>
	</section>
</article>
<div class="modal fade" id="submitPedidoModal" tabindex="-1" role="dialog" aria-labelledby="submitPedidoModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content text-dark">
      <div class="modal-header">
        <h5 class="modal-title" id="submitPedidoModalLabel">¿Confirmar Pedido?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-dark" id="submitPedido">Confirmar Pedido</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/ingresarpedido.js"></script>