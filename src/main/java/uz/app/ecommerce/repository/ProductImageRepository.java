package uz.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.ecommerce.entity.ProductImage;

import java.util.UUID;

public interface ProductImageRepository   extends JpaRepository<ProductImage, UUID> {
}
