package mate.bookshopapp.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.bookshopapp.dto.category.CategoryRequestDto;
import mate.bookshopapp.dto.category.CategoryResponseDto;
import mate.bookshopapp.exception.EntityNotFoundException;
import mate.bookshopapp.mapper.CategoryMapper;
import mate.bookshopapp.model.Category;
import mate.bookshopapp.repository.category.CategoryRepository;
import mate.bookshopapp.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Couldn't find category by id " + id)
        );
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryResponseDto save(CategoryRequestDto requestDto) {
        Category category = categoryMapper.toEntity(requestDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto requestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Couldn't find category by id " + id)
        );
        category.setName(requestDto.name());
        category.setDescription(requestDto.description());
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
