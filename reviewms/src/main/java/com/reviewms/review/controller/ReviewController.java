package com.reviewms.review.controller;

import com.reviewms.review.IService.IReviewService;
import com.reviewms.review.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

private IReviewService reviewService;
public ReviewController(IReviewService reviewService) {
    this.reviewService = reviewService;
}

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>( reviewService.getAllReviews(companyId),
                HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<String> addReviews(@RequestParam Long companyId , @RequestBody Review review){
        boolean isReviewSaved = reviewService.addCompanyReview(companyId,review);
        if(isReviewSaved)
            return new ResponseEntity<>("Review Added Successfully@@", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Unable to save review!!", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/{reviewId}")
    public  ResponseEntity<Review> getCompanyReview(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateCompanyReview(@PathVariable Long reviewId,
                                                      @RequestBody Review review  ){

        boolean isUpdatedReview = reviewService.updateCompanyReview(reviewId,review);
        if (isUpdatedReview)
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteCompanyReview( @PathVariable Long reviewId ){

        boolean isDeletedReview = reviewService.deleteCompanyReview(reviewId);
        if (isDeletedReview)
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not found",HttpStatus.NOT_FOUND);
    }
}
