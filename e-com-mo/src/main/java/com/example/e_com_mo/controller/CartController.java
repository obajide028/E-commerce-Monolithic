package com.example.e_com_mo.controller;

import com.example.e_com_mo.dto.CartItemRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<void> addToCart(
        @RequestHeader("X-User-ID") String userId,
        @RequestBody CartItemRequest request) {
        cartService.addToCart(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
