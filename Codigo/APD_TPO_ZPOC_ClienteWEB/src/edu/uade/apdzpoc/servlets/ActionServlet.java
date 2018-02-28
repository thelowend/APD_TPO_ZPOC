package edu.uade.apdzpoc.servlets;

import edu.uade.apdzpoc.actions.ListarRemitosAction;
import edu.uade.apdzpoc.actions.SeleccionarRemitoAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String jspPage = "/empty.jsp";

		//TODO Cambiar por un switch o una iteracion de IAction (simil Patron Strategy)
		if ((action == null) || (action.length() < 1)) {
			action = "default";
		} else if ("IngresarPedido".equals(action)) {
			jspPage = "/ingresarpedido.jsp";
		}  else if ("ListarPedidos".equals(action)) {
			jspPage = "/listarpedidos.jsp";
		} else if ("IngresarOrdenCompra".equals(action)) {
			jspPage = "/ingresarcompra.jsp";
		} else if ("ListarOrdenesCompra".equals(action)) {
			jspPage = "/listarordenescompra.jsp";
		} else if ("ValidarOrdenCompra".equals(action)) {
			jspPage = "/validarordencompra.jsp";
		} else if ("IngresarPagoCliente".equals(action)) {
			jspPage = "/ingresarpago.jsp";
		}  else if ("ListarRemitos".equals(action)) {
			jspPage = new ListarRemitosAction().doAction(request,response);
		} else if ("SeleccionarRemito".equals(action)) {
            jspPage = new SeleccionarRemitoAction().doAction(request,response);
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
