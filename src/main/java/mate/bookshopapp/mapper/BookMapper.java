package mate.bookshopapp.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import mate.bookshopapp.config.MapperConfig;
import mate.bookshopapp.dto.book.BookDto;
import mate.bookshopapp.dto.book.BookDtoWithoutCategoryIds;
import mate.bookshopapp.dto.book.CreateBookRequestDto;
import mate.bookshopapp.model.Book;
import mate.bookshopapp.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    @Mapping(target = "categoryIds", ignore = true)
    BookDto toDto(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        Set<Long> setIds = book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet());

        bookDto.setCategoryIds(setIds);
    }

    BookDtoWithoutCategoryIds toBookDtoWithoutCategoryIds(Book book);

    @Mapping(target = "categories", ignore = true)
    Book toModel(CreateBookRequestDto createBookRequestDto);

    @AfterMapping
    default void setCategories(@MappingTarget Book book,
                               CreateBookRequestDto createBookRequestDto) {
        Set<Category> categories = createBookRequestDto.categoryIds().stream()
                .map(Category::new)
                .collect(Collectors.toSet());

        book.setCategories(categories);
    }
}
