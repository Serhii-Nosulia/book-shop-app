package mate.bookshopapp.repository.book;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.bookshopapp.exception.SpecificationProviderNotFoundException;
import mate.bookshopapp.model.Book;
import mate.bookshopapp.repository.SpecificationProvider;
import mate.bookshopapp.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(()
                        -> new SpecificationProviderNotFoundException("Couldn't find correct "
                        + "specification provider for key: " + key));
    }
}
