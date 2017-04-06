package es.deusto.ingenieria.sd.server.gateway;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Igatewayfacebook {

	public boolean loginCorrecto(String nick, String contrasenia);
	
	
}
