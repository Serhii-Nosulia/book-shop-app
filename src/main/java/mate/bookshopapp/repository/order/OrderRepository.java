package mate.bookshopapp.repository.order;

import java.util.Optional;
import mate.bookshopapp.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "orderItems")
    Page<Order> findAllByUserId(Long id, Pageable pageable);

    @EntityGraph(attributePaths = "orderItems")
    Optional<Order> findById(Long id);
}
