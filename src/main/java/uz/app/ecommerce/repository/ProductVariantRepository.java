package uz.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.ecommerce.entity.ProductVariant;

import java.util.UUID;

public interface ProductVariantRepository   extends JpaRepository<ProductVariant, UUID> {
}
