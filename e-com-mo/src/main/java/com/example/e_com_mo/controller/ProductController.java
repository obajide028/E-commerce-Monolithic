package com.example.e_com_mo.controller;

import com.example.e_com_mo.dto.ProductRequest;
import com.example.e_com_mo.dto.ProductResponse;
import com.example.e_com_mo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {

        return new ResponseEntity<ProductResponse>(productService.createProduct(productRequest),
                    HttpStatus.CREATED);
    }
}
