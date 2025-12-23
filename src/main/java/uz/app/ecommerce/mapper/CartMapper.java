package uz.app.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.app.ecommerce.entity.Cart;
import uz.app.ecommerce.entity.CartItem;
import uz.app.ecommerce.entity.dto.CartItemResponseDto;
import uz.app.ecommerce.entity.dto.CartResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "total", ignore = true) // посчитаем в сервисе
    CartResponseDto toDto(Cart cart);

    List<CartItemResponseDto> toItemDtoList(List<CartItem> items);

    @Mapping(target = "variantId", source = "variant.id")
    @Mapping(target = "productName", source = "variant.product.name")
    @Mapping(target = "color", source = "variant.color")
    @Mapping(target = "size", source = "variant.size")
    @Mapping(target = "price", source = "variant.product.price")
    CartItemResponseDto toItemDto(CartItem item);
}