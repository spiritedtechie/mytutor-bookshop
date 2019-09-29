package co.uk.mytutor.repository;

import co.uk.mytutor.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountRepositoryTest {

    private AccountRepository accountRepository;

    @Before
    public void setup() {
        accountRepository = new InMemoryAccountRepository();
    }

    @Test
    public void testInstantiatesAccountWithInitialBalance() {
        var account = accountRepository.get();

        assertThat(account.getBalance()).isEqualTo(new BigDecimal(500));
    }

    @Test
    public void testAccountSingletonAccountIsReturn() {
        var account1 = accountRepository.get();
        var account2 = accountRepository.get();

        account1.recordPurchase(new Book("A", 10, 10), 2);

        assertThat(account1).isSameAs(account2);
        assertThat(account1.getBalance()).isEqualTo(account2.getBalance());
    }

}
