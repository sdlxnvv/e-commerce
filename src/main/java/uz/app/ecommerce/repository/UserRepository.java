package uz.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.ecommerce.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
