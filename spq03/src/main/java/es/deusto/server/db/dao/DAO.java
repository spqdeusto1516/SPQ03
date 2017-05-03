package es.deusto.dao;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.data.Product;
import es.deusto.data.User;

public class DAO implements IDAO {

    private PersistenceManagerFactory pmf;

    public UserDAO(){
        pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
    }

    @Override
    public void storeUser(User u) {

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            System.out.println("   * Storing a user: " + u.getLogin());
            pm.makePersistent(u);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("   $ Error storing an object: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
    }

    @Override
    public void storeProduct (Product prod) {

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            System.out.println("   * Storing a Product: " + prod.getName());
            pm.makePersistent(prod);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("   $ Error storing an object: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
    }

    @Override
    public User retrieveUser(String login) {
        User user = null;
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(2);
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            user = pm.getObjectById(User.class, login);
            tx.commit();
        } catch (javax.jdo.JDOObjectNotFoundException jonfe)
        {
            System.out.println("User does not exist: " + jonfe.getMessage());
        }

        finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }

        return user;
    }

    @Override
    public void updateUser(User u) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            pm.makePersistent(u);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("Error updating a user: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }

    }

    @Override
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

    @Override
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

    @Override
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
