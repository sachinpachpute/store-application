package com.sp.spring.catalogservice.service;

import com.sp.spring.catalogservice.entity.ProductReview;
import com.sp.spring.catalogservice.web.CreateOrUpdateReviewRequest;

import java.util.List;

public interface ReviewService {

    void createOrUpdateReview(CreateOrUpdateReviewRequest createOrUpdateReviewRequest);

    List<ProductReview> getReviewsForProduct(String productId);

}
