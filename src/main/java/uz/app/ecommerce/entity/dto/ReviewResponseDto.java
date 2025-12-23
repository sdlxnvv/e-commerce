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
public class ReviewResponseDto {

    private UUID id;
    private Integer rating;
    private String comment;
    private UUID productId;
    private UUID userId;
}