package uz.app.ecommerce.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.app.ecommerce.entity.enums.Role;

@Data
public class ChangeRoleDto {
    @NotNull
    private Role role;
}