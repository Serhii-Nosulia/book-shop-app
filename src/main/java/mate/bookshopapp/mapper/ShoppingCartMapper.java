package mate.bookshopapp.mapper;

import mate.bookshopapp.config.MapperConfig;
import mate.bookshopapp.dto.shopping.cart.ShoppingCartResponseDto;
import mate.bookshopapp.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    @Mapping(target = "userId", source = "user.id")
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);
}
