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
				<tr class="orden-row">
					<th scope="row">1</th>
					<td>Orden 1</td>
					<td>Bla bla</td>
					<td><button class="btn-dark btn-validar"
							data-orden="orden1">Validar</button></td>
				</tr>
				<tr class="orden-row">
					<th scope="row">2</th>
					<td>Orden 2</td>
					<td>Bla bla bla bla</td>
					<td><button class="btn-dark btn-validar"
							data-orden="orden2">Validar</button></td>
				</tr>
				<tr class="orden-row">
					<th scope="row">3</th>
					<td>Orden 3</td>
					<td>Bla bla bla bla bla bla</td>
					<td><button class="btn-dark btn-validar"
							data-orden="orden3">Validar</button></td>
				</tr>
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