package es.deusto.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.deusto.server.db.dao.*;
import es.deusto.server.db.data.*;

public class Transferer extends UnicastRemoteObject implements ITransferer{

	private static final long serialVersionUID = 1L;
	private int cont = 0;
	IDAO dao;
	
	public Transferer() throws RemoteException {
		super();
		dao = new DAO();

	}

	public Transferer(IDAO udao) throws RemoteException {
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
	

	public void sendMoney(String loginR, int amount, String loginS) throws RemoteException {

			System.out.println("Retrieving the user: '" + loginR +"'");
			User userR = null;
			User userS = null;
			try {
				userR = dao.retrieveUser(loginR);
				userS = dao.retrieveUser(loginS);
			} catch (Exception  e) {
				System.out.println("Exception launched: " + e.getMessage());
			}
			
			System.out.println("Users retrieved: " + userR + userS);
			if (userR != null)  {
				userR.addMoney(amount);
				userS.removeMoney(amount);
				dao.updateUser(userR);
				dao.updateUser(userS);
				cont++;
				System.out.println(" * Client number: " + cont);
			}
			else {
				System.out.println("Login details supplied for message delivery are not correct");
				throw new RemoteException("Login details supplied for message delivery are not correct");
			} 
	}

}
