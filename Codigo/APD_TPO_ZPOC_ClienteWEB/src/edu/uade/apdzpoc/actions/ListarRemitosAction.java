package edu.uade.apdzpoc.actions;

import edu.uade.apdzpoc.negociodelegado.BusinessDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Collections;

public class ListarRemitosAction implements IAction {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        //TODO Obtener Remitos
        Collection remitos = Collections.emptyList();
        request.setAttribute("remitos", remitos);
        return "/listarRemitos.jsp";
    }
}
