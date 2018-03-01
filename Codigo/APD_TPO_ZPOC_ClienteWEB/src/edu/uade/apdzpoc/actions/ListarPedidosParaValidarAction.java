package edu.uade.apdzpoc.actions;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import edu.uade.apdzpoc.dto.PedidoWebDTO;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.negociodelegado.BusinessDelegate;

public class ListarPedidosParaValidarAction implements IAction {

	@Override
	public boolean isValid(String action) {
		return "ListarPedidosParaValidar".equals(action);
		
	}

	@Override
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws ParseException,
			RemoteException, JSONException, ComunicationException, NumberFormatException, ArticuloException {
		List<PedidoWebDTO> pedidosParaValidar = new ArrayList<>();
    	try {
    		pedidosParaValidar = BusinessDelegate.getInstancia().obtenerPedidosParaProcesar();
		} catch (ComunicationException e) {
			e.printStackTrace();
		}
    	
    	request.setAttribute("pedidos", pedidosParaValidar);
		return "/listarpedidosparavalidar.jsp";
	}

}
