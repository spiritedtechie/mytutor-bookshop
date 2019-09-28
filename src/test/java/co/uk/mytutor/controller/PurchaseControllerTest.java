package co.uk.mytutor.controller;

import co.uk.mytutor.model.PurchaseStatus;
import co.uk.mytutor.service.BookPurchaser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {

    private static final String BOOK_NAME = "A";
    private static final Integer QUANTITY = 3;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookPurchaser bookPurchaser;

    @Before
    public void setup() {
    }

    @Test
    public void testPurchaseReturnsOutOfStockMessage() throws Exception {
        when(bookPurchaser.purchase(BOOK_NAME, QUANTITY)).thenReturn(new PurchaseStatus.OutOfStock());

        var resultActions = mvc.perform(
                post("/order")
                        .param("book_name", BOOK_NAME)
                        .param("quantity", QUANTITY.toString())
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Sorry, we are out of stock.")));
    }

    @Test
    public void testPurchaseReturnsSuccessMessage() throws Exception {
        when(bookPurchaser.purchase(BOOK_NAME, QUANTITY)).thenReturn(new PurchaseStatus.Successful());

        var resultActions = mvc.perform(
                post("/order")
                        .param("book_name", BOOK_NAME)
                        .param("quantity", QUANTITY.toString())
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Thank you for your purchase!")));
    }

    @Test
    public void testPurchaseWhereBookNotFound() throws Exception {
        when(bookPurchaser.purchase(BOOK_NAME, QUANTITY)).thenReturn(new PurchaseStatus.NonExistentBook());

        var resultActions = mvc.perform(
                post("/order")
                        .param("book_name", BOOK_NAME)
                        .param("quantity", QUANTITY.toString())
        );

        resultActions
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Book was not found.")));
    }
}
