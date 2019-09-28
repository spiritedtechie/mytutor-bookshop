package co.uk.mytutor.controller;

import co.uk.mytutor.BookRepository;
import co.uk.mytutor.model.Book;
import co.uk.mytutor.model.PurchaseStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository bookRepository;

    @Before
    public void setup() {
    }

    @Test
    public void testPurchaseReturnsOutOfStockMessage() throws Exception {
        String bookName = "A";
        Integer quantity = 3;

        Book mockedBook = mock(Book.class);
        when(bookRepository.get(bookName)).thenReturn(mockedBook);
        when(mockedBook.purchase(quantity)).thenReturn(new PurchaseStatus.OutOfStock());

        var resultActions = mvc.perform(
                post("/order")
                        .param("book_name", bookName)
                        .param("quantity", quantity.toString())
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Sorry, we are out of stock.")));
    }

    @Test
    public void testPurchaseReturnsSuccessMessage() throws Exception {
        String bookName = "A";
        Integer quantity = 3;

        Book mockedBook = mock(Book.class);
        when(bookRepository.get(bookName)).thenReturn(mockedBook);
        when(mockedBook.purchase(quantity)).thenReturn(new PurchaseStatus.Successful());

        var resultActions = mvc.perform(
                post("/order")
                        .param("book_name", bookName)
                        .param("quantity", quantity.toString())
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Thank you for your purchase!")));
    }
}
