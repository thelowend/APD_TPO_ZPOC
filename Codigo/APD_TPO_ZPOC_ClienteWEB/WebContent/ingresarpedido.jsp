<article class="container grid col-12 col-md-10 mt-2">
	<h1>Ingresar Pedido:</h1>

	<section class="row col-12 seleccionar-articulos mt-4 mb-4">
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Art&iacute;culo</th>
					<th scope="col">Descripci&oacuten</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<ArticuloDTO> articulos = (List<ArticuloDTO>) request.getAttribute("articulos");
					
					ArticuloDTO aux;
					
					String codigoBarra;
					String nombreArticulo;
					String descripcion;
					String precioVenta;
					String presentacion;
					String tamanio;
					
					
					for (Iterator<ArticuloDTO> i = articulos.iterator(); i.hasNext();) {
						aux = i.next();
						
						codigoBarra = String.valueOf(aux.getCodigoBarra());
						nombreArticulo = aux.getNombreArticulo();
						descripcion = aux.getDescripcion();
						precioVenta = String.valueOf(aux.getPrevioVenta());
						presentacion = aux.getPresentacion();
						tamanio = aux.getTamanio();
												
						
			%>
				<tr>
					<th scope="row"><%=aux.getCodigoBarra()%></th>
					<td><%=aux.getNombreArticulo()%></td>
					<td><%=aux.getDescripcion()%></td>
					<td><%=aux.getPrevioVenta()%></td>
					<td><%=aux.aux.getPresentacion()%></td>
					<td><%=aux.getTamanio()%></td>
					
					<td><button class="btn btn-sm btn-dark btn-anadir"
							data-articulo='{ "id": "<%=codigoBarra%>", "nombre": "<%= aux.getNombreArticulo() %>" }'><i class="fas fa-cubes"></i> Despachar</button>
				</tr>
				<% } %>
			
			
			
			
			
<!-- 				<tr>
					<th scope="row">1</th>
					<td>Pizza</td>
					<td>Pizza Vegana yummy</td>
					<td><button class="btn btn-sm btn-dark">Añadir</button></td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>Birra</td>
					<td>Bien fría por favor</td>
					<td><button class="btn btn-sm btn-dark">Añadir</button></td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td>Faso</td>
					<td>Guarda con éste</td>
					<td><button class="btn btn-sm btn-dark">Añadir</button></td> -->
				</tr>
			</tbody>
		</table>
	</section>
	<section class="row col-12 carrito-pedido p-4 bg-dark text-light">
		<div class="carro-left col-12 col-sm-6">
			<div class="form-group">
				<label for="cliente">Cliente</label> <input type="text"
					class="form-control" id="cliente"
					placeholder="Código de cliente" name="cliente">
			</div>
			<div class="form-group">
				<label for="direccion">Direcci&oacute;n de Entrega:</label> <input
					type="text" class="form-control" id="direccion"
					placeholder="(Ej: Av. Pueyrredón 1472, 2do &quot;A&quot;)" name="direccion">
			</div>
		</div>
		<div class="carro-right col-12 col-sm-6 bg-light text-dark p-2">
			<div class="carrito-articulos container-fluid" data-articulos='{}'>
				<div class="row no-gutters" data-articulo='{}'>
				  <div class="carrito-articulo-nombre col-8">Item 1</div>
				  <div class="carrito-articulo-trash col-1 text-center text-dark"><button class="btn btn-sm btn-light plus-art"><i class="fas fa-minus-square"></i></button></div>
				  <div class="carrito-articulo-cant col-1 text-center">1</div>
				  <div class="carrito-articulo-trash col-1 text-center text-dark"><button class="btn btn-sm btn-light min-art"><i class="fas fa-plus-square"></i></button></div>
				  <div class="carrito-articulo-trash col-1 text-center"><button class="btn btn-sm btn-dark remove-art"><i class="fas fa-trash"></i></button></div>
				</div>
			</div>
		</div>
		<div class="carro-pedir col-12">
			<button type="button" class="btn btn-light" data-toggle="modal" data-target="#submitPedidoModal">Hacer Pedido</button>
		</div>
	</section>
</article>
<div class="modal fade" id="submitPedidoModal" tabindex="-1" role="dialog" aria-labelledby="submitPedidoModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="submitPedidoModalLabel">¿Confirmar Pedido?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        (Contenido del pedido)
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