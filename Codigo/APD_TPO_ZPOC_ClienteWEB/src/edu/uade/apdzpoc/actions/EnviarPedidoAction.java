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
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ArticuloProveedorException;
import edu.uade.apdzpoc.excepciones.ClienteException;
import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.excepciones.ProveedorException;
import edu.uade.apdzpoc.negociodelegado.BusinessDelegate;

public class EnviarPedidoAction implements IAction {

	@Override
	public boolean isValid(String action) {
		// TODO Auto-generated method stub
		return "EnviarPedido".equals(action);
	}

	@Override
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws ParseException, RemoteException, JSONException, ComunicationException, NumberFormatException, ArticuloException {
		
		String jsonCliente = request.getParameter("cliente");
		String direccion = request.getParameter("direccion");
		String jsonArticulos = request.getParameter("articulos");
    	
		JSONObject cliente = new JSONObject(jsonCliente);
    	JSONArray articulos = new JSONArray(jsonArticulos);
    	
    	ClienteDTO clientePedido = new ClienteDTO((int) cliente.get("id"), (int) cliente.get("documento"), (String) cliente.get("nombre"));
    	List<ItemPedidoDTO> articulosPedido = new ArrayList<>();
    	
		for (int i = 0; i < articulos.length(); i++) {
			JSONObject objects = articulos.getJSONObject(i);
			ArticuloDTO artDTO = BusinessDelegate.getInstancia().obtenerArticuloPorCodigo(Integer.valueOf((String) objects.get("codigoBarra")));
			articulosPedido.add(new ItemPedidoDTO(artDTO, (int) objects.get("cant")));
		}
    	
    	try {
    		BusinessDelegate delegadonegocio = BusinessDelegate.getInstancia();
    		delegadonegocio.crearPedidoWeb(articulosPedido, clientePedido, direccion);
			List<ArticuloDTO> articulosActualizados = delegadonegocio.obtenerArticulosParaPublicar();
			List<ClienteDTO> clientesActualizados = delegadonegocio.obtenerClientesParaPublicar();
		    request.setAttribute("articulos", articulosActualizados);
		    request.setAttribute("clientes", clientesActualizados);

		} catch (ArticuloProveedorException | ProveedorException | ClienteException | ComunicationException e) {
			request.setAttribute("errores", e.getMessage());
		}
		
		return "/ingresarpedido.jsp";
	}

}
