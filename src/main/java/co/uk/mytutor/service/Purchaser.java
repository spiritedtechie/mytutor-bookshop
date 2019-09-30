package co.uk.mytutor.service;

import co.uk.mytutor.model.PurchaseStatus;

public interface Purchaser {

    PurchaseStatus purchase(String itemIdentifier, Integer quantity);
}
