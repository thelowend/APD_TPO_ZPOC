package edu.uade.apdzpoc.actions;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IngresarOrdenCompraAction implements IAction {

	@Override
	public boolean isValid(String action) {
		// TODO Auto-generated method stub
		return "IngresarOrdenCompra".equals(action);
	}

	@Override
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		// TODO Auto-generated method stub
		return "/ingresarcompra.jsp";
	}

}