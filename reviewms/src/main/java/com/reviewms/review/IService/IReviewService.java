package com.reviewms.review.IService;

import com.reviewms.review.model.Review;

import java.util.List;

public interface IReviewService {
    List<Review> getAllReviews(Long companyId);

    Boolean addCompanyReview(Long companyId, Review reviewCompany);

    Review getReview(Long reviewId);

    Boolean updateCompanyReview(Long reviewId, Review updatedReview);

    Boolean deleteCompanyReview(Long reviewId);
}
