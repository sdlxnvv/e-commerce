package uz.app.ecommerce.entity.dto;

import lombok.Data;
import uz.app.ecommerce.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderResponseDto {
    private UUID id;
    private OrderStatus status;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private List<OrderItemResponseDto> items;
}