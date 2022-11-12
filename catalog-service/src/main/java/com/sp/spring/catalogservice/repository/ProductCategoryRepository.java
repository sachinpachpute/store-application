package com.sp.spring.catalogservice.repository;

import com.sp.spring.catalogservice.repository.dao.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {
}