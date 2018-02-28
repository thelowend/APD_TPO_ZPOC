package edu.uade.apdzpoc.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uade.apdzpoc.dto.ClienteDTO;
import edu.uade.apdzpoc.dto.ItemPedidoDTO;
import edu.uade.apdzpoc.dto.PedidoWebDTO;
import edu.uade.apdzpoc.enums.EstadoPedido;
import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.negociodelegado.BusinessDelegate;

public class DespacharPedidoAction implements IAction {

    @Override
    public boolean isValid(String action) {
        return "DespacharPedido".equals(action);
    }

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) throws ParseException {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	
        Integer idPedidoWeb = Integer.valueOf(request.getParameter("id"));
        Date fechaEntrega = formatter.parse(request.getParameter("fechaentrega"));
        String empresaTransporte = request.getParameter("empresatransporte");
        
        System.out.println(idPedidoWeb);
        
//        PedidoWebDTO pwDTO = new PedidoWebDTO(Integer idPedido, ClienteDTO cliente, Date fechaGeneracion, Date fechaDespacho,
//				Date fechaDeEntrega, EstadoPedido estadoPedido, String direccionPedido, List<ItemPedidoDTO> items)
//        		
//        BusinessDelegate.getInstancia().despacharPedido(pwDTO, fechaEntrega, empresaTransporte);
        		
//    	List<PedidoWebDTO> pedidosPendientes = new ArrayList<>();
//    	try {
//    		
//			 pedidosPendientes = BusinessDelegate.getInstancia().obtenerPedidosParaDespachar();
//		} catch (ComunicationException e) {
//			e.printStackTrace();
//		}
//    	request.setAttribute("pedidosPendientes", pedidosPendientes);
        
        return "/listarpedidos.jsp";
    }
}
