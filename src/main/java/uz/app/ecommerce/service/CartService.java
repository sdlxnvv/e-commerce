package uz.app.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.app.ecommerce.entity.*;
import uz.app.ecommerce.entity.dto.CartItemCreateDto;
import uz.app.ecommerce.entity.dto.CartResponseDto;
import uz.app.ecommerce.mapper.CartMapper;
import uz.app.ecommerce.repository.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductVariantRepository variantRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    // ⚠️ временно: берём первого юзера из БД (потом будет из Security)
    private User getCurrentUser() {
        return userRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public CartResponseDto getMyCart() {
        User user = getCurrentUser();
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(new Cart(null, user, null)));

        CartResponseDto dto = cartMapper.toDto(cart);
        dto.setTotal(calculateTotal(cart));
        return dto;
    }

    public CartResponseDto addItem(CartItemCreateDto dto) {
        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUser(user);
                    return cartRepository.save(c);
                });

        ProductVariant variant = variantRepository.findById(dto.getVariantId())
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        Optional<CartItem> existing = cart.getItems().stream()
                .filter(i -> i.getVariant().getId().equals(variant.getId()))
                .findFirst();

        if (existing.isPresent()) {
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + dto.getQuantity());
        } else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setVariant(variant);
            item.setQuantity(dto.getQuantity());
            cart.getItems().add(item);
        }

        Cart saved = cartRepository.save(cart);
        CartResponseDto res = cartMapper.toDto(saved);
        res.setTotal(calculateTotal(saved));
        return res;
    }

    public void removeItem(UUID itemId) {
        cartItemRepository.deleteById(itemId);
    }

    private BigDecimal calculateTotal(Cart cart) {
        return cart.getItems().stream()
                .map(i -> i.getVariant().getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}