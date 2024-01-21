package mate.bookshopapp.service;

import mate.bookshopapp.dto.cart.items.AddBookToShoppingCartDto;
import mate.bookshopapp.dto.cart.items.SetQuantityDto;
import mate.bookshopapp.dto.shopping.cart.ShoppingCartResponseDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto findShoppingCartByUserId(Long id);

    AddBookToShoppingCartDto addBookToShoppingCart(Long userId,
                                                   AddBookToShoppingCartDto cartDto);

    SetQuantityDto updateBookQuantity(Long userId, Long cartItemId, SetQuantityDto setQuantityDto);

    void deleteCartItem(Long userId, Long cartItemId);
}
