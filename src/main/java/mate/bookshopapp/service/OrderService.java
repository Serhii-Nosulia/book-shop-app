package mate.bookshopapp.service;

import java.util.List;
import mate.bookshopapp.dto.order.CreateOrderDto;
import mate.bookshopapp.dto.order.OrderResponseDto;
import mate.bookshopapp.dto.order.UpdateOrderStatusDto;
import mate.bookshopapp.dto.order.item.OrderItemResponseDto;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto createOrder(CreateOrderDto createOrderDto, Long userId);

    List<OrderResponseDto> findAllByUser(Long userId, Pageable pageable);

    OrderResponseDto updateOrderStatus(UpdateOrderStatusDto updateOrderStatusDto, Long id);

    List<OrderItemResponseDto> findAllByOrderId(Long id, Pageable pageable);

    OrderItemResponseDto getOrderItemByOrderAndOrderItemIds(Long orderId, Long orderItemId);
}
