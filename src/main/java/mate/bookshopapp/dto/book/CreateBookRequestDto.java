package mate.bookshopapp.dto.book;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;
import mate.bookshopapp.validation.isbn.Isbn;

public record CreateBookRequestDto(@NotBlank String title,
                                   @NotBlank String author,
                                   @Isbn String isbn,
                                   @NotNull @Min(0) BigDecimal price,
                                   @Max(9000) String description,
                                   String coverImage,
                                   Set<Long> categoryIds
) {
}
