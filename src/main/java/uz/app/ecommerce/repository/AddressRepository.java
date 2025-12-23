package uz.app.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.ecommerce.entity.Address;

import java.util.UUID;

public interface AddressRepository   extends JpaRepository<Address, UUID> {
}
