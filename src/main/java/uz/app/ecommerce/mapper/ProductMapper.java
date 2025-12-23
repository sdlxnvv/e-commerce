package uz.app.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.app.ecommerce.entity.Product;
import uz.app.ecommerce.entity.ProductVariant;
import uz.app.ecommerce.entity.ProductImage;
import uz.app.ecommerce.entity.dto.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    // ➕ Create
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "rating", ignore = true)
    Product toEntity(ProductCreateDto dto);

    // ✏️ Update
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "rating", ignore = true)
    void update(ProductUpdateDto dto, @MappingTarget Product product);

    // 📤 Response
    ProductResponseDto toDto(Product product);

    List<ProductResponseDto> toDtoList(List<Product> products);

    // 🎨 Variant mapping
    ProductVariant toVariant(ProductVariantCreateDto dto);

    ProductVariantResponseDto toVariantDto(ProductVariant variant);

    List<ProductVariantResponseDto> toVariantDtoList(List<ProductVariant> variants);

    // 📋 For list page
    @Mapping(target = "image", ignore = true)
    ProductListItemDto toListItem(Product product);

    List<ProductListItemDto> toListItemList(List<Product> products);

    // 🖼 Custom mapping: ProductImage -> String (url)
    default String map(ProductImage image) {
        return image == null ? null : image.getUrl();
    }
}