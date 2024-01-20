package mate.bookshopapp.repository.shopping.cart;

import java.util.Optional;
import mate.bookshopapp.model.ShoppingCart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("FROM shopping_carts sc WHERE sc.id = :id")
    @EntityGraph(attributePaths = {"cartItems", "cartItems.book"})
    Optional<ShoppingCart> findShoppingCartById(@Param("id") Long id);
}
