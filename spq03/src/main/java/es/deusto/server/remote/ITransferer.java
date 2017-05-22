package es.deusto.server.remote;

import es.deusto.server.db.data.Product;
import es.deusto.server.db.data.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ITransferer extends Remote {

	/**
	 * This method allows the user with loginS to send an amount to the user with loginR
	 * @param loginR the name of the user that receives the money
	 * @param amount the number of money sent
	 * @param loginS the name of the user that sends the money
	 * @return whether the money has been sent or not
	 * @throws RemoteException fails if there is a connection error
	 */
	boolean sendMoney(String loginR, int amount, String loginS) throws RemoteException;
	/**
	 * This method returns the list of all users in the DB
	 * @return the list of users in the DB
	 * @throws RemoteException fails if there is a connection error
	 */
	ArrayList<User> getAllUser() throws RemoteException;
	
	/**
	 * This method registers a user and returns if the transaction was done rightfully
	 * @param u The user to register in the DB
	 * @return whether the user has been registered or not
	 * @throws RemoteException fails if there is a connection error
	 */
	boolean registerUser(User u) throws RemoteException;
	
	/**
	 * This method returns a user based on their username (login)
	 * @param login The name of the user that wants to be retreived
	 * @return The user with the login name "login"
	 * @throws RemoteException fails if there is a connection error
	 */
	User getUser(String login) throws RemoteException;
	
	/**
	 * This method retrieves all the products inside the DB
	 * @return A list of all the products inside the DB
	 * @throws RemoteException fails if there is a connection error
	 */
	ArrayList<Product> getAllProd() throws RemoteException;
	
	/**
	 * This method returns a Product based on its name
	 * @param name The PK of the Product and the way to search it inside the DB
	 * @return An object Product that meets with the requirements
	 * @throws RemoteException fails if there is a connection error
	 */
	Product searchProd(String name) throws RemoteException;
	
	/**
	 * This method allows the user with loginB to buy a product p to the user with loginS for the amount of money specified
	 * @param loginB The PK that identifies the buyer
	 * @param p The Product to be bought
	 * @param amount The quantity to be spent on the transaction
	 * @param loginS The PK that identifies the seller
	 * @return Whether the product has been bought or not
	 * @throws RemoteException fails if there is a connection error
	 */
	boolean buyProd(String loginB, Product p, int amount, String loginS) throws RemoteException;
	
	/**
	 * This method registers a product and returns if the transaction was done rightfully
	 * @param p The product to register in the DB
	 * @return whether the product has been registered or not
	 * @throws RemoteException fails if there is a connection error
	 */
	boolean registerProd(Product p) throws RemoteException;
}
