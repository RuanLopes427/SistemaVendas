package entities;

public abstract class Person {

    private String name;
    private String email;
    private String idCard;

    public Person(String name, String email, String idCard) {
        this.name = name;
        this.email = email;
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getIdCard() {
        return idCard;
    }
}
