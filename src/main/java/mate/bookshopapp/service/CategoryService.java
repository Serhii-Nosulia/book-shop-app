package mate.bookshopapp.service;

import java.util.List;
import mate.bookshopapp.dto.category.CategoryRequestDto;
import mate.bookshopapp.dto.category.CategoryResponseDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CategoryRequestDto requestDto);

    CategoryResponseDto update(Long id, CategoryRequestDto requestDto);

    void deleteById(Long id);
}
