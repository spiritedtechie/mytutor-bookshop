package co.uk.mytutor.controller;

import co.uk.mytutor.BookRepository;
import co.uk.mytutor.model.Book;
import co.uk.mytutor.model.PurchaseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);

    private BookRepository bookRepository;

    public PurchaseController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public Response purchase(@RequestParam(name = "book_name") String bookName,
                             @RequestParam Integer quantity) {

        LOGGER.info("purchase book: " + bookName + ", quantity: " + quantity);

        PurchaseStatus purchaseStatus = attemptBookPurchase(bookName, quantity);

        return responseFor(purchaseStatus);
    }

    private PurchaseStatus attemptBookPurchase(@RequestParam(name = "book_name") String bookName, @RequestParam Integer quantity) {
        Book book = bookRepository.get(bookName);
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
