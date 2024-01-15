package mate.bookshopapp.service;

import java.util.List;
import mate.bookshopapp.dto.book.BookDto;
import mate.bookshopapp.dto.book.BookDtoWithoutCategoryIds;
import mate.bookshopapp.dto.book.BookSearchParametersDto;
import mate.bookshopapp.dto.book.CreateBookRequestDto;
import mate.bookshopapp.dto.book.UpdateBookDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    BookDto findById(Long id);

    List<BookDto> findAll(Pageable pageable);

    List<BookDtoWithoutCategoryIds> findBooksByCategoryId(Long id);

    List<BookDto> searchBooks(BookSearchParametersDto bookSearchParametersDto);

    void updateById(Long id, UpdateBookDto updateBookDto);

    void deleteById(Long id);
}
