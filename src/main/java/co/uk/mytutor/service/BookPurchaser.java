package co.uk.mytutor.service;

import co.uk.mytutor.model.Account;
import co.uk.mytutor.model.Book;
import co.uk.mytutor.model.PurchaseStatus;
import co.uk.mytutor.repository.AccountRepository;
import co.uk.mytutor.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookPurchaser {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookPurchaser.class);

    private AccountRepository accountRepository;
    private BookRepository bookRepository;

    public BookPurchaser(AccountRepository accountRepository, BookRepository bookRepository) {
        this.accountRepository = accountRepository;
        this.bookRepository = bookRepository;
    }

    public PurchaseStatus purchase(String bookName, Integer quantity) {
        Optional<Book> book = findBook(bookName);

        if (!book.isPresent()) {
            return new PurchaseStatus.NonExistentBook();
        }

        PurchaseStatus purchaseStatus = attemptBookPurchase(book.get(), quantity);

        updateBookshopAccount(purchaseStatus, book, quantity);

        return purchaseStatus;
    }

    private void updateBookshopAccount(PurchaseStatus purchaseStatus, Optional<Book> book, Integer quantity) {
        if (purchaseStatus instanceof PurchaseStatus.Successful) {
            Account account = this.accountRepository.get();
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
