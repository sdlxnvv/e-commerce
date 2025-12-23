package uz.app.ecommerce.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double rating;

    private CategoryResponseDto category;
    private List<ProductVariantResponseDto> variants;
    private List<String> images;
}