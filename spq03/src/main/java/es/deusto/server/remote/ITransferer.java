package es.deusto.server.remote;

import es.deusto.server.db.data.Product;
import es.deusto.server.db.data.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ITransferer extends Remote {

    boolean registerUser(User u) throws RemoteException;
    User getUser(String login) throws RemoteException;

	boolean registerProd(Product p) throws RemoteException;
	Product searchProd(String name) throws RemoteException;
	boolean buyProd(String loginB, Product p, int amount, String loginS) throws RemoteException;
}
