package mate.bookshopapp.dto.cart.items;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddBookToShoppingCartDto(Long bookId,
                                       @NotNull @Min(value = 0) Integer quantity) {
}
