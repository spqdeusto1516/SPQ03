package es.deusto.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.deusto.dao.IUserDAO;
import es.deusto.dao.UserDAO;
import es.deusto.data.Money;
import es.deusto.data.User;

public class Transferer extends UnicastRemoteObject implements ITransferer{

	private static final long serialVersionUID = 1L;
	private int cont = 0;
	IUserDAO dao;
	
	public Transferer() throws RemoteException {
		super();
		dao = new UserDAO();
	
	}
	public Transferer(IUserDAO udao) throws RemoteException {
		super();
		dao = udao;
	
	}
	
	public void registerUser(String login, String password) {
			
			System.out.println("Checking whether the user already exits or not: '" + login +"'");
			User user = null;
			try {
				user = dao.retrieveUser(login);
			} catch (Exception  e) {
				System.out.println("Exception launched: " + e.getMessage());
			}
			
			if (user != null) {
				System.out.println("The user exists. So, setting new password for User: " + login);
				user.setPassword(password);
				System.out.println("Password set for User: " + login);
				dao.updateUser(user);
			} else {
				System.out.println("Creating user: " + login);
				user = new User(login, password);
				dao.storeUser(user);				 
				System.out.println("User created: " + login);
			}
	}
	

	public String sayMoney(String login, String password, int amount) throws RemoteException {

			System.out.println("Retrieving the user: '" + login +"'");
			User user = null;
			try {
				user = dao.retrieveUser(login);
			} catch (Exception  e) {
				System.out.println("Exception launched: " + e.getMessage());
			}
			
			System.out.println("User retrieved: " + user);
			if (user != null)  {
				Money m1 = new Money(amount, user);
				m1.setUserSending(user);
				user.addMoney(amount);
				dao.updateUser(user);
				cont++;
				System.out.println(" * Client number: " + cont);
				return "The amount of money sent was: " +  amount;
			}
			else {
				System.out.println("Login details supplied for message delivery are not correct");
				throw new RemoteException("Login details supplied for message delivery are not correct");
			} 
		}

}
