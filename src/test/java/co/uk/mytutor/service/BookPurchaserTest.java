package co.uk.mytutor.service;

import co.uk.mytutor.BookRepository;
import co.uk.mytutor.model.Book;
import co.uk.mytutor.model.PurchaseStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookPurchaserTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private Book book;

    private BookPurchaser bookPurchaser;

    @Before
    public void setup() {
        this.bookPurchaser = new BookPurchaser(bookRepository);
    }

    @Test
    public void testCoordinatesPurchase() {
        String bookName = "A";
        int requiredQuantity = 3;

        when(bookRepository.get(bookName)).thenReturn(Optional.of(book));
        when(book.purchase(requiredQuantity)).thenReturn(new PurchaseStatus.Successful());

        bookPurchaser.purchase(bookName, requiredQuantity);

        Mockito.verify(bookRepository).get(bookName);
        Mockito.verify(book).purchase(requiredQuantity);
    }

    @Test
    public void testWhereBookNotFound() {
        String bookName = "A";
        int requiredQuantity = 3;

        when(bookRepository.get(bookName)).thenReturn(Optional.empty());

        PurchaseStatus result = bookPurchaser.purchase(bookName, requiredQuantity);

        assertThat(result).isInstanceOf(PurchaseStatus.NonExistentBook.class);
    }
}
