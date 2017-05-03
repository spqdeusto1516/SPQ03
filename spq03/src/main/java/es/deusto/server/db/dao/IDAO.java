package es.deusto.server.db.dao;

import es.deusto.server.db.data.*;

import java.util.List;

public interface IDAO {
    void storeUser(User u);
    void storeProduct(Product p);
    User retrieveUser(String login);
    void updateUser(User u);

    void buyProd(Product p, Money m, String name);
    void addProd(Product p);
    List<Product> getAllProd();
    List<Product> searchProd(String name);
}