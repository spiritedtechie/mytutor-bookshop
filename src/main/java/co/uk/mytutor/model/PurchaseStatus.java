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

    public static final class NonExistentItem extends PurchaseStatus {
        private NonExistentItem() {
        }
    }

    public static final PurchaseStatus successful() {
        return new Successful();
    }

    public static final PurchaseStatus outOfStock() {
        return new OutOfStock();
    }

    public static final PurchaseStatus nonExistentItem() {
        return new NonExistentItem();
    }
}
