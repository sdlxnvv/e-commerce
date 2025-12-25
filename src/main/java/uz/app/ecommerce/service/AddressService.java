package uz.app.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.app.ecommerce.entity.Address;
import uz.app.ecommerce.entity.User;
import uz.app.ecommerce.entity.dto.*;
import uz.app.ecommerce.mapper.AddressMapper;
import uz.app.ecommerce.repository.AddressRepository;
import uz.app.ecommerce.repository.UserRepository;
import uz.app.ecommerce.security.SecurityUtil;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    private User getCurrentUser() {
        String email = SecurityUtil.getCurrentUserEmail();
        return userRepository.findByEmail(email);
    }

    public AddressResponseDto create(AddressCreateDto dto) {
        User user = getCurrentUser();
        Address address = addressMapper.toEntity(dto);
        address.setUser(user);
        return addressMapper.toDto(addressRepository.save(address));
    }

    public AddressResponseDto update(UUID id, AddressUpdateDto dto) {
        User user = getCurrentUser();
        Address address = addressRepository.findByIdAndUser(id, user);
        addressMapper.update(dto, address);
        return addressMapper.toDto(address);
    }

    @Transactional(readOnly = true)
    public List<AddressResponseDto> myAddresses() {
        User user = getCurrentUser();
        return addressRepository.findAllByUser(user)
                .stream()
                .map(addressMapper::toDto)
                .toList();
    }

    public void delete(UUID id) {
        User user = getCurrentUser();
        Address address = addressRepository.findByIdAndUser(id, user);
        addressRepository.delete(address);
    }
}