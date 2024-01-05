package mate.bookshopapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity(name = "roles")
@Where(clause = "is_deleted=FALSE ")
@SQLDelete(sql = "UPDATE roles SET is_delete = TRUE WHERE id =?")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;

    @Override
    public String getAuthority() {
        return name.name();
    }

    public enum RoleName {
        ROLE_USER,
        ROLE_ADMIN
    }
}
