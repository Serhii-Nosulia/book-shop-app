package mate.bookshopapp.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.bookshopapp.dto.order.CreateOrderDto;
import mate.bookshopapp.dto.order.OrderResponseDto;
import mate.bookshopapp.dto.order.UpdateOrderStatusDto;
import mate.bookshopapp.dto.order.item.OrderItemResponseDto;
import mate.bookshopapp.exception.DataNotFoundException;
import mate.bookshopapp.exception.EntityNotFoundException;
import mate.bookshopapp.mapper.OrderItemMapper;
import mate.bookshopapp.mapper.OrderMapper;
import mate.bookshopapp.model.CartItem;
import mate.bookshopapp.model.Order;
import mate.bookshopapp.model.OrderItem;
import mate.bookshopapp.model.ShoppingCart;
import mate.bookshopapp.repository.cart.items.CartItemsRepository;
import mate.bookshopapp.repository.order.OrderRepository;
import mate.bookshopapp.repository.order.item.OrderItemRepository;
import mate.bookshopapp.repository.shopping.cart.ShoppingCartRepository;
import mate.bookshopapp.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemsRepository cartItemsRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderResponseDto createOrder(CreateOrderDto createOrderDto, Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Couldn't find shopping cart by id " + userId)
        );
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        if (cartItems.isEmpty()) {
            throw new DataNotFoundException("Shopping cart is empty");
        }

        Order order = new Order();
        order.setUser(shoppingCart.getUser());
        order.setStatus(Order.Status.NEW);
        order.setTotal(calculateTotal(cartItems));
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(createOrderDto.shippingAddress());
        order.setOrderItems(collectOrderItems(cartItems, order));

        cartItemsRepository.deleteAll(cartItems);

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderResponseDto> findAllByUser(Long userId, Pageable pageable) {
        return orderRepository.findAllByUserId(userId, pageable).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderResponseDto updateOrderStatus(UpdateOrderStatusDto updateOrderStatusDto,
                                                  Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Couldn't find order by id " + id)
        );
        order.setStatus(updateOrderStatusDto.status());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderItemResponseDto> findAllByOrderId(Long id, Pageable pageable) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Couldn't find order by id " + id)
        );

        Set<OrderItem> orderItems = order.getOrderItems();
        if (orderItems.isEmpty()) {
            throw new DataNotFoundException("Order is empty");
        }

        return orderItems.stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto getOrderItemByOrderAndOrderItemIds(Long orderId,
                                                                   Long orderItemId) {
        OrderItem orderItem = orderItemRepository
                .findByOrder_IdAndId(orderId, orderItemId).orElseThrow(
                        () -> new EntityNotFoundException("Couldn't find order item by id "
                                + orderItemId)
        );
        return orderItemMapper.toDto(orderItem);
    }

    private Set<OrderItem> collectOrderItems(Set<CartItem> cartItems, Order order) {
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setPrice(cartItem.getBook().getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    private BigDecimal calculateTotal(Set<CartItem> cartItems) {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            total = total.add(cartItem.getBook().getPrice());
        }
        return total;
    }
}
