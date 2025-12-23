package uz.app.ecommerce.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CartItemResponseDto {
    private UUID id;
    private UUID variantId;
    private String productName;
    private String color;
    private String size;
    private BigDecimal price;
    private Integer quantity;
}