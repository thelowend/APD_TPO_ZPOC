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
		return pw.getCliente().leAlcanza(pw.calcularTotal());
	}

	public void crearFactura(PedidoWeb pw) {
		new Factura(pw).save();
	}

	public void crearRemitoTransporte(PedidoWeb pw, String empresaTransporte) {
		new RemitoTransporte(empresaTransporte, pw).save();
	}
	
	public void ingresarPagoCliente(PagoCliente pc) {

		Factura factura = pc.getFactura();
		CuentaCorriente cc = pc.getFactura().getCliente().getCuentaCorriente();
		
		cc.actulizarSaldo(factura.getTotalFactura());
		cc.save();
		
		factura.setEstado(EstadoFactura.Paga);
		factura.save();
		
	}

}
