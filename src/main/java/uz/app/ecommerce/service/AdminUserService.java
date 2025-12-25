package uz.app.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.app.ecommerce.entity.User;
import uz.app.ecommerce.entity.dto.*;
import uz.app.ecommerce.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminUserService {

    private final UserRepository userRepository;

    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream().map(this::toDto).toList();
    }

    public UserResponseDto changeRole(UUID id, ChangeRoleDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(dto.getRole());
        return toDto(user);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    private UserResponseDto toDto(User u) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(u.getId());
        dto.setEmail(u.getEmail());
        dto.setRole(u.getRole());
        return dto;
    }
}