package mate.bookshopapp.dto.order;

import jakarta.validation.constraints.NotNull;
import mate.bookshopapp.model.Order;

public record UpdateOrderStatusDto(@NotNull Order.Status status) {
}
