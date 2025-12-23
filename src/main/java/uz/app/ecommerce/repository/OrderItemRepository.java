package uz.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.ecommerce.entity.OrderItem;

import java.util.UUID;

public interface OrderItemRepository  extends JpaRepository<OrderItem, UUID> {
}
