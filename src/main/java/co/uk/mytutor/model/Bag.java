package co.uk.mytutor.model;

import java.math.BigDecimal;

public class Bag extends AbstractPurchasable {
    public Bag(String name, Integer initialStock, BigDecimal price) {
        super(name, initialStock, price);
    }

    public Bag(String name, Integer initialStock, int price) {
        super(name, initialStock, price);
    }
}
