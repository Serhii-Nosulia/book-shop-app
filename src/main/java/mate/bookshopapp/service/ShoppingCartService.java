package mate.bookshopapp.service;

import mate.bookshopapp.dto.cart.items.AddBookToShoppingCartDto;
import mate.bookshopapp.dto.cart.items.SetQuantityDto;
import mate.bookshopapp.dto.shopping.cart.ShoppingCartResponseDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto findShoppingCartByUserId(Long id);

    AddBookToShoppingCartDto addBookToShoppingCart(Long userId,
                                                   AddBookToShoppingCartDto cartDto);

    SetQuantityDto updateBookQuantity(Long cartItemIdm, SetQuantityDto setQuantityDto);

    void deleteCartItem(Long id);
}
