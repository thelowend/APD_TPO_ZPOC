package edu.uade.apdzpoc.actions;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.uade.apdzpoc.dto.ArticuloDTO;
import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.dto.ItemPedidoDTO;
import edu.uade.apdzpoc.dto.OrdenCompraDTO;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.negociodelegado.BusinessDelegate;

public class EnviarPedidoAction implements IAction {

	@Override
	public boolean isValid(String action) {
		// TODO Auto-generated method stub
		return "EnviarPedido".equals(action);
	}

	@Override
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws ParseException, RemoteException, JSONException, ComunicationException, NumberFormatException, ArticuloException {

    	
    	Integer cliente = Integer.valueOf(request.getParameter("cliente"));
    	String direccion = request.getParameter("direccion");
    	String jsonString = request.getParameter("articulos");
    	
    	JSONArray articulos = new JSONArray(String.valueOf(jsonString));
    	List<ItemPedidoDTO> artsPedido = new ArrayList<>();
    	
    	List<ClienteDTO> clientes = BusinessDelegate.getInstancia().obtenerClientesParaPublicar();
    	//
		for (int i = 0; i < articulos.length(); i++) {
			JSONObject objects = articulos.getJSONObject(i);
			ArticuloDTO artDTO = BusinessDelegate.getInstancia().obtenerArticuloPorCodigo(Integer.valueOf((String) objects.get("codigoBarra")));
			artsPedido.add(new ItemPedidoDTO(artDTO, (int) objects.get("cant")));
		}
    	
    	ClienteDTO clientePedido = new ClienteDTO(cliente, 123, "");
    	
    	//BusinessDelegate.getInstancia().crearPedidoWeb(articulosComprados, cliente, direccion)
		
		return "/ingresarpedido.jsp";
	}

}
