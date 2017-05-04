package es.deusto.server.db;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;


import es.deusto.server.db.dao.*;
import es.deusto.server.db.data.*;

public class DB implements IDB {

    private static final long serialVersionUID = 1L;
    private int cont = 0;
    IDAO dao;
  //  final Logger logger = LoggerFactory.getLogger(DB.class);

    public DB(){
        super();
        dao = new DAO();
    }

    public DB(IDAO idao) {
        super();
        dao = idao;
    }

    @Override
    public boolean insertUser(User u) {
        User user = null;
        boolean ret=true;

        try {
            user = dao.retrieveUser(u.getLogin());
        } catch (Exception  e) {
            //logger.error("Exception launched: " + e.getMessage());
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
    public List<Product> getAllProd() {
        List <Product> prodList = new ArrayList<>();
        try {
            prodList = dao.getAllProd();
        }catch (Exception e){
            //logger.error("Exception launched: " + e.getMessage());
        }
        return prodList;
    }

    @Override
    public Product showProd(String name){
        Product p = dao.retrieveProdSearch(name);
        return p;
    }

    @Override
    public User showUser(String login){
        User u = dao.retrieveUser(login);
        return u;
    }

    @Override
    public boolean insertProd(Product p){
        Product prod = null;
        boolean ret=true;

        try {
            prod = dao.retrieveProdSearch(p.getName());
        } catch (Exception  e) {
            //logger.error("Exception launched: " + e.getMessage());
            ret=false;
        }

        if (prod != null) {
            prod.setOwner(p.getOwner());

            dao.updateProd(prod);

        } else {
            dao.storeProd(p);
        }
        return ret;
    }

    @Override
    public  boolean buyProd(String loginB, String loginS, String name, int amount) {
        User uB = null;
        User uS = null;
        Product p = null;
        boolean ret = true;
        try{
            uB = dao.retrieveUser(loginB);
            uS = dao.retrieveUser(loginS);
            p = dao.retrieveProdSearch(name);
        }catch(Exception e){
            //logger.error("Exception launched: " + e.getMessage());
            ret=false;
        }

        if(uB == null || uS == null || p == null){
        }else{
            uB.removeMoney(amount);
            uS.addMoney(amount);
            p.setOwner(uB);
            dao.updateUser(uB);
            dao.updateUser(uS);
            dao.updateProd(p);
        }
        return ret;
    }
}

