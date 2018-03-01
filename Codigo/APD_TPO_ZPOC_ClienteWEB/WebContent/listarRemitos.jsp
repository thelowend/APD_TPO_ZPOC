<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="edu.uade.apdzpoc.dto.RemitoAlmacenDTO"%>
<%@ page import="java.util.Date"%>

<article class="container grid col-12 col-md-10 mt-2">
	<h1>&Oacute;rdenes de Compra</h1>
	
<section class="row col-12 seleccionar-articulos mt-4 mb-4">

    <table class="table table-stripe table-light">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Numero</th>
            <th>Estado</th>
            <th>Tipo</th>
            <th>Cantidad Items</th>
            <th>Seleccion</th>
        </tr>
        </thead>
        <tbody>
        
        <%
					List<RemitoAlmacenDTO> rmts = (List<RemitoAlmacenDTO>)request.getAttribute("remitosAlmacen");
        			RemitoAlmacenDTO aux;
					String id;
					String numero;
					String estado;
					String tipo;
					String cantidad;
					String ordenJSONstr = "";
					
					for (Iterator<RemitoAlmacenDTO> i = rmts.iterator(); i.hasNext();) {
						aux = i.next();
				%>
        
        			<tr class="orden-row">
					<th scope="row"><%=aux.getIdRemito()%></th>
					<td><%=aux.getNro()%></td>
					<td><%=aux.getEstado()%></td>
					<td><%=aux.getTipo().toString()%></td>
					<td><%=aux.getItemsRemito().size()%></td>
					<td><button class="btn btn-sm btn-warning btn-validar"
							data-pedido='<%=ordenJSONstr%>'><i class="fas fa-check-square"></i> Validar</button>
				</tr>
				<% } %>
        	</tbody>
		</table>
	</section>
</article>
<div class="modal fade" id="listarRemitosModal" tabindex="-1"
	role="dialog" aria-labelledby="listarRemitosModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="listarRemitosModalLabel">¿Validar Remito?</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				(DATOS DEL REMITO)
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-warning" data-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-dark" id="submitOrden">Validar
					Remito</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/listarordenes.js"></script>