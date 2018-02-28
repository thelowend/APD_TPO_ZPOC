package edu.uade.apdzpoc.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SeleccionarRemitoAction implements IAction {

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) {
        Integer idRemito = Integer.valueOf(request.getParameter("idRemito"));
        System.out.println(idRemito);
        return "/listarRemitos.jsp";
    }
}
