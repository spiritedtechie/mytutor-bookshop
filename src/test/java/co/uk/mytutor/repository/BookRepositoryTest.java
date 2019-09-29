package co.uk.mytutor.repository;

import co.uk.mytutor.model.Book;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRepositoryTest {

    private BookRepository bookRepository;

    @Before
    public void setup() {
        bookRepository = new InMemoryBookRepository();
    }

    @Test
    public void testGetBookA() {
        var bookName = "A";
        var initialStock = 10;
        var price = 25;

        var book = bookRepository.get(bookName).get();

        var expectedBook = new Book(bookName, initialStock, price);
        assertThat(book).isEqualTo(expectedBook);
    }

    @Test
    public void testGetBookB() {
        var bookName = "B";
        var initialStock = 10;
        var price = 20;

        var book = bookRepository.get(bookName).get();

        var expectedBook = new Book(bookName, initialStock, price);
        assertThat(book).isEqualTo(expectedBook);
    }

    @Test
    public void testGetBookC() {
        var bookName = "C";
        var initialStock = 10;
        var price = 23;

        var book = bookRepository.get(bookName).get();

        var expectedBook = new Book(bookName, initialStock, price);
        assertThat(book).isEqualTo(expectedBook);
    }

    @Test
    public void testGetBookD() {
        var bookName = "D";
        var initialStock = 10;
        var price = 30;

        var book = bookRepository.get(bookName).get();

        var expectedBook = new Book(bookName, initialStock, price);
        assertThat(book).isEqualTo(expectedBook);
    }

    @Test
    public void testGetBookE() {
        var bookName = "E";
        var initialStock = 10;
        var price = 27;

        var book = bookRepository.get(bookName).get();

        var expectedBook = new Book(bookName, initialStock, price);
        assertThat(book).isEqualTo(expectedBook);
    }

    @Test
    public void testGetUnknownBook() {
        var bookOptional = bookRepository.get("unknown");

        assertThat(bookOptional).isEmpty();
    }

}
