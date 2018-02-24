package edu.uade.apdzpoc.dao;

import edu.uade.apdzpoc.entidades.LoteEntity;
import edu.uade.apdzpoc.negocio.Lote;

public class LoteDAO {

private static LoteDAO instancia;
	
	private LoteDAO() {}
	
	public static LoteDAO getInstancia(){
		if(instancia == null)
			instancia = new LoteDAO();
		return instancia;
	}

	public LoteEntity toEntity(Lote l) {
		// TODO Auto-generated method stub
		return null;
	}

	public Lote toNegocio(LoteEntity l) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
