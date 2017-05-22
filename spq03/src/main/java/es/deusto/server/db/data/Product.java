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

    /**
     * Method to create a new instance of an object Product
     * @param name The value for the PK that will identify a product between all the ones in the DB
     * @param characteristics The value for the characteristics of the product
     */
    public Product(String name, String characteristics) {
        this.name = name;
        this.characteristics = characteristics;
        this.timestamp = System.currentTimeMillis();
    }

    /**
   	 * This method returns the user that owns the product
   	 * @return The owner of the Product
   	 */
    public User dnGetuser() {
        return user;
    }

    /**
   	 * This method returns the name (PK) of the product
   	 * @return The name of the Product
   	 */
    public String getName() {
        return name;
    }

    /**
	 * Establishes the value for the parameter name of the Product
	 * @param name The value for the name
	 */
    public void setName(String name){ this.name = name; }

    /**
	 * Establishes the value for the parameter owner of the Product
	 * @param owner The value for the new owner
	 */
    public void setOwner(User owner) {
        this.user = owner;
    }

    /**
	 * This method returns the characteristics of the product
	 * @return The characteristics of the Product
	 */
    public String getCharacteristics(){return characteristics;}

    /**
	 * This method returns the buyers payment of the product
	 * @return The buyers payment of the Product
	 */
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