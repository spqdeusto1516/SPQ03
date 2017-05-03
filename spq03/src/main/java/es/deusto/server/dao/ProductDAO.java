package es.deusto.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.data.Money;
import es.deusto.data.User;
import es.deusto.data.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void buyProd(Product p, Money m, String name){
        List<Product> listSearch = searchProd(name);
        if(listSearch.equals(null)){
            //No search, cannot buy a prod that doesn't exist
        }else{
            m.getSender().removeMoney(m.getAmount());
            p.getOwner().addMoney(m.getAmount());
            p.setOwner(m.getSender());
        }

    }


    public List<Product> getAllProd(){
        List<Product> allProd = null;
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(2);
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            allProd = pm.getObjectById(Product.class, name);
            tx.commit();
        } catch (javax.jdo.JDOObjectNotFoundException jonfe)
        {
            System.out.println("There are no Produccts in DB: " + jonfe.getMessage());
        }

        finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
        for(Product p: allProd){
            System.out.println(p.toStringShort() + ", " + p.getOwner().toString());
        }
        return allProd;
    }


    //Searches Products by name
    public List<Product> searchProd(String name){
        List<Product> allProd = getAllProd();
        List<Product> listProd = new ArrayList<Product>();

        for(Product p: allProd){
            if(p.getName().equals(name)){
                //Insert into the list
                listProd.add(p);
            }
        }
        return listProd;
    }

}
