package es.deusto.server.db.data;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;

@PersistenceCapable (detachable = "true")
public class User implements Serializable {
	/**
	 * User implements Serializable to be transferred to the RMI client
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	String login=null;
	String password=null;
	int amount = 0;
	
	
	public User(String login, String password) {
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
			 return "User: login --> " + this.login + ", password -->  " + this.password;
	 }
}

