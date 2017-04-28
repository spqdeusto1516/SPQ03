package es.deusto.data;


public class Assembler {

    public UserDTO userToDTO(User u){
        return new UserDTO(u.getLogin(), u.getPassword());
    }

    public User uDTOToNormal(UserDTO u){
        return new User(u.getLogin(), u.getPassword());
    }

    public MoneyDTO messageToDTO(Money m){
        return new MoneyDTO(m.getAmount(), new UserDTO(m.getSender().getLogin(), m.getSender().getPassword()));
    }

    public Money mDTOToNormal(MoneyDTO m){
        return new Money(m.getAmount(), new User(m.getSender().getLogin(), m.getSender().getPassword()));
    }

    public ProductDTO productToDTO(Product p){
        if(p.buyersPayment == null) {
            return new ProductDTO(new UserDTO(p.owner.getLogin(), p.owner.getPassword()), p.name, p.characteristics);
        }else{
            ProductDTO pDTO = null;
            pDTO = new ProductDTO(new UserDTO(p.owner.getLogin(), p.owner.getPassword()), p.name, p.characteristics);
            pDTO.setBuyersPayment(new MoneyDTO(p.buyersPayment.getAmount(), new UserDTO(p.buyersPayment.getSender().getLogin(), p.buyersPayment.getSender().getPassword())));
            return pDTO;
        }
    }

    public Product pDTOToNormal(ProductDTO p){
        if(p.buyersPayment == null) {
            return new Product(new User(p.owner.getLogin(), p.owner.getPassword()), p.name, p.characteristics);
        }else{
            Product prod = null;
            prod = new Product(new User(p.owner.getLogin(), p.owner.getPassword()), p.name, p.characteristics);
            prod.setBuyersPayment(new Money(p.buyersPayment.getAmount(), new User(p.buyersPayment.getSender().getLogin(), p.buyersPayment.getSender().getPassword())));
            return prod;
        }
    }
}
