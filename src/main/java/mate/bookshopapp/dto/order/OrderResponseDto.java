package mate.bookshopapp.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import mate.bookshopapp.dto.order.item.OrderItemResponseDto;
import mate.bookshopapp.model.Order;

public record OrderResponseDto(Long id,
                               Long userId,
                               Set<OrderItemResponseDto> orderItems,
                               LocalDateTime orderDate,
                               BigDecimal total,
                               Order.Status status
) {
}
