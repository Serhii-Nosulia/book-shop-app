package mate.bookshopapp.dto.order.item;

public record OrderItemResponseDto(Long id,
                                   Long bookId,
                                   Integer quantity
) {
}
