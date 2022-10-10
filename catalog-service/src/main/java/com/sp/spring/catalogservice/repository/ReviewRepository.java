package com.sp.spring.catalogservice.repository;

import com.sp.spring.catalogservice.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ProductReview, String> {

    Optional<ProductReview> findByUserIdAndProductId(String userId, String productId);
    Optional<List<ProductReview>> findAllByProductId(String productId);

    long countAllByProductId(String productId);
}
