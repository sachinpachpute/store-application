package com.sp.spring.catalogservice.service;

import com.sp.spring.catalogservice.web.CreateProductRequest;
import com.sp.spring.catalogservice.web.ProductResponse;
import org.springframework.data.domain.Page;

import javax.validation.Valid;

public interface ProductService {

    //String createProduct(@Valid CreateProductRequest createProductRequest);

    ProductResponse getProduct(String productId);

    String createProduct(@Valid CreateProductRequest createProductRequest);

    Page<ProductResponse> getAllProducts(String sort, Integer page, Integer size);

   /* void deleteProduct(String productId);

    void updateProduct(UpdateProductRequest updateProductRequest);

    Page<Product> findAllProducts(Pageable pageable);

    Page<ProductResponse> getAllProducts(String sort, Integer page, Integer size);*/
}

