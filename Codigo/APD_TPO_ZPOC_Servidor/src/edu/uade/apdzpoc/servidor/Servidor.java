package edu.uade.apdzpoc.servidor;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import edu.uade.apdzpoc.interfaces.InterfazRemota;
import edu.uade.apdzpoc.remoto.ObjetoRemoto;

public class Servidor {

	public static void main(String[] args) {
		
		System.setProperty("java.security.policy", "java.policy"); 
		InterfazRemota objetoRemoto;
		
			try {
				objetoRemoto = new ObjetoRemoto();
				LocateRegistry.createRegistry(1099);
				Naming.rebind("//localhost/Remoto", objetoRemoto);
				System.out.println("Fijado en //localhost/Remoto");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
		
		
	}

}
