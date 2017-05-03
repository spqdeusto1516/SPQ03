package es.deusto.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITransferer extends Remote {
	
	void sendMoney(String loginR, int amount, String loginS) throws RemoteException;
	void registerUser(String login, String password) throws RemoteException;

}
