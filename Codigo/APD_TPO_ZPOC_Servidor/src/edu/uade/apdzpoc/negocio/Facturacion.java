/**
 * 
 * 
 * TPO: APDZPOC
 * 
 * GRUPO 08
 * Integrantes:
 * 	LU:0119404	- Zapatero, Barbara Daniela
 * 	LU:1022185	- Pablos, Diego Maximiliano
 * 	LU:0133009	- Ojeda, Maria De Los Angeles
 *  LU:0127304	- Cavallaro, Cristian Alberto
 *  
 *
 */

package edu.uade.apdzpoc.negocio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uade.apdzpoc.dao.FacturaDAO;
import edu.uade.apdzpoc.enums.EstadoFactura;

public class Facturacion {
	private static Facturacion instancia;

	private Facturacion() {	}

	public static Facturacion getInstancia() {
		if (instancia == null)
			instancia = new Facturacion();
		return instancia;
	}

	public boolean alcanzaLimiteCTA(PedidoWeb pw) {
		List<ItemPedido> itemsComprados = pw.getItems();
		Cliente cliente = pw.getCliente();
		float totalCompra = 0f;

		for (ItemPedido item : itemsComprados) {
			totalCompra += item.calcularTotal();
		}

		return cliente.getCuentaCorriente().getSaldo() > totalCompra;
	}

	public void crearFactura(PedidoWeb pw) {
		Date fechaEmision = new Date(); // Fecha actual
		
		// Pongo la facturo a vencer dentro de tres meses:
		Date fechaVencimiento = Date.from(LocalDate.now().plusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant());

		// Si el cliente es responsable inscripto, es factura A. Factura B es para los demás.
		String tipoFactura = pw.getCliente().isIvaInscripto() ? "A" : "B";

		List<ItemFactura> itemsFactura = new ArrayList<>();
		for (ItemPedido item : pw.getItems()) {
			// Está bien guardar el calculartotal como campo de itemFactura al momento de crearlo,
			// ya que en el futuro podría cambiar el precio del artículo, pero no debería cambiar el precio en la factura emitida.
			itemsFactura.add(new ItemFactura(item.getArticulo(), item.getCantidad(), item.calcularTotal()));
		}

		// Persisto la nueva factura
		FacturaDAO.getInstancia()
				.save(new Factura(pw.getCliente(), fechaEmision, fechaVencimiento, tipoFactura, itemsFactura));
	}

	public void crearRemitoTransporte(PedidoWeb pw, String empresaTransporte) {
		new RemitoTransporte(empresaTransporte, pw).save();
	}
	
	public void ingresarPagoCliente(Cliente cliente, Factura factura) {
		//factura.setEstado(EstadoFactura.Paga);
	}

}
