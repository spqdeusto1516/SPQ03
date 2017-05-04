package es.deusto.server.db.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@PersistenceCapable (detachable = "true")
public class Money implements Serializable {
	/**
	 * Messages will be transferred to the RMI client as part of a User
	 */
	private static final long serialVersionUID = 1L;
	User user;
	Product product;
    int amount=0;
	long timestamp;
	
	
    public Money(int amount, User sender) {
        this.amount = amount;
        this.user = sender;
		this.timestamp = System.currentTimeMillis();
    }

	public User getSender() {
        return user;
    }

    public void setUserSending(User sender) {
        this.user = sender;
    }

    public int getAmount(){return amount;}

    public String toString() {
        return "Money: message --> " + this.amount + ", timestamp -->  " + new Date(this.timestamp) + " by " + this.user;
    }
}