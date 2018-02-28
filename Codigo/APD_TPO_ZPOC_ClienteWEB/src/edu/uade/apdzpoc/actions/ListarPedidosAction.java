package edu.uade.apdzpoc.actions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uade.apdzpoc.dto.PedidoWebDTO;
import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.negociodelegado.BusinessDelegate;

public class ListarPedidosAction implements IAction {

	@Override
	public boolean isValid(String action) {
		return "ListarPedidos".equals(action);
	}

	@Override
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws ParseException {
    	List<PedidoWebDTO> pedidosPendientes = new ArrayList<>();
    	try {
			 pedidosPendientes = BusinessDelegate.getInstancia().obtenerPedidosParaDespachar();
		} catch (ComunicationException e) {
			e.printStackTrace();
		}
    	
    	request.setAttribute("pedidosPendientes", pedidosPendientes);
    	
		return "/listarpedidos.jsp";
	}

}
