package edu.uade.apdzpoc.actions;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uade.apdzpoc.dto.ArticuloDTO;
import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.dto.OrdenCompraDTO;
import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.negociodelegado.BusinessDelegate;

public class IngresarPedidoAction implements IAction {

	@Override
	public boolean isValid(String action) {
		// TODO Auto-generated method stub
		return "IngresarPedido".equals(action);
	}

	@Override
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws ParseException, RemoteException {
		List<ArticuloDTO> articulos = new ArrayList<>();
		List<ClienteDTO> clientes = new ArrayList<>();
		try {
			articulos = BusinessDelegate.getInstancia().obtenerArticulosParaPublicar();
			clientes = BusinessDelegate.getInstancia().obtenerClientesParaPublicar();
		} catch (ComunicationException e) {
			e.printStackTrace();
			request.setAttribute("errores", e.getMessage());
		}
    	
    	request.setAttribute("articulos", articulos);
    	request.setAttribute("clientes", articulos);
		
		return "/ingresarpedido.jsp";
	}

}
