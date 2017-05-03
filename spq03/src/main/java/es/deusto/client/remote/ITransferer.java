package es.deusto.client.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import es.deusto.server.db.data.Product;
import es.deusto.server.db.data.User;

public interface ITransferer extends Remote {

	void sendMoney(String loginR, int amount, String loginS) throws RemoteException;
	void registerUser(String login, String password) throws RemoteException;
	User getUser(String login) throws RemoteException;
	List<Product> searchProd(String name) throws RemoteException;
}
