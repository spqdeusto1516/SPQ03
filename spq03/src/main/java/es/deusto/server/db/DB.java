package es.deusto.server.db;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;



import es.deusto.server.db.dao.*;
import es.deusto.server.db.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DB implements IDB {

    private static final long serialVersionUID = 1L;
    private int cont = 0;
    IDAO dao;
    final static Logger logger = LoggerFactory.getLogger(DB.class);

    public DB(){
        super();
        dao = new DAO();
    }

    public DB(IDAO idao) {
        super();
        dao = idao;
    }

    @Override
    public List<Product> getAllProd() {
        List <Product> prodList = new ArrayList<>();
        try {
            prodList = dao.getAllProd();
        }catch (Exception e){
            logger.error("Exception launched: " + e.getMessage());
        }
        return prodList;
    }

    @Override
    public Product showProd(String name){
        Product p = null;
        try {
            p = dao.retrieveProdSearch(name);
        }catch(Exception e){
            logger.error("Exception launched: " + e.getMessage());
        }
        return p;
    }

    @Override
    public User showUser(String login){
        User u = null;
        try {
             u = dao.retrieveUser(login);
        }catch (Exception e){
            logger.error("Exception launched: " + e.getMessage());
        }
        return u;
    }

    @Override
    public boolean insertProd(Product p){
        Product prod = null;
        boolean ret=true;

        try {
            prod = dao.retrieveProdSearch(p.getName());
        } catch (Exception  e) {
            logger.error("Exception launched: " + e.getMessage());
            ret=false;
        }

        if (prod != null) {
            prod.setOwner(p.dnGetuser());

            dao.updateProd(prod);

        } else {
            dao.storeProd(p);
        }
        return ret;
    }

    @Override
    public boolean insertUser(User u) {
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
    public boolean insertMoney(Money m) {
        Money money = null;
        boolean ret=true;

        try {
            money = dao.retrieveMoney(m.getAmount());
        } catch (Exception  e) {
            logger.error("Exception launched: " + e.getMessage());
            ret=false;
        }

        if (money != null) {
            money.setUserSending(m.getSender());
            dao.updateMoney(money);
        } else {
            dao.storeMoney(m);
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
            logger.error("Exception launched: " + e.getMessage());
            ret=false;
        }

        if(uB == null || uS == null || p == null){
        }else{
            uB.removeMoney(amount);
            uS.addMoney(amount);
            dao.updateUser(uB);
            p.setOwner(uB);
            dao.updateUser(uS);
            dao.updateProd(p);
        }
        return ret;
    }
}
