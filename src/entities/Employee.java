package entities;

import java.math.BigDecimal;

public class Employee extends Person{

    private String registration;
    private String position;
    private BigDecimal baseSalary;

    public Employee(String name, String email, String idCard, String registration, String position, BigDecimal baseSalary){
        super(name, email, idCard);
        this.registration = registration;
        this.position = position;
        this.baseSalary = baseSalary;
    }

    public String getRegistration() {
        return registration;
    }

    public String getPosition() {
        return position;
    }

    public BigDecimal getSalary() {
        return baseSalary;
    }
}
