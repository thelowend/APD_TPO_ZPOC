package edu.uade.apdzpoc.servlets;

import edu.uade.apdzpoc.actions.IAction;
import edu.uade.apdzpoc.actions.ListarRemitosAction;
import edu.uade.apdzpoc.actions.NotFoundAction;
import edu.uade.apdzpoc.actions.SeleccionarRemitoAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import controlador.Controller;
//import negocio.Cliente;

@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {

    private static final long serialVersionUID = 5459613143358646096L;

    private Collection<IAction> actions = new HashSet<>();

    @Override
    public void init() throws ServletException {
        super.init();
        actions.add(new ListarRemitosAction());
        actions.add(new SeleccionarRemitoAction());
        System.out.println();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final String action = request.getParameter("action");
        String jspPage = "/empty.jsp";
        //TODO Cambiar por un switch o una iteracion de IAction (ejemplo abajo)
        if ("IngresarPedido".equals(action)) {
            jspPage = "/ingresarpedido.jsp";
        } else if ("ListarPedidos".equals(action)) {
            jspPage = "/listarpedidos.jsp";
        } else if ("IngresarOrdenCompra".equals(action)) {
            jspPage = "/ingresarcompra.jsp";
        } else if ("ListarOrdenesCompra".equals(action)) {
            jspPage = "/listarordenescompra.jsp";
        } else if ("ValidarOrdenCompra".equals(action)) {
            jspPage = "/validarordencompra.jsp";
        } else if ("IngresarPagoCliente".equals(action)) {
            jspPage = "/ingresarpago.jsp";
        } else if ("DespacharPedido".equals(action)) {
    		String test = request.getParameter("id");
            jspPage = "/listarpedidos.jsp";
        } else if ("ListarRemitos".equals(action)) {
            IAction found = NotFoundAction.NOT_FOUND_ACTION;
            for (IAction a : this.actions) {
                if (a.isValid(action)) {
                    found = a;
                    break;
                }
            }
            jspPage = found.doAction(request, response);
        }

        dispatch(jspPage, request, response);
    }

    protected void dispatch(String jsp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (jsp != null) {
            RequestDispatcher rd = request.getRequestDispatcher(jsp);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            rd.forward(request, response);
        }
    }
}
