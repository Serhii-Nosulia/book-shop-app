package mate.bookshopapp.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.bookshopapp.dto.book.BookDto;
import mate.bookshopapp.dto.book.BookDtoWithoutCategoryIds;
import mate.bookshopapp.dto.book.BookSearchParametersDto;
import mate.bookshopapp.dto.book.CreateBookRequestDto;
import mate.bookshopapp.dto.book.UpdateBookDto;
import mate.bookshopapp.exception.EntityNotFoundException;
import mate.bookshopapp.mapper.BookMapper;
import mate.bookshopapp.model.Book;
import mate.bookshopapp.repository.SpecificationBuilder;
import mate.bookshopapp.repository.book.BookRepository;
import mate.bookshopapp.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final SpecificationBuilder<Book> bookSpecificationBuilder;

    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findBookById(id).orElseThrow(
                () -> new EntityNotFoundException("Couldn't find book by id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAllBooks(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findBooksByCategoryId(Long id) {
        return bookRepository.findBooksByCategoryId(id).stream()
                .map(bookMapper::toBookDtoWithoutCategoryIds)
                .toList();
    }

    @Override
    public List<BookDto> searchBooks(BookSearchParametersDto bookSearchParametersDto) {
        return bookRepository.findAll(bookSpecificationBuilder.build(bookSearchParametersDto))
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public void updateById(Long id, UpdateBookDto bookDto) {
        Book bookById = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find book by id: " + id));
        bookById.setTitle(bookDto.title());
        bookById.setAuthor(bookDto.author());
        bookById.setIsbn(bookDto.isbn());
        bookById.setPrice(bookDto.price());
        bookById.setDescription(bookDto.description());
        bookById.setCoverImage(bookDto.coverImage());
        bookRepository.save(bookById);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
