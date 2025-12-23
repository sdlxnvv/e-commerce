package uz.app.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.ecommerce.entity.dto.CartItemCreateDto;
import uz.app.ecommerce.entity.dto.CartResponseDto;
import uz.app.ecommerce.service.CartService;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponseDto> getMyCart() {
        return ResponseEntity.ok(cartService.getMyCart());
    }

    @PostMapping("/items")
    public ResponseEntity<CartResponseDto> addItem(
            @Valid @RequestBody CartItemCreateDto dto
    ) {
        return ResponseEntity.ok(cartService.addItem(dto));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable UUID id) {
        cartService.removeItem(id);
        return ResponseEntity.noContent().build();
    }
}