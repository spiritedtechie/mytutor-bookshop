package co.uk.mytutor.model;

import java.math.BigDecimal;

public class Account {

    private BigDecimal balance;

    public Account(BigDecimal balance) {
        this.balance = balance;
    }

    public void recordPurchase(Book book, Integer quantity) {
        BigDecimal purchasePriceTotal = book.getPurchasePrice(quantity);

        synchronized (balance) {
            balance = balance.add(purchasePriceTotal);
        }
    }

    public BigDecimal getBalance() {
        return this.balance;
    }
}
