package com.sp.spring.catalogservice.service;

import com.sp.spring.catalogservice.entity.ProductCategory;

public interface ProductCategoryService {

    //String createProductCategory(@Valid CreateProductCategoryRequest createProductCategoryRequest);

    ProductCategory getProductCategory(String productCategoryId);

    /*void deleteProductCategory(String productCategoryId);

    void updateProductCategory(UpdateProductCategoryRequest updateProductCategoryRequest);

    Page<ProductCategory> getAllProductCategories(String sort, Integer page, Integer size);*/
}
