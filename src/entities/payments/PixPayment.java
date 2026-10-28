package entities.payments;

import entities.enums.PaymentStatus;
import java.math.BigDecimal;

public class PixPayment implements Payment {
    private String pixKey;

    public PixPayment(String pixKey) {
        this.pixKey = pixKey;
    }

    @Override
    public BigDecimal valueFinalCalculate(BigDecimal baseValue) {
        return baseValue.subtract(baseValue.multiply(BigDecimal.valueOf(0.05)));
    }

    @Override
    public PaymentStatus process(BigDecimal finalValue) {
        if (pixKey == null){
            return PaymentStatus.REJECTED;
        }
        return PaymentStatus.APPROVED;
    }

    @Override
    public String getDescription() {
        return "Pix";
    }

}
