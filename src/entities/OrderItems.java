package entities;

import java.math.BigDecimal;

public class OrderItems {

    private Product product;
    private int quantity;
    private BigDecimal unitPrice;

    public OrderItems(Product product, int quantity) {
        if (quantity <= 0 || product == null || !product.inStock(quantity)){
            throw new IllegalArgumentException("The values informed were invalided. " +
                    "The values 'quantity' cant be smaller that zero and 'product' cant be null");
        }
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
    }

    public void addQuantity(int quantity) {
        if (quantity <= 0 ){
            throw new IllegalArgumentException("The quantity cant be smaller that zero.");
        }
        this.quantity += quantity;
    }

    public BigDecimal subtotalCalculate(){
        BigDecimal quantity = BigDecimal.valueOf(this.quantity);
        return this.unitPrice.multiply(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

}
