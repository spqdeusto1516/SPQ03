package es.deusto.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import es.deusto.server.db.DB;
import es.deusto.server.db.IDB;
import es.deusto.server.db.data.Money;
import es.deusto.server.db.data.Product;
import es.deusto.server.db.data.User;
import es.deusto.server.remote.*;


public class Server {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("How to invoke: java [policy] [codebase] Server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			
			ITransferer objServer = new Transferer();
			Naming.rebind(name, objServer);

			System.out.println("Money Transfering Server '" + name + "' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
            @SuppressWarnings("unused")
			String line  = stdin.readLine();
		} catch (Exception e) {
			System.err.println("Transferer exception: " + e.getMessage());
			e.printStackTrace();
		}
	}




	private static void initialStuff(){

        User u1 = new User("jocor", "kun");
        User u2 = new User("ainhoa", "qwerty");
        User u3 = new User("mikel", "ruiz");

        Product p0 = new Product(u1, "Prod 0", "pretty");
        Product p1 =new Product(u1, "Prod 1", "useful");
        Product p2 =new Product(u2, "Prod 2", "red");
        Product p3= new Product(u2, "Prod 3", "irritating");
        Product p4 =new Product(u2, "Prod 4", "beautiful");
        Product p5 =new Product(u3, "Prod 5", "big");
        Product p6 =new Product(u3, "Prod 6", "small");
        Product p7= new Product(u3, "Prod 7", "nice");

        Money m0 = new Money(1, u2, p0);
        Money m1 = new Money(123, u3, p1);
        Money m2 = new Money(555, u1, p2);
        Money m3 = new Money(777, u1, p3);
        Money m4 = new Money(890, u1, p5);

        IDB db = new DB();

        db.insertUser(u1);
        db.insertUser(u2);
        db.insertUser(u3);

        db.insertProd(p0);
        db.insertProd(p1);
        db.insertProd(p2);
        db.insertProd(p3);
        db.insertProd(p4);
        db.insertProd(p5);
        db.insertProd(p6);
        db.insertProd(p7);

        db.insertMoney(m0);
        db.insertMoney(m1);
        db.insertMoney(m2);
        db.insertMoney(m3);
        db.insertMoney(m4);

        db.buyProd(u2.getLogin(), u1.getLogin(), "Prod 0", 1);
        db.buyProd(u3.getLogin(), u1.getLogin(), "Prod 1", 123);
        db.buyProd(u1.getLogin(), u2.getLogin(), "Prod 2", 555);
        db.buyProd(u1.getLogin(), u2.getLogin(), "Prod 3", 777);
        db.buyProd(u1.getLogin(), u3.getLogin(), "Prod 5", 890);

    }
}
