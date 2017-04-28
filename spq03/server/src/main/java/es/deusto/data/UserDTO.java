package es.deusto.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable {
	/**
	 * UserDTO implements Serializable to be transferred to the RMI client
	 */
	private static final long serialVersionUID = 1L;
	String login=null;
	String password=null;
	int amount = 0;
	
	
	public UserDTO(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public void addMoney(int money) { this.amount += money;	}

	public void removeMoney(int money) { this.amount += money;	}

	public String getLogin() {
		return this.login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMoney() {return this.amount;}

	public String toString() {
		return "UserDTO: login --> " + this.login + ", password -->  " + this.password;
	}
}

