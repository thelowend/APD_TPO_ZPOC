package edu.uade.apdzpoc.actions;

import edu.uade.apdzpoc.negociodelegado.BusinessDelegate;
import edu.uade.apdzpoc.dto.RemitoAlmacenDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Collections;

public class ListarRemitosAction implements IAction {

    @Override
    public boolean isValid(String action) {
        return "ListarRemitos".equals(action);
    }

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        //TODO Obtener Remitos
        //List<RemitoAlmacenDTO> remitos = BusinessDelegate.getInstancia().obtenerRemitosAlmacen();
    	Collection remitos = Collections.emptyList();
        request.setAttribute("remitos", remitos);
        return "/listarRemitos.jsp";
    }
}
