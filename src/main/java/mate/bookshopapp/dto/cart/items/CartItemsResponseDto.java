package mate.bookshopapp.dto.cart.items;

public record CartItemsResponseDto(Long id,
                                   Long bookId,
                                   String bookTitle,
                                   Integer quantity
) {
}
