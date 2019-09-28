package co.uk.mytutor.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BookTest {

    @Test
    public void testPurchaseSuccessfulIfEnoughStock() {
        Book book = new Book("A", 10, BigDecimal.valueOf(14));

        PurchaseStatus result = book.purchase(1);

        assertThat(book.getStock()).isEqualTo(9);
        assertThat(result).isInstanceOf(PurchaseStatus.Successfull.class);
    }

    @Test
    public void testPurchaseSuccessForMultipleQuantity() {
        Book book = new Book("A", 10, BigDecimal.valueOf(14));

        PurchaseStatus result = book.purchase(3);

        assertThat(book.getStock()).isEqualTo(7);
        assertThat(result).isInstanceOf(PurchaseStatus.Successfull.class);
    }

    @Test
    public void testPurchaseToZeroStock() {
        Book book = new Book("A", 1, BigDecimal.valueOf(14));

        PurchaseStatus result = book.purchase(1);

        assertThat(book.getStock()).isEqualTo(0);
        assertThat(result).isInstanceOf(PurchaseStatus.Successfull.class);
    }

    @Test
    public void testPurchaseToInsufficientStock() {
        Book book = new Book("A", 5, BigDecimal.valueOf(14));

        PurchaseStatus result = book.purchase(10);

        assertThat(result).isInstanceOf(PurchaseStatus.OutOfStock.class);
    }

}
