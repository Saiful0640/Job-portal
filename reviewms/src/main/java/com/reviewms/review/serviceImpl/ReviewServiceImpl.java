package com.reviewms.review.serviceImpl;

import com.reviewms.review.IService.IReviewService;
import com.reviewms.review.dto.ReviewDto;
import com.reviewms.review.exceptionHandler.ReviewNotFoundException;
import com.reviewms.review.mapper.ReviewMapper;
import com.reviewms.review.model.Review;
import com.reviewms.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import java.util.List;

@Service
public class ReviewServiceImpl implements IReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDto> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().map(ReviewMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void addCompanyReview(Long companyId, ReviewDto reviewDto) {
        if (companyId != null && reviewDto != null) {
            Review review = ReviewMapper.mapToReview(reviewDto);
            review.setCompanyId(companyId);
            reviewRepository.save(review);
        }
    }

    @Override
    public ReviewDto getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review Not found with id " + reviewId));
        return ReviewMapper.mapToDto(review);
    }

    @Override
    public void updateCompanyReview(Long reviewId, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review Not found with id " + reviewId));
        review.setTitle(reviewDto.getTitle());
        review.setDescription(reviewDto.getDescription());
        review.setRating(reviewDto.getRating());
        review.setCompanyId(reviewDto.getCompanyId());
        reviewRepository.save(review);
    }

    @Override
    public void deleteCompanyReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review Not found with id " + reviewId));
        reviewRepository.delete(review);
    }
}
