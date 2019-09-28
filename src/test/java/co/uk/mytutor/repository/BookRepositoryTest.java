package co.uk.mytutor.repository;

import co.uk.mytutor.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

public class BookRepositoryTest {

    private BookRepository bookRepository;

    @Before
    public void setup() {
        bookRepository = new InMemoryBookRepository();
    }

    @Test
    public void testGetBookA() {
        String bookName = "A";
        int initialStock = 10;
        BigDecimal price = BigDecimal.valueOf(25);

        Book book = bookRepository.get(bookName).get();

        Book expectedBook = new Book(bookName, initialStock, price);
        Assertions.assertThat(book).isEqualTo(expectedBook);
    }

    @Test
    public void testGetBookB() {
        String bookName = "B";
        int initialStock = 10;
        BigDecimal price = BigDecimal.valueOf(20);

        Book book = bookRepository.get(bookName).get();

        Book expectedBook = new Book(bookName, initialStock, price);
        Assertions.assertThat(book).isEqualTo(expectedBook);
    }

    @Test
    public void testGetBookC() {
        String bookName = "C";
        int initialStock = 10;
        BigDecimal price = BigDecimal.valueOf(23);

        Book book = bookRepository.get(bookName).get();

        Book expectedBook = new Book(bookName, initialStock, price);
        Assertions.assertThat(book).isEqualTo(expectedBook);
    }

    @Test
    public void testGetBookD() {
        String bookName = "D";
        int initialStock = 10;
        BigDecimal price = BigDecimal.valueOf(30);

        Book book = bookRepository.get(bookName).get();

        Book expectedBook = new Book(bookName, initialStock, price);
        Assertions.assertThat(book).isEqualTo(expectedBook);
    }

    @Test
    public void testGetBookE() {
        String bookName = "E";
        int initialStock = 10;
        BigDecimal price = BigDecimal.valueOf(27);

        Book book = bookRepository.get(bookName).get();

        Book expectedBook = new Book(bookName, initialStock, price);
        Assertions.assertThat(book).isEqualTo(expectedBook);
    }

    @Test
    public void testGetUnknownBook() {

        Optional<Book> book = bookRepository.get("unknown");

        Assertions.assertThat(book).isEmpty();
    }

}
