package uz.app.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.app.ecommerce.entity.*;
import uz.app.ecommerce.entity.dto.OrderCreateDto;
import uz.app.ecommerce.entity.dto.OrderResponseDto;
import uz.app.ecommerce.entity.enums.OrderStatus;
import uz.app.ecommerce.mapper.OrderMapper;
import uz.app.ecommerce.repository.*;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    // ⚠️ временно — потом заменим на SecurityContext
    private User getCurrentUser() {
        return userRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public OrderResponseDto create(OrderCreateDto dto) {
        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart is empty"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Address address = addressRepository.findById(dto.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setStatus(OrderStatus.NEW);

        List<OrderItem> items = cart.getItems().stream().map(ci -> {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setVariant(ci.getVariant());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getVariant().getProduct().getPrice());
            return oi;
        }).toList();

        order.setItems(items);

        BigDecimal total = items.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotal(total);

        Order saved = orderRepository.save(order);

        // 🧹 очистим корзину
        cart.getItems().clear();
        cartRepository.save(cart);

        return orderMapper.toDto(saved);
    }

    public List<OrderResponseDto> myOrders() {
        User user = getCurrentUser();
        return orderRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }
}