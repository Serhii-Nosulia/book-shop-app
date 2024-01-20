package mate.bookshopapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity(name = "shopping_carts")
@Where(clause = "is_deleted=FALSE")
@SQLDelete(sql = "UPDATE shopping_carts SET is_deleted=TRUE WHERE  id=?")
public class ShoppingCart {
    @Id
    private Long id;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(mappedBy = "shoppingCart")
    private Set<CartItem> cartItems;
}
