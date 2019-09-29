package co.uk.mytutor.service;

import co.uk.mytutor.model.Account;
import co.uk.mytutor.model.Book;
import co.uk.mytutor.model.PurchaseStatus;
import co.uk.mytutor.repository.AccountRepository;
import co.uk.mytutor.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookPurchaserTest {
    public static final String BOOK_NAME = "A";
    public static final int REQUIRED_QUANTITY = 3;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private Account mockAccount;

    @Mock
    private Book mockBook;

    private BookPurchaser bookPurchaser;

    @Before
    public void setup() {
        this.bookPurchaser = new BookPurchaser(accountRepository, bookRepository);
    }

    @Test
    public void testCreatesPurchase() {
        when(bookRepository.get(BOOK_NAME)).thenReturn(Optional.of(mockBook));
        when(mockBook.purchase(BookPurchaserTest.REQUIRED_QUANTITY)).thenReturn(PurchaseStatus.successful());
        when(accountRepository.get()).thenReturn(mockAccount);

        bookPurchaser.purchase(BOOK_NAME, BookPurchaserTest.REQUIRED_QUANTITY);

        Mockito.verify(bookRepository).get(BOOK_NAME);
        Mockito.verify(mockBook).purchase(BookPurchaserTest.REQUIRED_QUANTITY);
    }

    @Test
    public void testWhereBookNotFound() {
        when(bookRepository.get(BookPurchaserTest.BOOK_NAME)).thenReturn(Optional.empty());

        PurchaseStatus result = bookPurchaser.purchase(BookPurchaserTest.BOOK_NAME, BookPurchaserTest.REQUIRED_QUANTITY);

        assertThat(result).isInstanceOf(PurchaseStatus.NonExistentBook.class);
    }

    @Test
    public void testUpdatesBalanceIfSuccessfulPurchase() {
        when(bookRepository.get(BookPurchaserTest.BOOK_NAME)).thenReturn(Optional.of(mockBook));
        when(mockBook.purchase(BookPurchaserTest.REQUIRED_QUANTITY)).thenReturn(PurchaseStatus.successful());
        when(accountRepository.get()).thenReturn(mockAccount);

        bookPurchaser.purchase(BookPurchaserTest.BOOK_NAME, BookPurchaserTest.REQUIRED_QUANTITY);

        verify(mockAccount).recordPurchase(mockBook, BookPurchaserTest.REQUIRED_QUANTITY);
    }

    @Test
    public void testDoesNotUpdatesBalanceIfUnsuccessfulPurchase() {
        when(bookRepository.get(BOOK_NAME)).thenReturn(Optional.of(mockBook));
        when(mockBook.purchase(REQUIRED_QUANTITY)).thenReturn(PurchaseStatus.outOfStock());

        bookPurchaser.purchase(BOOK_NAME, REQUIRED_QUANTITY);

        verify(mockAccount, never()).recordPurchase(any(), any());
    }

}
