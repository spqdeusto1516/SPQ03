package es.deusto.server.db.dao;

import es.deusto.server.db.data.*;

import java.util.List;

public interface IDAO {

    boolean storeUser(User u);
    List<User> getAllUser();
    User retrieveUser(String login);
    boolean updateUser(User u);

    boolean storeProd(Product p);
    List<Product> getAllProd();
    Product retrieveProdSearch(String name);
    boolean updateProd(Product p);

    boolean storeMoney(Money m);
    Money retrieveMoney(int amount);
    boolean updateMoney(Money m);

}