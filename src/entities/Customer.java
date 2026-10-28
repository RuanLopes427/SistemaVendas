package entities;

import java.time.LocalDate;

public class Customer extends Person{

    private String deliveryAddress;
    private LocalDate registrationDate;

    public Customer(String name, String email, String idCard,String deliveryAddress){
        super(name, email, idCard);
        this.deliveryAddress = deliveryAddress;
        this.registrationDate = LocalDate.now();
    }

    public String getDeliveryAddress(){
        return this.deliveryAddress;
    }

}
