package es.deusto.server.db.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import java.io.Serializable;
import java.util.Date;


@PersistenceCapable
public class Product implements Serializable {
    /**
     * Messages will be transferred to the RMI client as part of a User
     */
    private static final long serialVersionUID = 1L;
    User owner=null;
    @PrimaryKey
    String name=null;
    String characteristics=null;
    Money buyersPayment=null;
    long timestamp;


    public Product(User owner, String name, String characteristics) {
        this.owner = owner;
        this.name = name;
        this.characteristics = characteristics;
        this.timestamp = System.currentTimeMillis();
    }

    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getCharacteristics(){return characteristics;}

    public String getBuyersPayment(){
        if(this.buyersPayment != null){
            return this.buyersPayment.toString();
        }else{
            return "There is no buyer at this time";
        }
    }

    public void setBuyersPayment(Money buyersPayment){ this.buyersPayment = buyersPayment;}

    public String toString() {
        return "Product: " + this.name + " --> " + this.characteristics + ", is owned by " + this.owner.toString();
    }

    public String toStringShort(){
        return this.name;
    }
}