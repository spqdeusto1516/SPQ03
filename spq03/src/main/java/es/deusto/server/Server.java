package es.deusto.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import es.deusto.server.db.DB;
import es.deusto.server.db.IDB;
import es.deusto.server.db.data.Money;
import es.deusto.server.db.data.Product;
import es.deusto.server.db.data.User;
import es.deusto.server.remote.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Server {

    final static Logger logger = LoggerFactory.getLogger(Server.class);
	public static void main(String[] args) {
		if (args.length != 3) {
			logger.info("How to invoke: java [policy] [codebase] Server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			ITransferer objServer = new Transferer();
			Naming.rebind(name, objServer);
            initialStuff();
			logger.info("Money Transfering Server '" + name + "' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
            @SuppressWarnings("unused")
			String line  = stdin.readLine();
		} catch (Exception e) {
			logger.error("Transferer exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

    private static void initialStuff(){
        User u1 = new User("jocor", "kun");
        User u2 = new User("elle", "qwerty");
        User u3 = new User("pata", "tachan");

        u1.setAmount(3000000);
        u2.setAmount(4000000);
        u3.setAmount(5000000);

        Product p0 = new Product("Prod 0", "pretty");
        Product p1 =new Product("Prod 1", "useful");
        Product p2 =new Product("Prod 2", "red");
        Product p3= new Product("Prod 3", "irritating");
        Product p4 =new Product("Prod 4", "beautiful");
        Product p5 =new Product("Prod 5", "big");
        Product p6 =new Product("Prod 6", "small");
        Product p7= new Product("Prod 7", "nice");

        Money m0 = new Money(1);
        Money m1 = new Money(123);
        Money m2 = new Money(555);
        Money m3 = new Money(777);
        Money m4 = new Money(890);
        Money m5 = new Money(891);

        IDB db = new DB();

        db.insertUser(u1);
        db.insertUser(u2);
        db.insertUser(u3);

        p0.setOwner(u1);
        p1.setOwner(u1);
        p2.setOwner(u2);
        p3.setOwner(u2);
        p4.setOwner(u2);
        p5.setOwner(u3);
        p6.setOwner(u3);
        p7.setOwner(u3);

        db.insertProd(p0);
        db.insertProd(p1);
        db.insertProd(p2);
        db.insertProd(p3);
        db.insertProd(p4);
        db.insertProd(p5);
        db.insertProd(p6);
        db.insertProd(p7);

        m0.setUserSending(u2);
        m0.setProduct(p0);
        m1.setUserSending(u3);
        m1.setProduct(p1);
        m2.setUserSending(u1);
        m2.setProduct(p2);
        m3.setUserSending(u3);
        m3.setProduct(p3);
        m4.setUserSending(u2);
        m4.setProduct(p5);
        m5.setUserSending(u1);
        m5.setProduct(p6);

        db.insertMoney(m0);
        db.insertMoney(m1);
        db.insertMoney(m2);
        db.insertMoney(m3);
        db.insertMoney(m4);
        db.insertMoney(m5);

        db.buyProd(u2.getLogin(), u1.getLogin(), "Prod 0", 1);
        db.buyProd(u3.getLogin(), u1.getLogin(), "Prod 1", 123);
        db.buyProd(u1.getLogin(), u2.getLogin(), "Prod 2", 555);
        db.buyProd(u1.getLogin(), u2.getLogin(), "Prod 3", 777);
        db.buyProd(u1.getLogin(), u3.getLogin(), "Prod 5", 890);

        db.showProd("Prod 0");
    }
}
