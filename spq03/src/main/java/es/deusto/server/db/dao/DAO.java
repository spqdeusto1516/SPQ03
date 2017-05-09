package es.deusto.server.db.dao;
import javax.jdo.*;

import java.util.ArrayList;
import java.util.List;

import es.deusto.server.db.data.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAO implements IDAO {

    private PersistenceManagerFactory pmf;
    final static Logger logger = LoggerFactory.getLogger(DAO.class);
    public DAO(){
        pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
    }

    @Override
    public boolean storeUser(User u) {

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        boolean ret=true;
        try {
            tx.begin();

            pm.makePersistent(u);
            tx.commit();
        } catch (Exception ex) {
		    	logger.error("   $ Error storing an object User: " + ex.getMessage());
            ret=false;

        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
        return ret;
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
			logger.error("User does not exist: " + jonfe.getMessage());
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
    public boolean updateUser(User u) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        boolean r =true;
        try {
            tx.begin();
            pm.makePersistent(u);
            tx.commit();
        } catch (Exception ex) {
	    	   	logger.error("Error updating a user: " + ex.getMessage());
            r=false;
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
        return r;
    }


    @Override
    public	boolean storeProd(Product p){
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        boolean r=true;
        try {
            tx.begin();

            pm.makePersistent(p);
            tx.commit();
        } catch (Exception ex) {
		    	 	logger.error("   $ Error storing an object Prod: " + ex.getMessage());
            r=false;
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
        return r;
    }

    @Override
    public List<Product> getAllProd() {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        pm.getFetchPlan().setMaxFetchDepth(3);

        List<Product> products=new ArrayList<>();
        try {

            tx.begin();
            Extent<Product> extentP = pm.getExtent(Product.class);
            for (Product p : extentP) {
                products.add(p);
            }
            tx.commit();

        } catch (Exception ex) {
        	   logger.error("# Error getting Extent getAllGames: " + ex.getMessage());
        } finally {

            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();

        }
        return products;
    }

    @Override
    public Product retrieveProdSearch(String name){
        Product prod = null;
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(3);
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            prod = pm.getObjectById(Product.class, name);
            logger.info("Product in dao" + prod.getName());
            tx.commit();
        } catch (javax.jdo.JDOObjectNotFoundException jonfe)
        {
            logger.error("Product does not exist: " + jonfe.getMessage());
        }

        finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
        logger.info("The final return is: " + prod.getName());
        return prod;
    }

    @Override
    public	boolean updateProd(Product p){
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        boolean r=true;
        try {
            tx.begin();
            pm.makePersistent(p);
            tx.commit();
        } catch (Exception ex) {
	    	   	logger.error("Error updating a game: " + ex.getMessage());
            r=false;
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
        return r;
    }

    @Override
    public boolean storeMoney(Money m) {

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        boolean ret=true;
        try {
            tx.begin();
            pm.makePersistent(m);
            tx.commit();
        } catch (Exception ex) {
		    logger.error("   $ Error storing an object Money: " + ex.getMessage());
            ret=false;

        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
        return ret;
    }

    @Override
    public Money retrieveMoney(int amount) {
        Money money = null;
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(2);
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            money = pm.getObjectById(Money.class, amount);
            tx.commit();
        } catch (javax.jdo.JDOObjectNotFoundException jonfe) {
			logger.error("Purchase does not exist: " + jonfe.getMessage());
        }

        finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }

        return money;
    }

    @Override
    public	boolean updateMoney(Money m){
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        boolean r=true;
        try {
            tx.begin();
            pm.makePersistent(m);
            tx.commit();
        } catch (Exception ex) {
	    	   	logger.error("Error updating a game: " + ex.getMessage());
            r=false;
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
        return r;
    }
}