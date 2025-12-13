package com.reviewms.review.controller;

import com.reviewms.review.IService.IReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.reviewms.review.dto.ReviewDto;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private IReviewService reviewService;

    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReviews(@RequestParam Long companyId, @RequestBody ReviewDto reviewDto) {
        reviewService.addCompanyReview(companyId, reviewDto);
        return new ResponseEntity<>("Review Added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> getCompanyReview(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateCompanyReview(@PathVariable Long reviewId,
            @RequestBody ReviewDto reviewDto) {

        reviewService.updateCompanyReview(reviewId, reviewDto);
        return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);

    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteCompanyReview(@PathVariable Long reviewId) {
        reviewService.deleteCompanyReview(reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}
