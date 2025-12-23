package uz.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.ecommerce.entity.Category;

import java.util.UUID;

public interface CategoryRepository   extends JpaRepository<Category, UUID> {
}
