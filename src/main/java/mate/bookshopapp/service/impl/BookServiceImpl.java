package mate.bookshopapp.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.bookshopapp.dto.BookDto;
import mate.bookshopapp.dto.CreateBookRequestDto;
import mate.bookshopapp.exception.EntityNotFoundException;
import mate.bookshopapp.mapper.BookMapper;
import mate.bookshopapp.model.Book;
import mate.bookshopapp.repository.BookRepository;
import mate.bookshopapp.service.BookService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Couldn't find book by id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
