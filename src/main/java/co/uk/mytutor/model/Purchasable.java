package co.uk.mytutor.model;

import java.math.BigDecimal;

public interface Purchasable {

    BigDecimal getPurchasePrice(Integer forQuantity);

    PurchaseStatus purchase(Integer requiredQuantity);

}
