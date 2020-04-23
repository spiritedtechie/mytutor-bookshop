package co.uk.mytutor.model;

import java.math.BigDecimal;

public class Book extends AbstractPurchasable {
    public Book(String name, Integer initialStock, BigDecimal price) {
        super(name, initialStock, price);
    }

    public Book(String name, Integer initialStock, int price) {
        super(name, initialStock, price);
    }
}
