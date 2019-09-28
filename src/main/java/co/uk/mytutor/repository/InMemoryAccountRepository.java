package co.uk.mytutor.repository;

import co.uk.mytutor.model.Account;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InMemoryAccountRepository implements AccountRepository {

    private Account account;

    public InMemoryAccountRepository() {
        this.account = new Account(new BigDecimal(500));
    }

    @Override
    public Account get() {
        return this.account;
    }
}
