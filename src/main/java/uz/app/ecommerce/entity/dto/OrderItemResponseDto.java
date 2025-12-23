package uz.app.ecommerce.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderItemResponseDto {
    private UUID id;
    private String productName;
    private String color;
    private String size;
    private BigDecimal price;
    private Integer quantity;
}