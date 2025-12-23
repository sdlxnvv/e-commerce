package uz.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.ecommerce.entity.Cart;
import uz.app.ecommerce.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository  extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByUser(User user);
}
