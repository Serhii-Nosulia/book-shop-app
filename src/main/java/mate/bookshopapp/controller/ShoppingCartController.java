package mate.bookshopapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.bookshopapp.dto.cart.items.AddBookToShoppingCartDto;
import mate.bookshopapp.dto.cart.items.SetQuantityDto;
import mate.bookshopapp.dto.shopping.cart.ShoppingCartResponseDto;
import mate.bookshopapp.model.User;
import mate.bookshopapp.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    ShoppingCartResponseDto getShoppingCartByUserId(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.findShoppingCartByUserId(user.getId());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    AddBookToShoppingCartDto addBookToShoppingCar(
            Authentication authentication,
            @RequestBody @Valid AddBookToShoppingCartDto addBookToShoppingCartDto) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addBookToShoppingCart(user.getId(), addBookToShoppingCartDto);
    }

    @PutMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    SetQuantityDto updateBookQuantity(Authentication authentication, @PathVariable Long cartItemId,
                                      @RequestBody @Valid SetQuantityDto setQuantityDto) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateBookQuantity(user.getId(), cartItemId, setQuantityDto);
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Authentication authentication, @PathVariable Long cartItemId) {
        User user = (User) authentication.getPrincipal();
        shoppingCartService.deleteCartItem(user.getId(), cartItemId);
    }
}
