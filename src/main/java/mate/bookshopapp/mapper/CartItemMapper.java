package mate.bookshopapp.mapper;

import mate.bookshopapp.config.MapperConfig;
import mate.bookshopapp.dto.cart.items.AddBookToShoppingCartDto;
import mate.bookshopapp.dto.cart.items.CartItemsResponseDto;
import mate.bookshopapp.dto.cart.items.SetQuantityDto;
import mate.bookshopapp.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "bookTitle", source = "book.title")
    CartItemsResponseDto toDto(CartItem cartItem);

    @Mapping(target = "bookId", source = "book.id")
    AddBookToShoppingCartDto toAddBookToShoppingCartDto(CartItem cartItem);

    SetQuantityDto toSetQuantityDto(CartItem cartItem);
}
