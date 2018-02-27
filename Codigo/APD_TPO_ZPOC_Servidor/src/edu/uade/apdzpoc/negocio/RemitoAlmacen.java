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

import java.util.ArrayList;
import java.util.List;

import edu.uade.apdzpoc.dao.RemitoAlmacenDAO;
import edu.uade.apdzpoc.enums.EstadoRemito;
import edu.uade.apdzpoc.enums.TipoRemitoAlmacen;

public class RemitoAlmacen {

	private Integer idRemito;
	private EstadoRemito estado;
	private List<ItemRemitoAlmacen> itemsRemito;
	private TipoRemitoAlmacen tipo;
	private int nro;


	public RemitoAlmacen(EstadoRemito estado, List<ItemRemitoAlmacen> itemsRemito, TipoRemitoAlmacen tipo, int nro) {
		this.estado = estado;
		this.itemsRemito = itemsRemito;
		this.tipo = tipo;
		this.nro = nro;
	}

	public RemitoAlmacen(PedidoWeb pw) {
		this.estado = EstadoRemito.Pendiente;
		this.itemsRemito = this.generarItemsRemito(pw.getItems());
		this.tipo = tipo;
		this.nro = nro;
	}

	private List<ItemRemitoAlmacen> generarItemsRemito(List<ItemPedido> items) {
		List<ItemRemitoAlmacen> itemsRemitoAlmacen = new ArrayList<>();
		for (ItemPedido item : items) {
			// Est� bien guardar el calculartotal como campo de itemFactura al momento de crearlo, ya que en el futuro podr�a cambiar el precio del art�culo, pero no deber�a cambiar el precio en la factura emitida.
			List<Ubicacion> ubicacionesItem = Almacen.getInstancia().buscarUbicaciones(item);
			for (Ubicacion ubicacionItem : ubicacionesItem) {
				itemsRemito.add(new ItemRemitoAlmacen(item.getArticulo(), item.getCantidad(), ubicacionItem));
			}
		}
		return itemsRemitoAlmacen;
	}

	public RemitoAlmacen() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdRemito() {
		return idRemito;
	}
	public void setIdRemito(Integer idRemito) {
		this.idRemito = idRemito;
	}

	public EstadoRemito getEstado() {
		return estado;
	}

	public void setEstado(EstadoRemito estado) {
		this.estado = estado;
	}

	public List<ItemRemitoAlmacen> getItemsRemito() {
		return itemsRemito;
	}

	public void setItemsRemito(List<ItemRemitoAlmacen> itemsRemito) {
		this.itemsRemito = itemsRemito;
	}

	public TipoRemitoAlmacen getTipo() {
		return tipo;
	}

	public void setTipo(TipoRemitoAlmacen tipo) {
		this.tipo = tipo;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public void save() {
		RemitoAlmacenDAO.getInstancia().save(this);
	}

}
