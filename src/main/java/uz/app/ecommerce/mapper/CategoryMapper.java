package uz.app.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.app.ecommerce.entity.Category;
import uz.app.ecommerce.entity.dto.CategoryCreateDto;
import uz.app.ecommerce.entity.dto.CategoryResponseDto;
import uz.app.ecommerce.entity.dto.CategoryUpdateDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryCreateDto dto);

    void update(CategoryUpdateDto dto, @MappingTarget Category category);

    Category toEntity(CategoryUpdateDto dto);

    CategoryResponseDto toDto(Category category);
}