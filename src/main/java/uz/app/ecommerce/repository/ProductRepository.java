package uz.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.ecommerce.entity.Product;

import java.util.UUID;

public interface ProductRepository   extends JpaRepository<Product, UUID> {
}
