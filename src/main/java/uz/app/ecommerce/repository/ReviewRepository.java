package uz.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.ecommerce.entity.Review;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface ReviewRepository  extends JpaRepository<Review, UUID> {
    List<Review> findByProductId(UUID productId);
}
