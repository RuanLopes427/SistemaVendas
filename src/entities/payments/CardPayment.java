    package entities.payments;

    import entities.enums.PaymentStatus;

    import java.math.BigDecimal;

    public class CardPayment implements Payment {

        private String last4digits;
        private int installments;

        public CardPayment(String last4digits, int installments) {
            this.last4digits = last4digits;
            this.installments = installments;
        }

        @Override
        public BigDecimal valueFinalCalculate(BigDecimal baseValue) {
            if (installments < 1 || installments > 6) {
                throw new IllegalArgumentException("Invalid installments");
            }

           if (installments == 1){
               return baseValue;
           }

           BigDecimal addition = new BigDecimal("0.02");

            return baseValue.add(baseValue.multiply(addition));
        }

        @Override
        public PaymentStatus process(BigDecimal finalValue) {
            if (last4digits == null || !last4digits.matches("\\d{4}") || installments <= 0) {
                return PaymentStatus.REJECTED;
            }
            return PaymentStatus.APPROVED;
        }

        @Override
        public String getDescription() {
            return "Credit card";
        }
    }
