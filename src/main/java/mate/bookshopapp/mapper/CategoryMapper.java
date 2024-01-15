package mate.bookshopapp.mapper;

import mate.bookshopapp.config.MapperConfig;
import mate.bookshopapp.dto.category.CategoryRequestDto;
import mate.bookshopapp.dto.category.CategoryResponseDto;
import mate.bookshopapp.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryResponseDto toDto(Category category);

    Category toEntity(CategoryRequestDto requestDto);
}
