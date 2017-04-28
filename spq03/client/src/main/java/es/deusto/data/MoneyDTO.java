package es.deusto.data;

import java.io.Serializable;
import java.util.Date;

public class MoneyDTO implements Serializable {
    /**
     * Money will be transferred to the RMI client as part of a UserDTO
     */
    private static final long serialVersionUID = 1L;
    UserDTO sender=null;
    String text=null;
    long timestamp;


    public MoneyDTO(String text) {
        this.text = text;
        this.timestamp = System.currentTimeMillis();
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setUserDTOSending(UserDTO sender) {
        this.sender = sender;
    }

    public String toString() {
        return "MoneyDTO: message --> " + this.text + ", timestamp -->  " + new Date(this.timestamp) + " by " + this.sender;
    }
}