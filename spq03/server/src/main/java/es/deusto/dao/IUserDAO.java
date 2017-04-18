package es.deusto.dao;

import es.deusto.data.User;

public interface IUserDAO {
	void storeUser(User u);
	User retrieveUser(String login);
	void updateUser(User u);

}
