package co.uk.mytutor.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BookTest {

    @Test
    public void testPurchaseSuccessfulIfEnoughStock() {
        var book = new Book("A", 10, 14);

        var result = book.purchase(1);

        assertThat(book.getStock()).isEqualTo(9);
        assertThat(result).isInstanceOf(PurchaseStatus.Successful.class);
    }

    @Test
    public void testPurchaseSuccessForMultipleQuantity() {
        var book = new Book("A", 10, 14);

        var result = book.purchase(3);

        assertThat(book.getStock()).isEqualTo(7);
        assertThat(result).isInstanceOf(PurchaseStatus.Successful.class);
    }

    @Test
    public void testPurchaseToZeroStock() {
        var book = new Book("A", 1, 14);

        var result = book.purchase(1);

        assertThat(book.getStock()).isEqualTo(0);
        assertThat(result).isInstanceOf(PurchaseStatus.Successful.class);
    }

    @Test
    public void testPurchaseToInsufficientStock() {
        var book = new Book("A", 5, 14);

        var result = book.purchase(10);

        assertThat(result).isInstanceOf(PurchaseStatus.OutOfStock.class);
    }

    @Test
    public void testGetPurchasePrice() {
        var book = new Book("A", 5, 14);

        var result = book.getPurchasePrice(3);

        assertThat(result).isEqualTo(BigDecimal.valueOf(42));
    }

    @Test
    public void testGetPurchasePriceDouble() {
        var book = new Book("A", 5, BigDecimal.valueOf(14.54));

        var result = book.getPurchasePrice(3);

        assertThat(result).isEqualTo(BigDecimal.valueOf(43.62));
    }

}
