package es.deusto.client.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import es.deusto.data.UserDTO;

public interface ITransferer extends Remote {
	
	String sayMoney(String login, String password, int amount) throws RemoteException;
	void registerUser(String login, String password) throws RemoteException;
	UserDTO getUser(String login) throws RemoteException;

}
