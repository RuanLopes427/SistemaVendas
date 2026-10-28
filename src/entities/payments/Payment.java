package entities.payments;

import entities.enums.PaymentStatus;
import java.math.BigDecimal;

public interface Payment {

    BigDecimal valueFinalCalculate(BigDecimal baseValue);

    PaymentStatus process(BigDecimal finalValue);

    String getDescription();

}
