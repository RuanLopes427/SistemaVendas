package entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String sku;
    private String name;
    private BigDecimal price;
    private int stock;

    public Product(String sku, String name, BigDecimal price, int stock) {
        Objects.requireNonNull(price, "Price cannot be null.");

        if (stock < 0 || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The price or stock cant be lower that zero");
        }

        this.sku = sku;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getSku() {
        return sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public boolean inStock(int quantity) {
        if (this.stock >= quantity) {
            return true;
        } else {
            return false;
        }
    }

    public void reduceInventory(int quantity) {
        if (quantity > getStock() || quantity < 0) {
            throw new IllegalArgumentException("The information quantity is bigger that quantity in stock");
        } else {
            this.stock -= quantity;
        }
    }

    public void replenishStock(int quantity) {
        this.stock += quantity;
    }

    public void updatePrice(BigDecimal newPrice) {
        this.price = newPrice;
    }
}
