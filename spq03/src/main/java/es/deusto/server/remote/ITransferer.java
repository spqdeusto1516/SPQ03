package es.deusto.server.remote;

import es.deusto.server.db.data.Product;
import es.deusto.server.db.data.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ITransferer extends Remote {

	boolean sendMoney(String loginR, int amount, String loginS) throws RemoteException;
	boolean registerUser(User u) throws RemoteException;
	User getUser(String login) throws RemoteException;
	ArrayList<Product> getAllProd() throws RemoteException;
	Product searchProd(String name) throws RemoteException;
	boolean buyProd(String loginB, Product p, int amount, String loginS) throws RemoteException;
	boolean registerProd(Product p) throws RemoteException;
}
