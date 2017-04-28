package es.deusto.data;

import java.io.Serializable;
import java.util.Date;

public class MoneyDTO implements Serializable {
	/**
	 * Messages will be transferred to the RMI client as part of a UserDTO
	 */
	private static final long serialVersionUID = 1L;
    UserDTO sender=null;
    int amount=0;
	long timestamp;
	
	
    public MoneyDTO(int amount, UserDTO sender) {
        this.amount = amount;
        this.sender = sender;
		this.timestamp = System.currentTimeMillis();
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setUserDTOSending(UserDTO sender) {
        this.sender = sender;
    }

    public int getAmount(){return amount;}

    public String toString() {
        return "MoneyDTO: message --> " + this.amount + ", timestamp -->  " + new Date(this.timestamp) + " by " + this.sender;
    }
}