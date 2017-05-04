package es.deusto.client;

import java.rmi.RMISecurityManager;
import java.util.List;
import java.util.Scanner;

import es.deusto.server.remote.ITransferer;
import es.deusto.server.db.data.Product;
import es.deusto.server.db.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class Client {
    final static Logger logger = LoggerFactory.getLogger(Client.class);
    public static void main(String[] args) {
        if (args.length != 3) {
            logger.info("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
            System.exit(0);
        }

//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new RMISecurityManager());
//        }

        try {
            Scanner entradaEscaner = null;
            String prodName = null;
            String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
            ITransferer objHello = (ITransferer) java.rmi.Naming.lookup(name);
            // Register to be allowed to send messages
            logger.info("Register a user for the first time: dipina");
            objHello.registerUser(new User("jocor", "ral"));
            logger.info("Change the password as the user is already registered: kun");
            objHello.registerUser(new User("jocor", "kun"));
            int dec1 = 0;
            User sender = objHello.getUser("jocor");
            do {
                dec1 = 0;
                logger.info("Insert whether you want to send some Money (1), you want to look for a Product (2) or you" +
                        "want to insert a new Product (3)");
                entradaEscaner = new Scanner(System.in);
                dec1 = Integer.parseInt(entradaEscaner.nextLine());
            } while (dec1 != 1 && dec1 != 2 && dec1 != 3);
            switch (dec1) {
                case (1):
                    int amount = 0;
                    do {
                        amount = 0;
                        logger.info("Set the amount of money");
                        entradaEscaner = new Scanner(System.in);
                        amount = Integer.parseInt(entradaEscaner.nextLine());
                    } while (amount <= sender.getMoney());

                    logger.info("Now insert who you want to send it to");
                    entradaEscaner = new Scanner(System.in);
                    User receiver = objHello.getUser(entradaEscaner.nextLine());
                    if (!receiver.equals(null)) {
                        logger.info("Sending money...");
                        objHello.sendMoney(receiver.getLogin(), amount, sender.getLogin());
                    }
                    break;
                case (2):
                    logger.info("What's the name of the Product you are looking for?");
                    entradaEscaner = new Scanner(System.in);
                    prodName = entradaEscaner.nextLine();
                    Product search = objHello.searchProd(prodName);
                    if (search.equals(null)) {
                        logger.info("Error! No Product with such name");
                    } else {
                        logger.info(search.toStringShort() + search.getOwner().toString());
                    }
                    break;

                case (3):
                    Product p = null;
                    logger.info("Insert the name of the product");
                    entradaEscaner = new Scanner(System.in);
                    prodName = entradaEscaner.nextLine();
                    logger.info("Insert the characteristics");
                    entradaEscaner = new Scanner(System.in);
                    String characteristics = entradaEscaner.nextLine();
                    p = new Product(sender, prodName, characteristics);
                    objHello.registerProd(p);
                    break;
            }

        } catch (Exception e) {
            logger.debug("RMI Example exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}