package uz.app.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.ecommerce.entity.dto.OrderCreateDto;
import uz.app.ecommerce.entity.dto.OrderResponseDto;
import uz.app.ecommerce.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(
            @Valid @RequestBody OrderCreateDto dto
    ) {
        return ResponseEntity.ok(orderService.create(dto));
    }

    @GetMapping("/my")
    public ResponseEntity<List<OrderResponseDto>> myOrders() {
        return ResponseEntity.ok(orderService.myOrders());
    }
}