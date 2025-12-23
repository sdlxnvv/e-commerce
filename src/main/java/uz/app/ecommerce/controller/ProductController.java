package uz.app.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.ecommerce.entity.dto.ProductCreateDto;
import uz.app.ecommerce.entity.dto.ProductListItemDto;
import uz.app.ecommerce.entity.dto.ProductResponseDto;
import uz.app.ecommerce.entity.dto.ProductUpdateDto;
import uz.app.ecommerce.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ➕ Create (ADMIN)
    @PostMapping
    public ResponseEntity<ProductResponseDto> create(
            @RequestBody @Valid ProductCreateDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.create(dto));
    }

    // ✏️ Update (ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(
            @PathVariable UUID id,
            @RequestBody @Valid ProductUpdateDto dto) {

        return ResponseEntity.ok(productService.update(id, dto));
    }

    // 🔍 Get by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    // 📋 Get all (list)
    @GetMapping
    public ResponseEntity<List<ProductListItemDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    // 🗑 Delete (ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}