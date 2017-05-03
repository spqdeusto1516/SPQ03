package es.deusto.dao;

import es.deusto.data.Money;
import es.deusto.data.User;
import es.deusto.data.Product;

import java.util.List;

public interface IDAO {
    void storeUser(User u);
    void storeProduct(Product p);
    User retrieveUser(String login);
    void updateUser(User u);

    void buyProd(Product p, Money m);
    void addProd(Product p);
    List<Product> getAllProd();
    List<Product> searchProd(String name);
}