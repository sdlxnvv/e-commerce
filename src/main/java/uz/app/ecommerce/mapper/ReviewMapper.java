package uz.app.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.app.ecommerce.entity.Review;
import uz.app.ecommerce.entity.dto.*;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "user", ignore = true)
    Review toEntity(ReviewCreateDto dto);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "userId", source = "user.id")
    ReviewResponseDto toDto(Review review);
}