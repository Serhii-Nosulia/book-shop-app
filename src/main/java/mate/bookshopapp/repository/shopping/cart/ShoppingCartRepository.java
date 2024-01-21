package mate.bookshopapp.repository.shopping.cart;

import java.util.Optional;
import mate.bookshopapp.model.ShoppingCart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @EntityGraph(attributePaths = {"cartItems", "cartItems.book"})
    Optional<ShoppingCart> findById(Long id);
}
