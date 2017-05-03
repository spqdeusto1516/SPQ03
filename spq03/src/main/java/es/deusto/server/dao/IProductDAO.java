package es.deusto.dao;

import es.deusto.data.Money;
import es.deusto.data.User;
import es.deusto.data.Product;

import java.util.List;

public interface IProductDAO {
    void buyProd(Product p, Money m);
    void addProd(Product p);
    List<Product> getAllProd();
    List<Product> searchProd(String name);
}
