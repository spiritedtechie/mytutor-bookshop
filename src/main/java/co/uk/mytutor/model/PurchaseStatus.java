package co.uk.mytutor.model;

public class PurchaseStatus {

    public static final class Successful extends PurchaseStatus {
    }

    public static final class OutOfStock extends PurchaseStatus {
    }

    public static final class NonExistentBook extends PurchaseStatus {
    }
}
