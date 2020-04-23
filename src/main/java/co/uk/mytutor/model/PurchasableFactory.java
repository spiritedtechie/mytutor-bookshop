package co.uk.mytutor.model;

import java.math.BigDecimal;

public class PurchasableFactory {

    public Purchasable createItems(String type, String name, Integer initialStock, BigDecimal price ) {

        if ("book".equals(type)) {
            return new Book(name, initialStock, price);
        }

        if ("bag".equals(type)) {
            return new Bag(name, initialStock, price);
        }

        throw new IllegalArgumentException("Unknown purchase type");

    }
}
