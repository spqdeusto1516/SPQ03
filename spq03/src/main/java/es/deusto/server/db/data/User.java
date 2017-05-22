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
	private boolean superU = false;
	@Persistent(defaultFetchGroup="true", mappedBy="user")
	@Join
	List<Money> moneyList = new ArrayList<>();
	private int amount = 0;

	/**
	 * Method to create a new instance of an object User
	 * @param login The value for the PK that will identify a user between all the ones in the DB
	 * @param password The value for the password of the user
	 */
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	/**
	 * This method augments the value of the users amount of money for the parameter that is sent
	 * @param money the amount of money to be added
	 */
	public void addMoney(int money) { this.amount += money;	}

	/**
	 * This method reduces the value of the users amount of money for the parameter that is sent
	 * @param money the amount of money to be removed
	 */
	public void removeMoney(int money) { this.amount -= money;	}

	/**
	 * This method returns the PK of the user
	 * @return The username (login) of the user
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * This method returns the pass of the user
	 * @return The password of the user
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Establishes the value for the parameter password of the user
	 * @param password The value for the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Establishes the value for the parameter superU of the user
	 * @param superU The value of the users superU parameter
	 */
	public void setSuper(boolean superU){
		this.superU = superU;
	}
	
	/**
	 * This method returns the superU value of the user
	 * @return The superU value of the user
	 */
	public boolean getSuper() {return this.superU;}

	/**
	 * This method returns the amount of money of the user
	 * @return The amount value of the user
	 */
	public int getMoney() {return this.amount;}

	/**
	 * Establishes the value for the parameter amount of the user
	 * @param amount The value of the users amount parameter
	 */
	public void setAmount(int number){ this.amount = number; }

	public String toString() {
		return "User: login --> " + this.login + ", password -->  " + this.password;
	}
}

