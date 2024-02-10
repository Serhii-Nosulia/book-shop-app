package mate.bookshopapp.repository.order.item;

import java.util.Optional;
import mate.bookshopapp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByOrder_IdAndId(Long orderId, Long id);
}
