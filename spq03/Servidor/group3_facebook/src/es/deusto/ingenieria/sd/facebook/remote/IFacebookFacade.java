package es.deusto.ingenieria.sd.facebook.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFacebookFacade extends Remote{

	public boolean loginAutenticar(String nick, String contrasenia)throws RemoteException;
}
