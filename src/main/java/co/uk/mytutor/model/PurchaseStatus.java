package co.uk.mytutor.model;

public class PurchaseStatus {

    public static final class Successful extends PurchaseStatus {
        private Successful() {
        }
    }

    public static final class OutOfStock extends PurchaseStatus {
        private OutOfStock() {
        }
    }

    public static final class NonExistentBook extends PurchaseStatus {
        private NonExistentBook() {
        }
    }

    public static final PurchaseStatus successful() {
        return new Successful();
    }

    public static final PurchaseStatus outOfStock() {
        return new OutOfStock();
    }

    public static final PurchaseStatus nonExistentBook() {
        return new NonExistentBook();
    }
}
