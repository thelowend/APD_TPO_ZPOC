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
				Naming.rebind("//127.0.0.1/Remoto", objetoRemoto);
				System.out.println("Fijado en //127.0.0.1/Remoto");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
		
		
	}

}
