package mate.bookshopapp.dto.shopping.cart;

import java.util.Set;
import mate.bookshopapp.dto.cart.items.CartItemsResponseDto;

public record ShoppingCartResponseDto(Long id,
                                      Long userId,
                                      Set<CartItemsResponseDto> cartItems
) {
}
