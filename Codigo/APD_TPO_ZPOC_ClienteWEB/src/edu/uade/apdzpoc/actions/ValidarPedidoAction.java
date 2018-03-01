package edu.uade.apdzpoc.actions;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;


import edu.uade.apdzpoc.dto.PedidoWebDTO;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.excepciones.PedidoWebException;
import edu.uade.apdzpoc.excepciones.ProveedorException;
import edu.uade.apdzpoc.negociodelegado.BusinessDelegate;

public class ValidarPedidoAction implements IAction {

	@Override
	public boolean isValid(String action) {
		return "ValidarPedido".equals(action);
	}

	@Override
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws ParseException,
			RemoteException, JSONException, ComunicationException, NumberFormatException, ArticuloException {
		
		BusinessDelegate delegadonegocio = BusinessDelegate.getInstancia();
		List<PedidoWebDTO> pedidosParaValidar = new ArrayList<>();
		String jsonPedido = request.getParameter("pedido");
		JSONObject pedido = new JSONObject(jsonPedido);
		PedidoWebDTO pw;
		
		try {
			pw = delegadonegocio.obtenerPedidoWebParaPublicar((int) pedido.get("idPedido"));
			delegadonegocio.procesarPedido(pw);
			pedidosParaValidar = delegadonegocio.obtenerPedidosParaProcesar();
		} catch (PedidoWebException | ArticuloProveedorException | ProveedorException e) {
			request.setAttribute("errores", e.getMessage());
		}
		
    	request.setAttribute("pedidos", pedidosParaValidar);
		return "/listarpedidosparavalidar.jsp";
	}

}
