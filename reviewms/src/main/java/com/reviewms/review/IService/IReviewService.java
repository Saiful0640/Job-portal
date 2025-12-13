package com.reviewms.review.IService;

import com.reviewms.review.dto.ReviewDto;
import com.reviewms.review.model.Review;

import java.util.List;

public interface IReviewService {
    List<ReviewDto> getAllReviews(Long companyId);

    void addCompanyReview(Long companyId, ReviewDto reviewDto);

    ReviewDto getReview(Long reviewId);

    void updateCompanyReview(Long reviewId, ReviewDto reviewDto);

    void deleteCompanyReview(Long reviewId);
}
