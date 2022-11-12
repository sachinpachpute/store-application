package com.sp.spring.catalogservice.service;

import com.sp.spring.catalogservice.repository.dao.ProductCategory;
import com.sp.spring.catalogservice.web.CreateProductCategoryRequest;
import com.sp.spring.catalogservice.web.UpdateProductCategoryRequest;
import org.springframework.data.domain.Page;

import javax.validation.Valid;

public interface ProductCategoryService {

    String createProductCategory(@Valid CreateProductCategoryRequest createProductCategoryRequest);

    ProductCategory getProductCategory(String productCategoryId);

    void deleteProductCategory(String productCategoryId);

    void updateProductCategory(UpdateProductCategoryRequest updateProductCategoryRequest);

    Page<ProductCategory> getAllProductCategories(String sort, Integer page, Integer size);
}
