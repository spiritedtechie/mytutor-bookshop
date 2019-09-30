package co.uk.mytutor.service;

import co.uk.mytutor.model.Book;
import co.uk.mytutor.model.PurchaseStatus;
import co.uk.mytutor.repository.AccountRepository;
import co.uk.mytutor.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookPurchaser implements Purchaser {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookPurchaser.class);

    private AccountRepository accountRepository;
    private BookRepository bookRepository;

    public BookPurchaser(AccountRepository accountRepository, BookRepository bookRepository) {
        this.accountRepository = accountRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public PurchaseStatus purchase(String identifier, Integer quantity) {
        var book = findBook(identifier);

        if (!book.isPresent()) {
            return PurchaseStatus.nonExistentItem();
        }

        var purchaseStatus = attemptBookPurchase(book.get(), quantity);

        updateAccount(purchaseStatus, book, quantity);

        return purchaseStatus;
    }

    private void updateAccount(PurchaseStatus purchaseStatus, Optional<Book> book, Integer quantity) {
        if (purchaseStatus instanceof PurchaseStatus.Successful) {
            var account = this.accountRepository.get();
            account.recordPurchase(book.get(), quantity);

            LOGGER.debug("Account balance is: " + account.getBalance());
        }
    }

    private Optional<Book> findBook(String bookName) {
        return bookRepository.get(bookName);
    }

    private PurchaseStatus attemptBookPurchase(Book book, Integer quantity) {
        return book.purchase(quantity);
    }
}
