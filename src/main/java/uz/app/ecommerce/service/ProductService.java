package uz.app.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.app.ecommerce.entity.Category;
import uz.app.ecommerce.entity.Product;
import uz.app.ecommerce.entity.ProductVariant;
import uz.app.ecommerce.entity.dto.ProductCreateDto;
import uz.app.ecommerce.entity.dto.ProductListItemDto;
import uz.app.ecommerce.entity.dto.ProductResponseDto;
import uz.app.ecommerce.entity.dto.ProductUpdateDto;
import uz.app.ecommerce.repository.CategoryRepository;
import uz.app.ecommerce.repository.ProductRepository;
import uz.app.ecommerce.repository.ProductVariantRepository;
import uz.app.ecommerce.mapper.ProductMapper;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductVariantRepository variantRepository;
    private final ProductMapper productMapper;

    // ➕ Create product with variants
    public ProductResponseDto create(ProductCreateDto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = productMapper.toEntity(dto);
        product.setCategory(category);

        Product saved = productRepository.save(product);

        // variants
        List<ProductVariant> variants = dto.getVariants().stream()
                .map(v -> {
                    ProductVariant variant = productMapper.toVariant(v);
                    variant.setProduct(saved);
                    return variant;
                })
                .toList();

        variantRepository.saveAll(variants);
        saved.setVariants(variants);

        return productMapper.toDto(saved);
    }

    // ✏️ Update product
    public ProductResponseDto update(UUID id, ProductUpdateDto dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        productMapper.update(dto, product);
        product.setCategory(category);

        return productMapper.toDto(product);
    }

    // 🔍 Get by id
    @Transactional(readOnly = true)
    public ProductResponseDto getById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toDto(product);
    }

    // 📋 Get all (list page)
    @Transactional(readOnly = true)
    public List<ProductListItemDto> getAll() {
        return productMapper.toListItemList(productRepository.findAll());
    }

    // 🗑 Delete
    public void delete(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}