package com.reviewms.review.mapper;

import com.reviewms.review.dto.ReviewDto;
import com.reviewms.review.model.Review;

public class ReviewMapper {

    public static ReviewDto mapToDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setTitle(review.getTitle());
        dto.setDescription(review.getDescription());
        dto.setRating(review.getRating());
        dto.setCompanyId(review.getCompanyId());
        return dto;
    }

    public static Review mapToReview(ReviewDto dto) {
        Review review = new Review();
        review.setId(dto.getId());
        review.setTitle(dto.getTitle());
        review.setDescription(dto.getDescription());
        review.setRating(dto.getRating());
        review.setCompanyId(dto.getCompanyId());
        return review;
    }
}
