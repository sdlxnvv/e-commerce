package uz.app.ecommerce.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressUpdateDto {

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String house;

    private String apartment;

    @NotBlank
    private String phone;
}