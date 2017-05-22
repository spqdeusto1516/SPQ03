package es.deusto.server.db.data;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@PersistenceCapable (detachable = "true")
public class Money implements Serializable {
    final static Logger logger = LoggerFactory.getLogger(Money.class);
	/**
	 * Messages will be transferred to the RMI client as part of a User
	 */
	private static final long serialVersionUID = 1L;
    private User user;
    private Product product;
    private int amount=0;
    private long timestamp;
	
	/**
	 * Method to create a new instance of an object Product
	 * @param amount establishes the value for the parameter amount
	 */
    public Money(int amount) {
        this.amount = amount;
		this.timestamp = System.currentTimeMillis();
    }

    /**
   	 * This method returns the user that sends the money
   	 * @return The sender of the money
   	 */
	public User getSender() {
        return user;
    }

	/**
   	 * This method returns the product inside the money interaction
   	 * @return The product of the money interaction
   	 */
    public Product getProd() {
        return product;
    }

    /**
	 * Establishes the value for the parameter sender of the Money
     * @param sender the user that will send the money
     */
    public void setUserSending(User sender) {
        this.user = sender;
    }

    /**
	 * Establishes the value for the parameter sender of the Money
     * @param p the product inside the money interaction
     */
    public void setProduct(Product p) {
        this.product = p;
    }

	/**
   	 * This method returns the amount of the money
   	 * @return The amount of money in the interaction
   	 */
    public int getAmount(){return amount;}

    public String toString() {
        return "Money: message --> " + this.amount + ", timestamp -->  " + new Date(this.timestamp) + " by " + this.user;
    }
}