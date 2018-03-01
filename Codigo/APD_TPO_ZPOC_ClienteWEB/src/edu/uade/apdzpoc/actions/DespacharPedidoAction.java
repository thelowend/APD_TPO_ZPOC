package edu.uade.apdzpoc.actions;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uade.apdzpoc.dto.PedidoWebDTO;
import edu.uade.apdzpoc.excepciones.ComunicationException;
import edu.uade.apdzpoc.excepciones.PedidoWebException;
import edu.uade.apdzpoc.negociodelegado.BusinessDelegate;

public class DespacharPedidoAction implements IAction {

    @Override
    public boolean isValid(String action) {
        return "DespacharPedido".equals(action);
    }

    @Override
    public String doAction(HttpServletRequest request, HttpServletResponse response) throws ParseException {
    	
    	List<PedidoWebDTO> pedidosPendientesActualizados = new ArrayList<>();
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Integer idPedidoWeb = Integer.valueOf(request.getParameter("idPedido"));
        Date fechaEntrega = formatter.parse(request.getParameter("fechaEntrega"));
        String empresaTransporte = request.getParameter("empresatransporte");
        
		try {
			BusinessDelegate delegado = BusinessDelegate.getInstancia();
			PedidoWebDTO pedidoWebDTO = delegado.obtenerPedidoWebParaPublicar(idPedidoWeb);
			delegado.despacharPedido(pedidoWebDTO, fechaEntrega, empresaTransporte);
			
	    	pedidosPendientesActualizados = BusinessDelegate.getInstancia().obtenerPedidosParaDespachar();
			
		} catch (RemoteException | ComunicationException | PedidoWebException e) {
			request.setAttribute("errores", e.getMessage());
		}
		
		request.setAttribute("pedidosPendientes", pedidosPendientesActualizados);
		
        return "/listarpedidos.jsp";
    }
}
