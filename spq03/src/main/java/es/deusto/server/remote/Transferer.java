package es.deusto.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import es.deusto.server.db.DB;
import es.deusto.server.db.IDB;
import es.deusto.server.db.dao.*;
import es.deusto.server.db.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transferer extends UnicastRemoteObject implements ITransferer{

    final static Logger logger = LoggerFactory.getLogger(Transferer.class);
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

	@Override
	public boolean registerUser(User u) {
        User user = null;
        boolean ret=true;
        try {
            user = dao.retrieveUser(u.getLogin());
        } catch (Exception  e) {
			logger.error("Exception launched: " + e.getMessage());
            ret=false;
        }

        if (user != null) {
            user.setPassword(u.getPassword());
            dao.updateUser(user);
        } else {
            dao.storeUser(u);
        }
        return ret;
	}

	@Override
    public User getUser(String login) throws RemoteException{
	    User u = null;
        logger.info("Checking whether the user exists...");
        try {
            u = dao.retrieveUser(login);
        } catch (Exception  e) {
            logger.error("Exception launched: " + e.getMessage());
        }
        if (u != null) {
            return u;
        } else {
            logger.warn("There is not a user with such login");
            return u;
        }
    }

    @Override
    public ArrayList<Product> getAllProd() throws RemoteException{
    	List<Product> listProd = new ArrayList<>();
	    int cont = 0;
	    try{
	        listProd = dao.getAllProd();
        }catch (Exception e){
	        logger.error("Exception launched: " + e.getMessage());
        }
	    return (ArrayList)listProd;
    }
	
    @Override
    public Product searchProd(String name) throws RemoteException{
	    Product prod=null;
	    List<Product> listProd = new ArrayList<>();
	    int cont = 0;
	    try{
	        listProd = dao.getAllProd();
	        for(Product p: listProd){
	            if(p.getName().equals(name) && cont < 1){
	                cont ++;
	                prod = p;
                }
            }
        }catch (Exception e){
	        logger.error("Exception launched: " + e.getMessage());
        }
	    return prod;
    }

    @Override
    public boolean registerProd(Product p) throws RemoteException{
        boolean ret=true;
        Product prod = null;
        try {
            prod = dao.retrieveProdSearch(p.getName());
        }catch(Exception  e){
            logger.error("Exception launched: " + e.getMessage());
            ret=false;
        }
        if (prod != null ) {
            p.setOwner(prod.dnGetuser());
            p.setName(prod.getName());
            dao.updateProd(prod);
        }else{
            p.setOwner(prod.dnGetuser());
            p.setName(prod.getName());
            dao.storeProd(prod);
        }
        return ret;
    }

    @Override
    public boolean buyProd(String loginB, Product p, int amount, String loginS) throws RemoteException{
        boolean ret = true;
        Product prod = null;
        User sender = null;
        User buyer = null;
        try {
            prod = dao.retrieveProdSearch(p.getName());
            sender = dao.retrieveUser(loginS);
            buyer = dao.retrieveUser(loginB);
        }catch(Exception e){
          logger.error("Exception launched: " + e.getMessage());
            ret=false;
        }
        if(prod == null || sender == null || buyer == null){
        }else if(prod != null && sender != null && buyer != null){
            buyer.removeMoney(amount);
            sender.addMoney(amount);
            prod.setOwner(buyer);
            dao.updateProd(prod);
            dao.updateUser(sender);
            dao.updateUser(buyer);
        }
        return ret;
    }

	public boolean sendMoney(String loginR, int amount, String loginS) throws RemoteException {
            boolean ret = true;
			logger.info("Retrieving the user: '" + loginR +"'");
			User userR = null;
			User userS = null;
			try {
				userR = dao.retrieveUser(loginR);
				userS = dao.retrieveUser(loginS);
			} catch (Exception  e) {
				logger.error("Exception launched: " + e.getMessage());
				ret = false;
			}
			
			logger.info("Users retrieved: " + userR + userS);
			if (userR != null)  {
				userR.addMoney(amount);
				userS.removeMoney(amount);
				dao.updateUser(userR);
				dao.updateUser(userS);
				cont++;
				logger.info(" * Client number: " + cont);
			}
			else {
				logger.error("Login details supplied for message delivery are not correct");
				throw new RemoteException("Login details supplied for message delivery are not correct");
			}
			return ret;
	}
}
