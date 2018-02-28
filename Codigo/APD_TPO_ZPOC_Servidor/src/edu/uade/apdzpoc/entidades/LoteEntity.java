package edu.uade.apdzpoc.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Lotes")
public class LoteEntity {
	
	@Id
	@Column(name = "IdInterno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idInterno;
	
    @Column(name = "NroLote")
    private Integer nroLote;

    @Column(name = "Vencimiento")
    @Type(type = "date")
    private Date vencimiento;

    @ManyToOne
    @JoinColumn(name = "CodigoBarraArticulo")
    private ArticuloEntity articulo;

    @OneToMany //(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "NroLote")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UbicacionEntity> ubicaciones;


    public LoteEntity() {

    }
    
    public Integer getIdInterno() {
    	return idInterno;
    }
    
    public void setIdInterno(Integer idInterno) {
    	this.idInterno = idInterno;
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
