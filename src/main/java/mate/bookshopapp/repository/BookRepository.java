package mate.bookshopapp.repository;

import java.util.List;
import mate.bookshopapp.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
