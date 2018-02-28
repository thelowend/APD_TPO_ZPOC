package edu.uade.apdzpoc.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 2481325140950452392L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String jspPage = "/layout.jsp";
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");

		// Acá llamaría un servicio de Login de verdad.
		if ((usuario.equals("Godio")) && (password.equals("powermetal"))) {
			dispatch(jspPage, request, response);
		} else {
			jspPage = "/login.jsp?error=LoginInvalido";
			dispatch(jspPage, request, response);
		}
		
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
