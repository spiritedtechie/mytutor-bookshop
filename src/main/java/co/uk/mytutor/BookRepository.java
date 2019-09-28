package co.uk.mytutor;

import co.uk.mytutor.model.Book;

public interface BookRepository {
    Book get(String name);
}
