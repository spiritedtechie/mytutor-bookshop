package co.uk.mytutor.repository;

import co.uk.mytutor.model.Book;

import java.util.Optional;

public interface BookRepository {
    Optional<Book> get(String name);
}
