package co.uk.mytutor.controller;

import co.uk.mytutor.BookRepository;
import co.uk.mytutor.model.Book;
import co.uk.mytutor.model.PurchaseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PurchaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);

    private BookRepository bookRepository;

    public PurchaseController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity purchase(@RequestParam(name = "book_name") String bookName,
                                   @RequestParam Integer quantity) {

        LOGGER.info("purchase book: " + bookName + ", quantity: " + quantity);

        Optional<Book> book = findBook(bookName);

        if (!book.isPresent()) {
            return buildHttpResponse(HttpStatus.NOT_FOUND, new Response("Book was not found."));
        }

        PurchaseStatus purchaseStatus = attemptBookPurchase(book.get(), quantity);

        return buildHttpResponse(HttpStatus.OK, responseFor(purchaseStatus));
    }

    private ResponseEntity<Response> buildHttpResponse(HttpStatus httpStatus, Response response) {
        return ResponseEntity.status(httpStatus).body(response);
    }

    private Optional<Book> findBook(String bookName) {
        return bookRepository.get(bookName);
    }

    private PurchaseStatus attemptBookPurchase(Book book, Integer quantity) {
        return book.purchase(quantity);
    }

    private Response responseFor(PurchaseStatus status) {
        if (status instanceof PurchaseStatus.Successful) {
            return new Response("Thank you for your purchase!");
        } else if (status instanceof PurchaseStatus.OutOfStock) {
            return new Response("Sorry, we are out of stock.");
        } else {
            return null;
        }
    }
}
