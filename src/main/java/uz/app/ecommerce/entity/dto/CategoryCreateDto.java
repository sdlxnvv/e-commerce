package uz.app.ecommerce.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateDto {
    @NotBlank
    private String name;
}