package edu.uade.apdzpoc.entidades;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="MovimientosStock")
@DiscriminatorColumn(name="TipoMovimiento", discriminatorType=DiscriminatorType.STRING)
public abstract class MovimientoEntity {

	@Id
	@Column(name="IdMStock")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer idMovimiento;
	
	@Column(name="Fecha")
	@Type(type="date")
	protected Date fecha;
	
	@OneToOne
	@JoinColumn(name="CodigoBarra")
	protected ArticuloEntity articulo;
	
	@Column(name="Cantidad")
	protected int cantidad;

	
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





	public int getCantidad() {
		return cantidad;
	}





	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}




	
}
