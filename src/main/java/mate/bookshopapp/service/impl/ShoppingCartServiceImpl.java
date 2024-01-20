package mate.bookshopapp.service.impl;

import lombok.RequiredArgsConstructor;
import mate.bookshopapp.dto.cart.items.AddBookToShoppingCartDto;
import mate.bookshopapp.dto.cart.items.SetQuantityDto;
import mate.bookshopapp.dto.shopping.cart.ShoppingCartResponseDto;
import mate.bookshopapp.exception.EntityNotFoundException;
import mate.bookshopapp.mapper.CartItemMapper;
import mate.bookshopapp.mapper.ShoppingCartMapper;
import mate.bookshopapp.model.CartItem;
import mate.bookshopapp.model.ShoppingCart;
import mate.bookshopapp.repository.book.BookRepository;
import mate.bookshopapp.repository.cart.items.CartItemsRepository;
import mate.bookshopapp.repository.shopping.cart.ShoppingCartRepository;
import mate.bookshopapp.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final Integer PLUS_ONE_QUANTITY = 1;

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final BookRepository bookRepository;
    private final CartItemMapper cartItemMapper;
    private final CartItemsRepository cartItemsRepository;

    @Override
    public ShoppingCartResponseDto findShoppingCartByUserId(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Couldn't find shopping cart by id " + id)
        );
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public AddBookToShoppingCartDto addBookToShoppingCart(Long userId,
                                                          AddBookToShoppingCartDto cartDto) {
        ShoppingCart shoppingCart = getShoppingCartByUserId(userId);

        for (CartItem cartItem : shoppingCart.getCartItems()) {
            if (cartItem.getBook().getId().equals(cartDto.bookId())) {
                cartItem.setQuantity(cartItem.getQuantity() + PLUS_ONE_QUANTITY);
                return cartItemMapper.toAddBookToShoppingCartDto(
                        cartItemsRepository.save(cartItem)
                );
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setBook(bookRepository.findBookById(cartDto.bookId()).orElseThrow(
                () -> new EntityNotFoundException("Couldn't find book by id " + cartDto.bookId()))
        );
        cartItem.setQuantity(cartDto.quantity());
        cartItem.setShoppingCart(shoppingCart);
        return cartItemMapper.toAddBookToShoppingCartDto(
                cartItemsRepository.save(cartItem)
        );
    }

    @Override
    public SetQuantityDto updateBookQuantity(Long userId,
                                             Long cartItemId,
                                             SetQuantityDto setQuantityDto) {
        ShoppingCart shoppingCart = getShoppingCartByUserId(userId);

        for (CartItem cartItem : shoppingCart.getCartItems()) {
            if (cartItem.getId().equals(cartItemId)) {
                cartItem.setQuantity(setQuantityDto.quantity());
                return cartItemMapper.toSetQuantityDto(cartItemsRepository.save(cartItem));
            }
        }
        throw new EntityNotFoundException("Couldn't find cartItem by id " + cartItemId);
    }

    @Override
    public void deleteCartItem(Long userId, Long cartItemId) {
        ShoppingCart shoppingCart = getShoppingCartByUserId(userId);

        for (CartItem cartItem : shoppingCart.getCartItems()) {
            if (cartItem.getId().equals(cartItemId)) {
                cartItemsRepository.deleteById(cartItemId);
            }
        }
        throw new EntityNotFoundException("Couldn't find cartItem by id " + cartItemId);
    }

    private ShoppingCart getShoppingCartByUserId(Long userId) {
        return shoppingCartRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Couldn't find shopping cart by id " + userId)
        );
    }
}
