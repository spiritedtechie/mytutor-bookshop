package co.uk.mytutor.repository;

import co.uk.mytutor.model.Book;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryBookRepository implements BookRepository {

    private final ConcurrentHashMap<String, Book> books = new ConcurrentHashMap<>();

    public InMemoryBookRepository() {
        var bookA = new Book("A", 10, 25);
        var bookB = new Book("B", 10, 20);
        var bookC = new Book("C", 10, 23);
        var bookD = new Book("D", 10, 30);
        var bookE = new Book("E", 10, 27);

        books.put(bookA.getName(), bookA);
        books.put(bookB.getName(), bookB);
        books.put(bookC.getName(), bookC);
        books.put(bookD.getName(), bookD);
        books.put(bookE.getName(), bookE);
    }

    @Override
    public Optional<Book> get(String name) {
        return Optional.ofNullable(books.get(name));
    }
}
