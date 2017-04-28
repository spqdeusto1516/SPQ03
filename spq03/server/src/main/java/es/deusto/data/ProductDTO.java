package es.deusto.data;

import java.io.Serializable;
import java.util.Date;

public class ProductDTO implements Serializable {
    /**
     * Messages will be transferred to the RMI client as part of a User
     */
    private static final long serialVersionUID = 1L;
    UserDTO owner=null;
    String name=null;
    String characteristics=null;
    MoneyDTO buyersPayment=null;
    long timestamp;


    public ProductDTO(UserDTO owner, String name, String characteristics) {
        this.owner = owner;
        this.name = name;
        this.characteristics = characteristics;
        this.timestamp = System.currentTimeMillis();
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
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

    public void setBuyersPayment(MoneyDTO buyersPayment){ this.buyersPayment = buyersPayment;}

    public String toString() {
        return "ProductDTO: " + this.name + " --> " + this.characteristics + ", is owned by " + this.owner.toString();
    }
}
