package uz.app.ecommerce.entity.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ReviewCreateDto {

    @NotNull
    private UUID productId;

    @NotNull
    private UUID userId;

    @Min(1)
    @Max(5)
    private Integer rating;

    @NotBlank
    private String comment;
}