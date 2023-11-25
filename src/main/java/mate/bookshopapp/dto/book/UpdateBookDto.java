package mate.bookshopapp.dto.book;

import java.math.BigDecimal;

public record UpdateBookDto(String title,
                            String author,
                            String isbn,
                            BigDecimal price,
                            String description,
                            String coverImage) {
}
