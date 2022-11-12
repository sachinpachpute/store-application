package com.sp.spring.catalogservice.controller;

import com.sp.spring.catalogservice.service.ProductService;
import com.sp.spring.catalogservice.web.CreateProductRequest;
import com.sp.spring.catalogservice.web.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
//TODO Sachin
public class ProductController {

    @GetMapping
    public String home(){
        return "Hello, JWT!";
    }

    @Autowired
    private ProductService productService;

    @PostMapping("/addProductTest")
    public CreateProductRequest addProduct(@RequestBody CreateProductRequest createProductRequest) {
        System.out.println("********************* addProduct Method called ***********************************");
        System.out.println(createProductRequest.toString());
        return createProductRequest;
    }

    @PostMapping("/product")
    //TODO: implement hasAuthority
    //@PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> createProduct(@RequestBody @Valid CreateProductRequest createProductRequest){

        String product = productService.createProduct(createProductRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("productId") String productId) {

        ProductResponse product = productService.getProduct(productId);

        return ResponseEntity.ok(product);
    }
    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<?> getAllProducts(@RequestParam(value = "sort", required = false) String sort,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size){

        Page<ProductResponse> list = productService.getAllProducts(sort, page, size);

        return ResponseEntity.ok(list);
    }

}
