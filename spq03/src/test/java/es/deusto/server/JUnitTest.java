package es.deusto.server;
import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.PropertyPermission;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

/*import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;*/

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import es.deusto.client.Client;
import es.deusto.server.Server;
import es.deusto.server.db.DB;
import es.deusto.server.db.IDB;
import es.deusto.server.db.dao.IDAO;
import es.deusto.server.db.data.Money;
import es.deusto.server.db.data.Product;
import es.deusto.server.db.data.User;
import es.deusto.server.remote.ITransferer;
import es.deusto.server.remote.Transferer;
import junit.framework.JUnit4TestAdapter;

public class JUnitTest {

	User u;
	String name;
	Product p;
	Server server;
	DB dataBase;
	java.util.List<Product> products = new ArrayList<Product>();
	IDAO dao;
	String nameCon = "//127.0.0.1:1099/MessengerRMIDAO";
	Remote remote;
	es.deusto.server.remote.ITransferer objHello;
	
	final static org.slf4j.Logger logger = LoggerFactory.getLogger(JUnitTest.class);

	private static Thread rmiRegistryThread = null;
	private static Thread rmiServerThread = null;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(JUnitTest.class);
	}
	
	//@BeforeClass 
	static public void setUp() {
		// Launch the RMI registry
		class RMIRegistryRunnable implements Runnable {

			public void run() {
				try {
					java.rmi.registry.LocateRegistry.createRegistry(1099);
					System.out.println("BeforeClass: RMI registry ready.");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
					
					String name = "//127.0.0.1:1099/MessengerRMIDAO";
					System.out.println("11111BeforeClass - instanciando server " + name);

					es.deusto.server.remote.ITransferer s = new es.deusto.server.remote.Transferer();
					System.out.println("BeforeClass - instanciado server " + name);
					System.out.println("222222BeforeClass - instanciando server " + name);
					
					
					Naming.rebind(name,s);
					System.out.println("33333BeforeClass - instanciando server " + name);

				} catch (RemoteException re) {
					re.printStackTrace();
				} catch (Exception e) {
					System.out.println("Exception starting RMI registry:");
					e.printStackTrace();
				}
			}
		}

		rmiRegistryThread = new Thread(new RMIRegistryRunnable());
		rmiRegistryThread.start();

		class RMIServerRunnable implements Runnable {

			public void run() {
				System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

				if (System.getSecurityManager() == null) {
					System.setSecurityManager(new SecurityManager());
				}

				String name = "//127.0.0.1:1099/MessengerRMIDAO";
				
				System.out.println("BeforeClass - Setting the server ready TestServer name: " + name);

				try {
					System.out.println("BeforeClass - instanciando server " + name);

					es.deusto.server.remote.ITransferer s = new es.deusto.server.remote.Transferer();
					System.out.println("BeforeClass - instanciado server " + name);

					Naming.rebind(name,s);
					System.out.println("BeforeClass - hablado con rmi registry " + name);

					
					
					System.out.println("*******BeforeClass: RMI server registered.");
				} catch (RemoteException re) {
					System.out.println(" # Messenger RemoteException: " + re.getMessage());
					re.printStackTrace();
					System.exit(-1);
				} catch (MalformedURLException e) {
					System.out.println(" # Messenger MalformedURLException: " + e.getMessage());

					System.err.println(" # Messenger MalformedURLException: " + e.getMessage());
					e.printStackTrace();
					System.exit(-1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		//rmiServerThread = new Thread(new RMIServerRunnable());
		//rmiServerThread.start();
		//try {
			//Thread.sleep(4000);
		//} catch (InterruptedException ie) {
			//ie.printStackTrace();
		//}

	}
	
	//@Before
	public void setUpUser(){
		try {
			System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			String name = "//127.0.0.1:1099/MessengerRMIDAO";
			System.out.println("BeforeTest - Setting the client ready for calling TestServer name: " + name);
			remote = (Remote) java.rmi.Naming.lookup(name);
			System.out.println("***********");
		}
		catch (Exception re) {
			System.err.println(" # Messenger RemoteException: " + re.getMessage());
			System.exit(-1);
		}
	}
	
	/*@Before
	public void setUpDatabase() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
            Query q1 = pm.newQuery(User.class);
            long numberInstancesDeleted = q1.deletePersistentAll(1);
            System.out.println("Deleted " + numberInstancesDeleted + " user");
			Query q2 = pm.newQuery(Product.class);
			long numberInstancesDeleted2 = q2.deletePersistentAll();
			System.out.println("Deleted " + numberInstancesDeleted2 + " product");
            Query q3 = pm.newQuery(Money.class);
            long numberInstancesDeleted3 = q3.deletePersistentAll();
            System.out.println("Deleted " + numberInstancesDeleted3 + " money"); 
			tx.commit();
        } finally {
		    if (tx.isActive()) {
		    	tx.rollback();
			}
			pm.close();
		}
		Server server;
		try {
			server = new Server();
			((Server) server).createDatabase();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
	
	//@Before
	public void stablishConnection(){
		try {
			objHello = (es.deusto.server.remote.ITransferer) java.rmi.Naming.lookup(nameCon);
        } catch (NotBoundException ex) {
            Logger.getLogger(JUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(JUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(JUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	//@Test
	public void RegisterProductTest(){
		System.out.println("************ me he metido ");
		//try {
			//assertTrue(objHello.registerProd(new Product("Caja caramelos", "Caramelos de todos los sabores")));
			System.out.println("Test ejecutado");
		//} catch (RemoteException ex) {
        //    Logger.getLogger(JUnitTest.class.getName()).log(Level.SEVERE, null, ex);
		//}
	}
	
	//@Test
	public void testRegisterUser(){
		try {
			assertTrue(objHello.registerUser(new User("Itsazain", "123")));
        } catch (RemoteException ex) {
            Logger.getLogger(JUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	//@Test
	public void testGetUser(){
		try {
			if (null!=objHello.getUser("Itsazain")){
				assertTrue(true);
            } else
            	assertTrue(false);
		} catch (RemoteException ex) {
			Logger.getLogger(JUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	//@Test
	public void testSearchProduct(){
		try {
			if (null!=objHello.searchProd("Caja caramelos")) {
				assertTrue(true);
			} else
				assertTrue(false);
		} catch (RemoteException ex) {
			Logger.getLogger(JUnitTest.class.getName()).log(Level.SEVERE, null, ex);
		}    
	}
	
	//@Test
	public void testStoreUser(){
		try {
			System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			String name = "//127.0.0.1:1099/MessengerRMIDAO";
			System.out.println("BeforeTest - Setting the client ready for calling TestServer name: " + name);
			remote = (Remote) java.rmi.Naming.lookup(name);
		}
		catch (Exception re) {
			System.err.println(" # Messenger RemoteException: " + re.getMessage());
			re.printStackTrace();
			System.exit(-1);
		} 
	}
	
	//@Test
	public void testRetrieveUser(){
		/*boolean t = false;
		String a = null;
		try {
			a = remote.getLogin();
			logger.info(a);
		} catch (RemoteException e) {
			logger.error(" # RemoteException: " + e.getMessage());
			logger.trace(e.getMessage());
		}
		if(a!=null) {
			t = true;
		}
		assertTrue(t);*/
	}
	
	/*@Test
	
	public void testUpdateUser(){
		try {
			System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			String name = "//127.0.0.1:1099/MessengerRMIDAO";
			System.out.println("BeforeTest - Setting the client ready for calling TestServer name: " + name);
			remote = (Remote) java.rmi.Naming.lookup(name);
		}
		catch (Exception re) {
			System.err.println(" # Messenger RemoteException: " + re.getMessage());
			re.printStackTrace();
			System.exit(-1);
		} 
	}*/
	
	//@Test
	public void testStoreProduct() {
		
		dao.storeProd(p);
		assertEquals(1, dao.getAllProd().size());
		assertEquals(p.getName(),dao.getAllProd().get(0).getName());
	}
	
	@Test
	public void testGetAllProd(){

	}

	//@Test
	public void testUpdateProduct(){
		dao.updateProd(p);
		assertEquals(1, dao.getAllProd().size());
		assertEquals(p.getName(),dao.getAllProd().get(0).getName());
	}
	
	/*@Test
	public void testRegisterUser(){
		boolean a = true;
		try{
			logger.info("Test 1 - Register new user");
			remote.registerUser("login", "password");
		}
		catch (Exception re) {
			logger.error(" # Messenger RemoteException: " + re.getMessage());
			a =false;
		} 
		assertTrue( a );
	}*/

	@Test
	public void testBuyProduct(){

	}
	
	//@Test
	public void testUpdateMoney(){
		Money m = new Money(10);
		int amount = 10;
		dao.storeMoney(m);
		assertEquals(1, dao.retrieveMoney(amount));
		assertEquals(m.getAmount(),dao.retrieveMoney(0).getAmount());
	}
	
	//@Test
	public void testStoreMoney(){
		Money m = new Money(10);
		int amount = 10;
		dao.storeMoney(m);
		assertEquals(1, dao.retrieveMoney(amount));
		assertEquals(m.getAmount(),dao.retrieveMoney(0).getAmount());
	}

	//@Test
	public void testRetrieveMoney(){
		
	}

	//@Test
	public void testSendMoney(){

	}
	
	@Test
	public void testGetSender() {
       User u= new User("pepe", "pepito");
       assertEquals("pepe", u.getLogin());
       assertEquals("pepito", u.getPassword());
      
    }
	
	@Test
    public void testGetProd() {
        Product p= new Product("zapato", "negro");
        assertEquals("zapato",p.getName());
        assertEquals("negro", p.getCharacteristics());
    }
	
	@Test
	public void testGetAmount(){
		Money m= new Money(5);
		assertEquals(5, m.getAmount());
	}
	
	@Test
	public void testGetName() {
		 Product p= new Product("zapato", "negro");
	     assertEquals("zapato",p.getName());
    }
	
	@Test	
	public void testGetCharacteristics(){
		Product p= new Product("zapato", "negro");
        assertEquals("negro", p.getCharacteristics());
	}
	
	@Test
	public void testGetLogin() {
		User u= new User("pepe", "pepito");
	    assertEquals("pepe", u.getLogin());
	
	}
	
	@Test
	public void testGetPassword() {
		User u= new User("pepe", "pepito");
	    assertEquals("pepito", u.getPassword());
	}
	
	@Test
	public void testGetMoney() {
		 Money m= new Money(5);
		 assertEquals(5, m.getAmount());
	}

	//@AfterClass 
	static public void tearDown() {
		try	{
			rmiServerThread.join();
			rmiRegistryThread.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

	}
	
	
}