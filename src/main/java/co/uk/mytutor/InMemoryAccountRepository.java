package co.uk.mytutor;

import co.uk.mytutor.model.Account;

import java.math.BigDecimal;

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
