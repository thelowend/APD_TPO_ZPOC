package edu.uade.apdzpoc.actions;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import edu.uade.apdzpoc.dto.ArticuloDTO;
import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.dto.OrdenCompraDTO;
import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.negociodelegado.BusinessDelegate;

public class EnviarPedidoAction implements IAction {

	@Override
	public boolean isValid(String action) {
		// TODO Auto-generated method stub
		return "EnviarPedido".equals(action);
	}

	@Override
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws ParseException, RemoteException, JSONException {

    	
    	Integer cliente = (Integer) request.getAttribute("cliente");
    	String direccion = (String) request.getAttribute("direccion");
    	String jsonString = (String) request.getAttribute("articulos");
    	
    	JSONObject json = new JSONObject(jsonString.toString());
    	
    	ClienteDTO clientePedido = new ClienteDTO(cliente, 123, "");
    	//ObjectMapper mapper = new ObjectMapper();
    	//List<ItemPedidoDTO> itemspedido = mapper.readValue(jsonString, new TypeReference<List<ItemPedidoDTO>>(){});
    	
    	//BusinessDelegate.getInstancia().crearPedidoWeb(articulosComprados, cliente, direccion)
		
		return "/ingresarpedido.jsp";
	}

}
