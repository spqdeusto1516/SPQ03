package es.deusto.server.db.data;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;

@PersistenceCapable (detachable = "true")
public class User implements Serializable {
	final static Logger logger = LoggerFactory.getLogger(User.class);
	/**
	 * User implements Serializable to be transferred to the RMI client
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private String login;
	private String password=null;
	private boolean superU = true;
	@Persistent(defaultFetchGroup="true", mappedBy="user")
	@Join
	List<Money> moneyList = new ArrayList<>();
	private int amount = 0;


	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public void addMoney(int money) { this.amount += money;	}

	public void removeMoney(int money) { this.amount -= money;	}

	public String getLogin() {
		return this.login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSuper(boolean superU){
		this.superU = superU;
	}
	public boolean getSuper() {return this.superU;}

	public int getMoney() {return this.amount;}

	public void setAmount(int number){ this.amount = number; }

	public String toString() {
		return "User: login --> " + this.login + ", password -->  " + this.password;
	}
}

