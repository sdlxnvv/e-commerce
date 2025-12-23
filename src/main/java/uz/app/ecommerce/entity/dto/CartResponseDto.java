package uz.app.ecommerce.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class CartResponseDto {
    private UUID id;
    private List<CartItemResponseDto> items;
    private BigDecimal total;
}