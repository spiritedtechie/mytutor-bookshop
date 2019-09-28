package co.uk.mytutor;

import co.uk.mytutor.model.Account;
import co.uk.mytutor.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountRepositoryTest {

    private AccountRepository accountRepository;

    @Before
    public void setup() {
        accountRepository = new InMemoryAccountRepository();
    }

    @Test
    public void testInstantiatesAccountWithInitialBalance() {
        Account account = accountRepository.get();

        assertThat(account.getBalance()).isEqualTo(new BigDecimal(500));
    }

    @Test
    public void testAccountSingletonAccountIsReturn() {
        Account account1 = accountRepository.get();
        Account account2 = accountRepository.get();

        account1.recordPurchase(new Book("A", 10, new BigDecimal(10)), 2);

        assertThat(account1).isSameAs(account2);
        assertThat(account1.getBalance()).isEqualTo(account2.getBalance());
    }

}
