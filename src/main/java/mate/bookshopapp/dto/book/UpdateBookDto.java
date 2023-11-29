package mate.bookshopapp.dto.book;

import jakarta.validation.constraints.Min;
import java.math.BigDecimal;

public record UpdateBookDto(String title,
                            String author,
                            String isbn,
                            @Min(0) BigDecimal price,
                            String description,
                            String coverImage) {
}
