package es.deusto.server.db.data;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@PersistenceCapable (detachable = "true")
public class Product implements Serializable {
    /**
     * Messages will be transferred to the RMI client as part of a User
     */
    private static final long serialVersionUID = 1L;
    User user;
    @PrimaryKey
    String name=null;
    String characteristics=null;
    @Persistent(defaultFetchGroup="true", mappedBy="product", dependentElement="true")
    @Join
    List<Money> buyersPayment = new ArrayList<>();
    long timestamp;


    public Product(User owner, String name, String characteristics) {
        this.user = owner;
        this.name = name;
        this.characteristics = characteristics;
        this.timestamp = System.currentTimeMillis();
    }

    public User getOwner() {
        return user;
    }

    public String getName() {
        return name;
    }

    public void setOwner(User owner) {
        this.user = owner;
    }

    public String getCharacteristics(){return characteristics;}

    public String getBuyersPayment(){
        if(this.buyersPayment != null){
            return this.buyersPayment.toString();
        }else{
            return "There is no buyer at this time";
        }
    }

    public String toString() {
        return "Product: " + this.name + " --> " + this.characteristics + ", is owned by " + this.user.toString();
    }

    public String toStringShort(){
        return this.name;
    }
}