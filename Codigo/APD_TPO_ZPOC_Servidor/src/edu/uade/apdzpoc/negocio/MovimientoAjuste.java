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

import java.util.Date;

import edu.uade.apdzpoc.dao.MovimientoAjusteDAO;
import edu.uade.apdzpoc.enums.CausaAjuste;
import edu.uade.apdzpoc.enums.DestinoArticulos;

public class MovimientoAjuste extends Movimiento {

	private int legajoOperador;
	private int legajoAutorizante;
	private DestinoArticulos destino;
	private CausaAjuste causa;
	private Lote lote;

	public MovimientoAjuste(Date fecha, Articulo articulo, int cantidad, CausaAjuste causa, int legajoOperador,
			int legajoAutorizante, DestinoArticulos destino, Lote lote) {
		super(fecha, articulo, cantidad);
		this.causa = causa;
		this.legajoOperador = legajoOperador;
		this.legajoAutorizante = legajoAutorizante;
		this.destino = destino;
		this.lote = lote;
	}

	public MovimientoAjuste(Date fecha, Articulo articulo, int cantidad/* , String tipo */) {
		super(fecha, articulo, cantidad);
		// TODO Auto-generated constructor stub
	}

	public MovimientoAjuste() {
		// TODO Auto-generated constructor stub
	}

	public int getLegajoOperador() {
		return legajoOperador;
	}

	public void setLegajoOperador(int legajoOperador) {
		this.legajoOperador = legajoOperador;
	}

	public int getLegajoAutorizante() {
		return legajoAutorizante;
	}

	public void setLegajoAutorizante(int legajoAutorizante) {
		this.legajoAutorizante = legajoAutorizante;
	}

	public DestinoArticulos getDestino() {
		return destino;
	}

	public void setDestino(DestinoArticulos destino) {
		this.destino = destino;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	@Override
	public void actualizarNovedadStock() {

		int cantidadVencida = this.cantidad;

		int stockActual = this.articulo.getStockDisponible() - cantidadVencida;
		int stockFisico = this.articulo.getStockFisico() - cantidadVencida;

		this.articulo.setStockDisponible(stockActual < 0 ? 0 : stockActual);
		this.articulo.setStockVirtual(stockActual > 0 ? 0 : stockActual);
		this.articulo.setStockFisico(stockFisico > 0 ? stockFisico : 0);

		this.articulo.save();
	}

	public CausaAjuste getCausa() {
		return causa;
	}

	public void setCausa(CausaAjuste causa) {
		this.causa = causa;
	}

	public void save() {
		MovimientoAjusteDAO.getInstancia().save(this);
	}

	public MovimientoAjuste saveAndFetch() {
		return MovimientoAjusteDAO.getInstancia().saveAndFetch(this);
	}
}
