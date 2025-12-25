package uz.app.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.ecommerce.entity.dto.*;
import uz.app.ecommerce.service.AdminUserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(adminUserService.getAll());
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<UserResponseDto> changeRole(
            @PathVariable UUID id,
            @RequestBody ChangeRoleDto dto
    ) {
        return ResponseEntity.ok(adminUserService.changeRole(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        adminUserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}