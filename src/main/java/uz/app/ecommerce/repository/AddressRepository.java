package uz.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.ecommerce.entity.Address;
import uz.app.ecommerce.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository   extends JpaRepository<Address, UUID> {
    Address findByIdAndUser(UUID id, User user);

    List<Address> findAllByUser(User user);
}
