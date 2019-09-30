package co.uk.mytutor.model;

import java.math.BigDecimal;

public class Account {

    private BigDecimal balance;

    public Account(BigDecimal balance) {
        this.balance = balance;
    }

    public Account(int balance) {
        this(new BigDecimal(balance));
    }

    public void recordPurchase(Purchasable item, Integer quantity) {
        var purchasePriceTotal = item.getPurchasePrice(quantity);

        synchronized (balance) {
            balance = balance.add(purchasePriceTotal);
        }
    }

    public BigDecimal getBalance() {
        return this.balance;
    }
}
