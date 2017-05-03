package es.deusto.dao;

import es.deusto.data.Product;
import es.deusto.data.User;

public interface IUserDAO {
	void storeUser(User u);
	void storeProduct(Product p);
	User retrieveUser(String login);
	void updateUser(User u);

}
