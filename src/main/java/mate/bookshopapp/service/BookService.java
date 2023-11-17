package mate.bookshopapp.service;

import java.util.List;
import mate.bookshopapp.dto.BookDto;
import mate.bookshopapp.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    BookDto findById(Long id);

    List<BookDto> findAll();
}
