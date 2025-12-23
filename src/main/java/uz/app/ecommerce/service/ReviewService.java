package uz.app.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.app.ecommerce.entity.Product;
import uz.app.ecommerce.entity.Review;
import uz.app.ecommerce.entity.User;
import uz.app.ecommerce.entity.dto.*;
import uz.app.ecommerce.mapper.ReviewMapper;
import uz.app.ecommerce.repository.ProductRepository;
import uz.app.ecommerce.repository.ReviewRepository;
import uz.app.ecommerce.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;

    public ReviewResponseDto create(ReviewCreateDto dto) {

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = reviewMapper.toEntity(dto);
        review.setProduct(product);
        review.setUser(user);

        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Transactional(readOnly = true)
    public List<ReviewResponseDto> getByProduct(UUID productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    public void delete(UUID id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(id);
    }
}