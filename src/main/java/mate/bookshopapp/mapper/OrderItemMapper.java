package mate.bookshopapp.mapper;

import mate.bookshopapp.config.MapperConfig;
import mate.bookshopapp.dto.order.item.OrderItemResponseDto;
import mate.bookshopapp.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    OrderItemResponseDto toDto(OrderItem orderItem);
}
