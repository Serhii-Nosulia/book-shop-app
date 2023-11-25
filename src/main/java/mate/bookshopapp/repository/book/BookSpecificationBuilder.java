package mate.bookshopapp.repository.book;

import lombok.RequiredArgsConstructor;
import mate.bookshopapp.dto.book.BookSearchParametersDto;
import mate.bookshopapp.model.Book;
import mate.bookshopapp.repository.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String ISBN = "isbn";
    private final BookSpecificationProviderManager bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto bookSearchParametersDto) {
        Specification<Book> specification = Specification.where(null);
        if (bookSearchParametersDto.title() != null
                && bookSearchParametersDto.title().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(TITLE)
                    .getSpecification(bookSearchParametersDto.title()));
        }
        if (bookSearchParametersDto.author() != null
                && bookSearchParametersDto.author().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(AUTHOR)
                    .getSpecification(bookSearchParametersDto.author()));
        }
        if (bookSearchParametersDto.isbn() != null
                && bookSearchParametersDto.isbn().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(ISBN)
                    .getSpecification(bookSearchParametersDto.isbn()));
        }

        return specification;
    }
}
