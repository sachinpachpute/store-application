package com.sp.spring.catalogservice.controller;

import com.sp.spring.catalogservice.entity.ProductReview;
import com.sp.spring.catalogservice.service.ReviewService;
import com.sp.spring.catalogservice.web.CreateOrUpdateReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<?> createOrUpdateReview(@RequestBody @Valid CreateOrUpdateReviewRequest createOrUpdateReviewRequest) {
        reviewService.createOrUpdateReview(createOrUpdateReviewRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/review")
    public ResponseEntity<?> getAllReviewsForProduct(@RequestParam("productId") String productId) {
        List<ProductReview> reviewsForProduct = reviewService.getReviewsForProduct(productId);
        return ResponseEntity.ok(reviewsForProduct);
    }
}
