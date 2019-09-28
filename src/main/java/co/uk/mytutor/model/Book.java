package co.uk.mytutor.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {

    private final String name;
    private final Integer initialStock;
    private final BigDecimal price;

    public Book(String name, Integer initialStock, BigDecimal price) {
        this.name = name;
        this.initialStock = initialStock;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name) &&
                initialStock.equals(book.initialStock) &&
                price.equals(book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, initialStock, price);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", initialStock=" + initialStock +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return this.name;
    }
}
