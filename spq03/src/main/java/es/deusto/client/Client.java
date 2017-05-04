package es.deusto.client;

import java.rmi.RMISecurityManager;
import java.util.List;
import java.util.Scanner;

import es.deusto.server.remote.ITransferer;
import es.deusto.server.db.data.Product;
import es.deusto.server.db.data.User;

@SuppressWarnings("deprecation")
public class Client {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
            System.exit(0);
        }

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            Scanner entradaEscaner = null;
            String prodName = null;
            String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
            ITransferer objHello = (ITransferer) java.rmi.Naming.lookup(name);
            // Register to be allowed to send messages
            System.out.println("Register a user for the first time: dipina");
            objHello.registerUser(new User("jocor", "ral"));
            System.out.println("Change the password as the user is already registered: kun");
            objHello.registerUser(new User("jocor", "kun"));
            int dec1 = 0;
            User sender = objHello.getUser("jocor");
            do {
                dec1 = 0;
                System.out.println("Insert whether you want to send some Money (1), you want to look for a Product (2) or you" +
                        "want to insert a new Product (3)");
                entradaEscaner = new Scanner(System.in);
                dec1 = Integer.parseInt(entradaEscaner.nextLine());
            } while (dec1 != 1 && dec1 != 2 && dec1 != 3);
            switch (dec1) {
                case (1):
                    int amount = 0;
                    do {
                        amount = 0;
                        System.out.println("Set the amount of money");
                        entradaEscaner = new Scanner(System.in);
                        amount = Integer.parseInt(entradaEscaner.nextLine());
                    } while (amount <= sender.getMoney());

                    System.out.println("Now insert who you want to send it to");
                    entradaEscaner = new Scanner(System.in);
                    User receiver = objHello.getUser(entradaEscaner.nextLine());
                    if (!receiver.equals(null)) {
                        System.out.println("Sending money...");
                        objHello.sendMoney(receiver.getLogin(), amount, sender.getLogin());
                    }
                    break;
                case (2):
                    System.out.println("What's the name of the Product you are looking for?");
                    entradaEscaner = new Scanner(System.in);
                    prodName = entradaEscaner.nextLine();
                    Product search = objHello.searchProd(prodName);
                    if (search.equals(null)) {
                        System.out.println("Error! No Product with such name");
                    } else {
                        System.out.println(search.toStringShort() + search.getOwner().toString());
                    }
                    break;

                case (3):
                    Product p = null;
                    System.out.println("Insert the name of the product");
                    entradaEscaner = new Scanner(System.in);
                    prodName = entradaEscaner.nextLine();
                    System.out.println("Insert the characteristics");
                    entradaEscaner = new Scanner(System.in);
                    String characteristics = entradaEscaner.nextLine();
                    p = new Product(sender, prodName, characteristics);
                    objHello.registerProd(p);
                    break;
            }

        } catch (Exception e) {
            System.err.println("RMI Example exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}