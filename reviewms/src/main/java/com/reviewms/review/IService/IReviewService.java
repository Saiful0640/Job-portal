package com.reviewms.review.IService;

import com.reviewms.review.model.Review;

import java.util.List;

public interface IReviewService {
    List<Review> getAllReviews(Long companyId);

    void addCompanyReview(Long companyId, Review reviewCompany);

    Review getReview(Long reviewId);

    void updateCompanyReview(Long reviewId, Review updatedReview);

    void deleteCompanyReview(Long reviewId);
}
