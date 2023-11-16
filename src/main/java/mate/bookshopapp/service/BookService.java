package mate.bookshopapp.service;

import java.util.List;
import mate.bookshopapp.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
