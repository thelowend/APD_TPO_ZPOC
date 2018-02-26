package edu.uade.apdzpoc.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name="Lotes")
public class LoteEntity {
	@Id
	@Column(name="NroLote")
	private Integer nroLote;
	
	@Column(name="Vencimiento")
	@Type(type="date")
	private Date vencimiento;
	
/*	
	@OneToOne
	@JoinColumn (name="CodigoBarra")
	private ArticuloEntity articulo;
	*/
	
	@OneToMany
	@JoinColumn(name="IdUbicacion")
	private List<UbicacionEntity> ubicaciones;
	
	
	
	public LoteEntity() {
		
	}
	public Integer getNroLote() {
		return nroLote;
	}

	public void setNroLote(Integer nroLote) {
		this.nroLote = nroLote;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

/*
	public ArticuloEntity getArticulo() {
		return articulo;
	}



	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}

*/

	public List<UbicacionEntity> getUbicaciones() {
		return ubicaciones;
	}



	public void setUbicaciones(List<UbicacionEntity> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	
	
	

}
