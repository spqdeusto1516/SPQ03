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
	
	
    public Money(int amount) {
        this.amount = amount;
		this.timestamp = System.currentTimeMillis();
    }

	public User getSender() {
        return user;
    }

    public Product getProd() {
        return product;
    }

    public void setUserSending(User sender) {
        this.user = sender;
    }

    public void setProduct(Product p) {
        this.product = p;
    }

    public int getAmount(){return amount;}

    public String toString() {
        return "Money: message --> " + this.amount + ", timestamp -->  " + new Date(this.timestamp) + " by " + this.user;
    }
}