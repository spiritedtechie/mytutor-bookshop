package co.uk.mytutor.repository;

import co.uk.mytutor.model.Account;
import org.springframework.stereotype.Component;

@Component
public class InMemoryAccountRepository implements AccountRepository {

    private final Account account;

    public InMemoryAccountRepository() {
        this.account = new Account(500);
    }

    @Override
    public Account get() {
        return this.account;
    }
}
