package uz.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.ecommerce.entity.CartItem;

import java.util.UUID;

public interface CartItemRepository  extends JpaRepository<CartItem, UUID> {
}
