package mate.bookshopapp.service;

import java.util.List;
import mate.bookshopapp.dto.BookDto;
import mate.bookshopapp.dto.CreateBookRequestDto;
import mate.bookshopapp.dto.UpdateBookDto;

public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    BookDto findById(Long id);

    List<BookDto> findAll();

    void updateById(Long id, UpdateBookDto updateBookDto);

    void deleteById(Long id);
}
