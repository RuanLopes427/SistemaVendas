package entities.payments;

import entities.enums.PaymentStatus;

import java.math.BigDecimal;

public class TicketPayment implements Payment {

    private String barcode;

    public TicketPayment(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public BigDecimal valueFinalCalculate(BigDecimal baseValue) {
        return baseValue.subtract(baseValue.multiply(BigDecimal.valueOf(0.03)));
    }

    @Override
    public PaymentStatus process(BigDecimal finalValue) {
        if (barcode == null || !barcode.matches("\\d{44}")) {
            return PaymentStatus.REJECTED;
        } else {
            return PaymentStatus.PENDING;
        }
    }

    @Override
    public String getDescription() {
        return "Ticket";
    }
}
