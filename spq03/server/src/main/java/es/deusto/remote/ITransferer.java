package es.deusto.remote;

import es.deusto.data.UserDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITransferer extends Remote {
	
	String sayMoney(String login, String password, int amount) throws RemoteException;
	void registerUser(String login, String password) throws RemoteException;

}
