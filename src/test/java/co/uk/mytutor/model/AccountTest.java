package co.uk.mytutor.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void testRecordPurchase() {
        Book book = new Book("A", 10, 10);
        Account account = new Account(20);

        account.recordPurchase(book, 4);

        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(60));
    }
}
