package edu.uade.apdzpoc.servlets;

import edu.uade.apdzpoc.actions.DespacharPedidoAction;
import edu.uade.apdzpoc.actions.EnviarPedidoAction;
import edu.uade.apdzpoc.actions.IAction;
import edu.uade.apdzpoc.actions.IngresarOrdenCompraAction;
import edu.uade.apdzpoc.actions.IngresarPagoClienteAction;
import edu.uade.apdzpoc.actions.IngresarPedidoAction;
import edu.uade.apdzpoc.actions.ListarOrdenesCompraAction;
import edu.uade.apdzpoc.actions.ListarPedidosAction;
import edu.uade.apdzpoc.actions.ListarPedidosParaValidarAction;
import edu.uade.apdzpoc.actions.ListarRemitosAction;
import edu.uade.apdzpoc.actions.NotFoundAction;
import edu.uade.apdzpoc.actions.SeleccionarRemitoAction;
import edu.uade.apdzpoc.actions.ValidarOrdenCompraAction;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ComunicationException;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;


@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {

	private static final long serialVersionUID = 5459613143358646096L;

	private Collection<IAction> actions = new HashSet<>();

	@Override
	public void init() throws ServletException {
		super.init();
		actions.add(new DespacharPedidoAction());
		actions.add(new EnviarPedidoAction());
		actions.add(new ListarRemitosAction());
		actions.add(new SeleccionarRemitoAction());
		actions.add(new IngresarOrdenCompraAction());
		actions.add(new IngresarPagoClienteAction());
		actions.add(new IngresarPedidoAction());
		actions.add(new ListarOrdenesCompraAction());
		actions.add(new ListarPedidosAction());
		actions.add(new SeleccionarRemitoAction());
		actions.add(new ValidarOrdenCompraAction());
		actions.add(new ListarPedidosParaValidarAction());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String action = request.getParameter("action");
		String jspPage = "/empty.jsp";

		IAction found = NotFoundAction.NOT_FOUND_ACTION;
		for (IAction a : this.actions) {
			if (a.isValid(action)) {
				found = a;
				break;
			}
		}

		try {
			jspPage = found.doAction(request, response);
		} catch (ParseException | JSONException | ComunicationException | NumberFormatException | ArticuloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
