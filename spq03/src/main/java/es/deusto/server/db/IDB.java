package es.deusto.server.db;


import es.deusto.server.db.data.*;

import java.util.List;

public interface IDB {

    List<Product> getAllProd();

    boolean buyProd(String loginB, String loginS, String name, int amount);
    boolean insertUser(User u);
    boolean insertProd(Product p);
    boolean insertMoney(Money m);

    Product showProd(String name);
    User	showUser(String login);

}