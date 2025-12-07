package com.reviewms.review.serviceImpl;

import com.reviewms.review.IService.IReviewService;
import com.reviewms.review.exceptionHandler.ReviewNotFoundException;
import com.reviewms.review.model.Review;
import com.reviewms.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements IReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public void addCompanyReview(Long companyId, Review reviewCompany) {
        if (companyId != null && reviewCompany != null) {
            reviewCompany.setCompanyId(companyId);
            reviewRepository.save(reviewCompany);
        }
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review Not found with id " + reviewId));
    }

    @Override
    public void updateCompanyReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review Not found with id " + reviewId));
        review.setTitle(updatedReview.getTitle());
        review.setDescription(updatedReview.getDescription());
        review.setRating(updatedReview.getRating());
        review.setCompanyId(updatedReview.getCompanyId());
        reviewRepository.save(review);
    }

    @Override
    public void deleteCompanyReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review Not found with id " + reviewId));
        reviewRepository.delete(review);
    }
}
