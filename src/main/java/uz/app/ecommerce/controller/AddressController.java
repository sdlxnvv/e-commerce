package uz.app.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.ecommerce.entity.dto.*;
import uz.app.ecommerce.service.AddressService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponseDto> create(
            @Valid @RequestBody AddressCreateDto dto
    ) {
        return ResponseEntity.ok(addressService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDto> update(
            @PathVariable UUID id,
            @Valid @RequestBody AddressUpdateDto dto
    ) {
        return ResponseEntity.ok(addressService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDto>> myAddresses() {
        return ResponseEntity.ok(addressService.myAddresses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}