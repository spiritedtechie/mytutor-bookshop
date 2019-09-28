package co.uk.mytutor.service;

import co.uk.mytutor.BookRepository;
import co.uk.mytutor.model.Book;
import co.uk.mytutor.model.PurchaseStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookPurchaser {

    private BookRepository bookRepository;

    public BookPurchaser(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public PurchaseStatus purchase(String bookName, Integer quantity) {
        Optional<Book> book = findBook(bookName);

        if (!book.isPresent()) {
            return new PurchaseStatus.NonExistentBook();
        }

        return attemptBookPurchase(book.get(), quantity);
    }

    private Optional<Book> findBook(String bookName) {
        return bookRepository.get(bookName);
    }

    private PurchaseStatus attemptBookPurchase(Book book, Integer quantity) {
        return book.purchase(quantity);
    }
}
