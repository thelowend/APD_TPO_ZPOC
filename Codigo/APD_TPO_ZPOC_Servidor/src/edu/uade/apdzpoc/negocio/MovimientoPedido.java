/**
 * TPO: APDZPOC
 * <p>
 * GRUPO 08
 * Integrantes:
 * LU:0119404	- Zapatero, Barbara Daniela
 * LU:1022185	- Pablos, Diego Maximiliano
 * LU:0133009	- Ojeda, Maria De Los Angeles
 * LU:0127304	- Cavallaro, Cristian Alberto
 */

package edu.uade.apdzpoc.negocio;

import edu.uade.apdzpoc.dao.MovimientoPedidoDAO;

import java.util.Date;

public class MovimientoPedido extends Movimiento {

    private PedidoWeb pw;

    public MovimientoPedido(Date fecha, Articulo articulo, int cantidad) {
        super(fecha, articulo, cantidad);
    }

    public MovimientoPedido(Date fecha, Articulo articulo, int cantidad, PedidoWeb pw) {
        super(fecha, articulo, cantidad);
        this.pw = pw;
    }

    public MovimientoPedido() {
        // TODO Auto-generated constructor stub
    }

    public PedidoWeb getPw() {
        return pw;
    }

    public void setPw(PedidoWeb pw) {
        this.pw = pw;
    }

    public void actualizarNovedadStock() {
        int cantidadPedido = this.cantidad;
        
        if (this.articulo.getStockDisponible()-cantidadPedido>0) {
       // yo agregue el if y la siguiente linea          
        this.articulo.setStockDisponible(this.articulo.getStockDisponible()-cantidadPedido);
        }else {
//esto es lo que estaba antes
        int stockActual = this.articulo.getStockDisponible() + cantidadPedido + this.articulo.getStockVirtual();
        this.articulo.setStockDisponible(stockActual < 0 ? 0 : stockActual);
        this.articulo.setStockVirtual(stockActual > 0 ? 0 : stockActual);
        this.articulo.setStockPendienteEntrega(this.articulo.getStockPendienteEntrega() - cantidadPedido);
      
        this.articulo.save();
        }
    }
    public void save() {
        MovimientoPedidoDAO.getInstancia().save(this);
    }
    
    public MovimientoPedido saveAndFetch() {
    	return MovimientoPedidoDAO.getInstancia().saveAndFetch(this);
    }
}
