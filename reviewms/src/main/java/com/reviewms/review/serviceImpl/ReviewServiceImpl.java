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
        List<Review> reviews= reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Boolean addCompanyReview(Long companyId, Review reviewCompany) {
        if (companyId != null && reviewCompany != null){
            reviewCompany.setCompanyId(companyId);
            reviewRepository.save(reviewCompany);
            return true;
        }else
            return false;
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(null);
    }

    @Override
    public Boolean updateCompanyReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }else
            return false;
    }

    @Override
    public Boolean deleteCompanyReview(Long reviewId) {
        return reviewRepository.findById(reviewId).map(
                company -> {

                    reviewRepository.delete(company);
                    return true;
                }).orElseThrow(()-> new ReviewNotFoundException("No company found:"+ reviewId));
    }
}
