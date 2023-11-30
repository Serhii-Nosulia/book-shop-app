package mate.bookshopapp.dto.book;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import mate.bookshopapp.validation.Isbn;

public record CreateBookRequestDto(@NotNull String title,
                                   @NotNull String author,
                                   @Isbn String isbn,
                                   @NotNull @Min(0) BigDecimal price,
                                   @Max(9000) String description,
                                   String coverImage) {

}
