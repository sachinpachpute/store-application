package com.sp.spring.catalogservice.service.impl;

import com.sp.spring.catalogservice.entity.ProductReview;
import com.sp.spring.catalogservice.repository.ReviewRepository;
import com.sp.spring.catalogservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<ProductReview> getReviewsForProduct(String productId) {

        Optional<List<ProductReview>> reviewsForProduct = reviewRepository.findAllByProductId(productId);
        return reviewsForProduct.orElseGet(ArrayList::new);

    }
}
