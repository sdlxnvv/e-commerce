package uz.app.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.app.ecommerce.entity.Address;
import uz.app.ecommerce.entity.dto.*;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Address toEntity(AddressCreateDto dto);

    @Mapping(target = "user", ignore = true)
    void update(AddressUpdateDto dto, @MappingTarget Address address);

    AddressResponseDto toDto(Address address);
}