package edu.uade.apdzpoc.entidades;

import java.util.Date;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="Movimiento_Stock")
@DiscriminatorColumn(name="Tipo_Movimiento", discriminatorType=DiscriminatorType.STRING)
public abstract class MovimientoEntity {

	@Id
	@Column(name="Id_MStock")
	protected Integer idMovimiento;
	
	@Column(name="Fecha")
	protected Date fecha;
	
	@OneToOne
	@JoinColumn(name="Codigo_Barra")
	protected ArticuloEntity articulo;
	
	@Column(name="Cantidad")
	protected Integer cantidad;
	
	
	
	
	public MovimientoEntity() {
		// TODO Auto-generated constructor stub
	}





	public Integer getIdMovimiento() {
		return idMovimiento;
	}





	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}





	public Date getFecha() {
		return fecha;
	}





	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}





	public ArticuloEntity getArticulo() {
		return articulo;
	}





	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}





	public Integer getCantidad() {
		return cantidad;
	}





	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}




	
}
