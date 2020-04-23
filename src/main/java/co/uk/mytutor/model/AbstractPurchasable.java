package co.uk.mytutor.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractPurchasable implements Purchasable {

    private final String name;
    private AtomicInteger stock;
    private final BigDecimal price;

    public AbstractPurchasable(String name, Integer initialStock, BigDecimal price) {
        this.name = name;
        this.stock = new AtomicInteger(initialStock);
        this.price = price;
    }

    public AbstractPurchasable(String name, Integer initialStock, int price) {
        this(name, initialStock, new BigDecimal(price));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var abstractPurchasable = (AbstractPurchasable) o;
        return name.equals(abstractPurchasable.name) &&
                stock.get() == abstractPurchasable.stock.get() &&
                price.equals(abstractPurchasable.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stock, price);
    }

    @Override
    public String toString() {
        return "Purchasable{" +
                "name='" + name + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return this.name;
    }

    public Integer getStock() {
        return Integer.valueOf(this.stock.get());
    }

    @Override
    public BigDecimal getPurchasePrice(Integer quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public synchronized PurchaseStatus purchase(Integer requiredQuantity) {
        if (leavesItemOutOfStock(requiredQuantity)) {
            return PurchaseStatus.outOfStock();
        } else {
            takeFromStock(requiredQuantity);
            return PurchaseStatus.successful();
        }
    }

    private int takeFromStock(Integer requiredQuantity) {
        return this.stock.updateAndGet(previousValue -> previousValue - requiredQuantity);
    }

    private boolean leavesItemOutOfStock(Integer requiredQuantity) {
        return this.stock.get() - requiredQuantity < 0;
    }
}
