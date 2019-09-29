package co.uk.mytutor.controller;

import co.uk.mytutor.model.PurchaseStatus;
import co.uk.mytutor.service.BookPurchaser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);

    private BookPurchaser bookPurchaser;

    public PurchaseController(BookPurchaser bookPurchaser) {
        this.bookPurchaser = bookPurchaser;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity purchase(@RequestParam(name = "book_name") String bookName,
                                   @RequestParam Integer quantity) {

        LOGGER.info("purchase book: " + bookName + ", quantity: " + quantity);

        var purchaseStatus = bookPurchaser.purchase(bookName, quantity);

        return responseFor(purchaseStatus);
    }

    private ResponseEntity<Response> responseFor(PurchaseStatus status) {
        if (status instanceof PurchaseStatus.Successful) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Thank you for your purchase!"));
        } else if (status instanceof PurchaseStatus.OutOfStock) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Sorry, we are out of stock."));
        } else if (status instanceof PurchaseStatus.NonExistentBook) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Book was not found."));
        } else {
            return null;
        }
    }
}
