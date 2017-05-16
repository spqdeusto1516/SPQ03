package es.deusto.server.db.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    final static Logger logger = LoggerFactory.getLogger(Product.class);
    /**
     * Messages will be transferred to the RMI client as part of a User
     */
    private static final long serialVersionUID = 1L;
    private User user;

    @PrimaryKey
    private String name;

    private String characteristics=null;
    @Persistent(defaultFetchGroup="true", mappedBy="product", dependentElement="true")
    @Join
    private List<Money> buyersPayment = new ArrayList<>();
    private long timestamp;


    public Product(String name, String characteristics) {
        this.name = name;
        this.characteristics = characteristics;
        this.timestamp = System.currentTimeMillis();
    }

    public User dnGetuser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){ this.name = name; }

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