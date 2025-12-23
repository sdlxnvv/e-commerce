package uz.app.ecommerce.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantResponseDto {
    private UUID id;
    private String color;
    private String size;
    private Integer stock;
}