package uz.app.ecommerce.entity.dto;

import lombok.Data;
import uz.app.ecommerce.entity.enums.Role;

import java.util.UUID;

@Data
public class UserResponseDto {
    private UUID id;
    private String email;
    private Role role;
}