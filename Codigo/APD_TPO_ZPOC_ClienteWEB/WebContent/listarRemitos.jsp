<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="edu.uade.apdzpoc.dto.RemitoAlmacenDTO"%>

<article class="container grid col-12 col-md-10 mt-2">
	<h1>Remitos del Almac&eacute;n</h1>
	
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
					List<RemitoAlmacenDTO> rmts = (List<RemitoAlmacenDTO>)request.getAttribute("remitos");
        			RemitoAlmacenDTO aux;

					
					for (Iterator<RemitoAlmacenDTO> i = rmts.iterator(); i.hasNext();) {
						aux = i.next();
				%>
        
        			<tr class="orden-row">
					<th scope="row"><%=aux.getIdRemito()%></th>
					<td><%=aux.getNro()%></td>
					<td><%=aux.getEstado()%></td>
					<td><%=aux.getTipo().toString()%></td>
					<td><%=aux.getItemsRemito().size()%></td>
					<td><button class="btn btn-sm btn-warning btn-validar"data-remito=""'><i class="fas fa-search"></i> Ver Detalle</button>
				</tr>
				<% } %>
        	</tbody>
		</table>
	</section>
</article>
