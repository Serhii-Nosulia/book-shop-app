package mate.bookshopapp.dto.cart.items;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SetQuantityDto(@NotNull @Min(value = 1) Integer quantity) {
}
