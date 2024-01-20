package mate.bookshopapp.repository.cart.items;

import mate.bookshopapp.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItem, Long> {
}
