package edu.uade.apdzpoc.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Lote")
public class LoteEntity {
	@Id
	@Column(name="Nro_Lote")
	private Integer nroLote;
	
	@Column(name="Vencimiento")
	private Date vencimiento;
	
	
	@ManyToOne
	@JoinColumn (name="Codigo_Barra")
	private ArticuloEntity articulo;
	
	
	@OneToMany
	@JoinColum(name="Id_Ubicacion")
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



	public ArticuloEntity getArticulo() {
		return articulo;
	}



	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}



	public List<UbicacionEntity> getUbicaciones() {
		return ubicaciones;
	}



	public void setUbicaciones(List<UbicacionEntity> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	
	
	

}
