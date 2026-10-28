import entities.*;
import entities.payments.CardPayment;
import entities.payments.Payment;
import entities.payments.PixPayment;
import entities.payments.TicketPayment;

public static void main(String[] args) {

    Product keyboard = new Product(
            "P001",
            "Keyboard",
            new BigDecimal("100.00"),
            10
    );

    Product mouse = new Product(
            "P002",
            "Mouse",
            new BigDecimal("50.00"),
            10
    );

    Customer customer = new Customer(
            "Ana",
            "ana@example.com",
            "ID001",
            "Rua A"
    );

    Employee employee = new Employee(
            "Carlos",
            "carlos@example.com",
            "ID002",
            "EMP001",
            "Sales",
            new BigDecimal("2500.00")
    );

    Order order = new Order(customer, employee);

    order.additem(keyboard, 2);
    order.additem(mouse, 1);

    System.out.println(
            "Subtotal: " + order.subTotalCalculate()
    );

    Payment pixPayment = new PixPayment("14174272484");

    Order order1 = new Order(customer, employee);

    order1.additem(keyboard, 2);

    Payment pixtPayment = new PixPayment("141742724844");
    Payment cardPayment = new CardPayment("1234", 3);

    order1.cancel();
    System.out.println(cardPayment.valueFinalCalculate(order1.subTotalCalculate()));

    for (OrderItems items: order1.getItems()){
        System.out.println(items.getProduct().getStock());
    }

    for (OrderItems items: order1.getItems()){
        System.out.println(items.getProduct().getStock());
    }

}


