package uz.app.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.app.ecommerce.entity.User;
import uz.app.ecommerce.entity.dto.*;
import uz.app.ecommerce.entity.enums.Role;
import uz.app.ecommerce.repository.UserRepository;
import uz.app.ecommerce.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthResponseDto register(RegisterRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User u = new User();
        u.setEmail(dto.getEmail());
        u.setPassword(encoder.encode(dto.getPassword()));
        u.setRole(Role.USER);
        userRepository.save(u);
        return new AuthResponseDto(jwtService.generate(u.getEmail()));
    }

    public AuthResponseDto login(AuthRequestDto dto) {
        User u = userRepository.findByEmail(dto.getEmail());
        if (!encoder.matches(dto.getPassword(), u.getPassword())) {
            throw new RuntimeException("Bad credentials");
        }
        return new AuthResponseDto(jwtService.generate(u.getEmail()));
    }
}