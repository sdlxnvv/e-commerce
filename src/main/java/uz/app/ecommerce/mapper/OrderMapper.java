package uz.app.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.app.ecommerce.entity.Order;
import uz.app.ecommerce.entity.OrderItem;
import uz.app.ecommerce.entity.dto.OrderItemResponseDto;
import uz.app.ecommerce.entity.dto.OrderResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "items", source = "items")
    OrderResponseDto toDto(Order order);

    @Mapping(target = "productName", source = "variant.product.name")
    @Mapping(target = "color", source = "variant.color")
    @Mapping(target = "size", source = "variant.size")
    OrderItemResponseDto toItemDto(OrderItem item);

    List<OrderItemResponseDto> toItemDtoList(List<OrderItem> items);
}