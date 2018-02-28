package edu.uade.apdzpoc.actions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uade.apdzpoc.dto.OrdenCompraDTO;

import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.negociodelegado.BusinessDelegate;

public class ListarOrdenesCompraAction implements IAction {

	@Override
	public boolean isValid(String action) {
		return "ListarOrdenesCompra".equals(action);
	}

	@Override
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws ParseException {
    	List<OrdenCompraDTO> ordenesCompra = new ArrayList<>();
    	try {
    		ordenesCompra = BusinessDelegate.getInstancia().obtenerOrdenesdeCompraParaValidar();
		} catch (ComunicationException e) {
			e.printStackTrace();
		}
    	
    	request.setAttribute("ordenesCompra", ordenesCompra);
		return "/listarordenescompra.jsp";
	}
}
