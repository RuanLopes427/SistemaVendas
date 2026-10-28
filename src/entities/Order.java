package entities;

import entities.enums.OrderStatus;
import entities.enums.PaymentStatus;
import entities.payments.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Order {

    private String orderId;
    private LocalDateTime creationDate = LocalDateTime.now();
    private OrderStatus status = OrderStatus.OPEN;
    private PaymentStatus statusPayment = PaymentStatus.NOT_STARTED;
    private Customer customer;
    private Employee employee;
    private ArrayList<OrderItems> items = new ArrayList<>();
    private Payment payment;

    public Order(Customer customer, Employee employee) {
        this.orderId = orderId = UUID.randomUUID().toString();
        this.customer = customer;
        this.employee = employee;
    }

    public void additem(Product product, int quantity) {

        if (status != status.OPEN) {
            throw new IllegalStateException(
                    "Cannot add items to a non-open order."
            );
        }
        items.add(new OrderItems(product, quantity));
    }

    public boolean removeItem(String sku) {
        if (sku == null || sku.isBlank()) {
            return false;
        }

        return items.removeIf(item -> item.getProduct().getSku().equals(sku));
    }

    public BigDecimal subTotalCalculate() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderItems item : items) {
            subtotal = subtotal.add(item.subtotalCalculate());
        }
        return subtotal;
    }

    public BigDecimal totalCalculate() {
        return payment.valueFinalCalculate(subTotalCalculate());
    }

    public void finish(Payment payment) {

        if (status != status.OPEN) {
            throw new IllegalStateException(
                    "Only open orders can be finalized."
            );
        }

        if (items.isEmpty()) {
            throw new IllegalStateException(
                    "Cannot finalize an empty order."
            );
        }

        if (payment == null) {
            throw new IllegalArgumentException(
                    "Payment cannot be null."
            );
        }
        this.payment = payment;

        statusPayment = payment.process(totalCalculate());

        if (statusPayment == PaymentStatus.APPROVED) {
            status = OrderStatus.PAID;
            for (OrderItems item : items) {
                item.getProduct().reduceInventory(item.getQuantity());
            }

        } else if (statusPayment == PaymentStatus.PENDING) {
            status = OrderStatus.WAITING_PAYMENT;

        } else if (statusPayment == PaymentStatus.REJECTED) {
            return;
        }

    }

    public void confirmPayment() {
        if (status == OrderStatus.WAITING_PAYMENT) {
            for (OrderItems item : items) {
                item.getProduct().reduceInventory(item.getQuantity());
            }

            this.status = OrderStatus.PAID;
            this.statusPayment = PaymentStatus.APPROVED;
        }
    }

    public void cancel() {
        if (status == OrderStatus.PAID || status == OrderStatus.CANCELED) {
            return;
        }
        this.status = OrderStatus.CANCELED;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Payment getPayment() {
        return payment;
    }

    public PaymentStatus getStatusPayment() {
        return statusPayment;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public ArrayList<OrderItems> getItems() {
        return items;
    }
}
