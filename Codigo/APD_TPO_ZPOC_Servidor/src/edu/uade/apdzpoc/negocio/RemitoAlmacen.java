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

	public RemitoAlmacen() { }
	
	public RemitoAlmacen(EstadoRemito estado, List<ItemRemitoAlmacen> itemsRemito, TipoRemitoAlmacen tipo, int nro) {
		this.estado = estado;
		this.itemsRemito = itemsRemito;
		this.tipo = tipo;
		this.nro = nro;
	}

	public RemitoAlmacen(PedidoWeb pw) {
		this.estado = EstadoRemito.Pendiente;
		this.itemsRemito = this.generarItemsRemito(pw.getItems());
		this.tipo = TipoRemitoAlmacen.PedidoWeb;
		this.nro = pw.getIdPedido();
	}

	private List<ItemRemitoAlmacen> generarItemsRemito(List<ItemPedido> items) {
		List<ItemRemitoAlmacen> itemsRemitoAlmacen = new ArrayList<>();
		for (ItemPedido item : items) {
			List<Ubicacion> ubicacionesItem = Almacen.getInstancia().buscarUbicaciones(item);
			for (Ubicacion ubicacionItem : ubicacionesItem) {
				itemsRemito.add(new ItemRemitoAlmacen(item.getArticulo(), item.getCantidad(), ubicacionItem));
			}
		}
		return itemsRemitoAlmacen;
	}
	
	public void generarItemsRemito(MovimientoAjuste ma) {
		List<ItemRemitoAlmacen> itemsRemitoAlmacen = new ArrayList<>();
		
		int cantidadAjustar = ma.getCantidad();

		List<Ubicacion> ubicacionesArticulo = ma.getLote().getUbicaciones();
		
		for (int i = 0; cantidadAjustar > 0 && i < ubicacionesArticulo.size(); i++) {
			if (cantidadAjustar <= ubicacionesArticulo.get(i).getCapacidad()) {
				itemsRemitoAlmacen.add(new ItemRemitoAlmacen(ma.getArticulo(), cantidadAjustar, ubicacionesArticulo.get(i)));
				cantidadAjustar = 0;
			} else {
				cantidadAjustar =- ubicacionesArticulo.get(i).getCapacidad();
				itemsRemitoAlmacen.add(new ItemRemitoAlmacen(ma.getArticulo(), ubicacionesArticulo.get(i).getCapacidad(), ubicacionesArticulo.get(i)));
			}
			
		}

		this.setItemsRemito(itemsRemitoAlmacen);
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
