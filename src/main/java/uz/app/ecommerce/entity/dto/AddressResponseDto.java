package uz.app.ecommerce.entity.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AddressResponseDto {
    private UUID id;
    private String country;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String phone;
}