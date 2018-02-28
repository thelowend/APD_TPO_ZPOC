<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Listar Remitos:</h1>

<div class="container">
    <table class="table table-striped">
        <thead>
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
        <c:forEach items="${remitos}" var="rem">
            <tr>
                <td>${rem.idRemito}</td>
                <td>${rem.nro}</td>
                <td>${rem.estado}</td>
                <td>${rem.tipo.name()}</td>
                <td>${rem.items.size()}</td>
                <td><a class="btn btn-primary ajax-link" href="ActionServlet?action=SeleccionarRemito&idRemito=${rem.idRemito}">Seleccionar</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>