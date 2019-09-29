package co.uk.mytutor.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Book {

    private final String name;
    private AtomicInteger stock;
    private final BigDecimal price;

    public Book(String name, Integer initialStock, BigDecimal price) {
        this.name = name;
        this.stock = new AtomicInteger(initialStock);
        this.price = price;
    }

    public Book(String name, Integer initialStock, int price) {
        this(name, initialStock, new BigDecimal(price));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name) &&
                stock.get() == book.stock.get() &&
                price.equals(book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stock, price);
    }

    @Override
    public String toString() {
        return "Book{" +
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

    public BigDecimal getPurchasePrice(Integer quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public synchronized PurchaseStatus purchase(Integer requiredQuantity) {
        if (leavesBookOutOfStock(requiredQuantity)) {
            return PurchaseStatus.outOfStock();
        } else {
            takeFromStock(requiredQuantity);
            return PurchaseStatus.successful();
        }
    }

    private int takeFromStock(Integer requiredQuantity) {
        return this.stock.updateAndGet(previousValue -> previousValue - requiredQuantity);
    }

    private boolean leavesBookOutOfStock(Integer requiredQuantity) {
        return this.stock.get() - requiredQuantity < 0;
    }
}
