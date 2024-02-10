package mate.bookshopapp.mapper;

import mate.bookshopapp.config.MapperConfig;
import mate.bookshopapp.dto.order.OrderResponseDto;
import mate.bookshopapp.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    OrderResponseDto toDto(Order order);
}
