package co.uk.mytutor.repository;

import co.uk.mytutor.model.Book;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryBookRepository implements BookRepository {

    private final ConcurrentHashMap<String, Book> books = new ConcurrentHashMap<>();

    public InMemoryBookRepository() {
        Book a = new Book("A", 10, BigDecimal.valueOf(25));
        Book b = new Book("B", 10, BigDecimal.valueOf(20));
        Book c = new Book("C", 10, BigDecimal.valueOf(23));
        Book d = new Book("D", 10, BigDecimal.valueOf(30));
        Book e = new Book("E", 10, BigDecimal.valueOf(27));

        books.put(a.getName(), a);
        books.put(b.getName(), b);
        books.put(c.getName(), c);
        books.put(d.getName(), d);
        books.put(e.getName(), e);
    }

    @Override
    public Optional<Book> get(String name) {
        return Optional.ofNullable(books.get(name));
    }
}
