package uz.app.ecommerce.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderCreateDto {

    @NotNull
    private UUID addressId;
}