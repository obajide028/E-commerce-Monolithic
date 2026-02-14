package com.example.e_com_mo.service;

import com.example.e_com_mo.dto.CartItemRequest;
import com.example.e_com_mo.model.CartItem;
import com.example.e_com_mo.model.Product;
import com.example.e_com_mo.model.User;
import com.example.e_com_mo.repository.CartItemRepository;
import com.example.e_com_mo.repository.ProductRepository;
import com.example.e_com_mo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public boolean addToCart(String userId, CartItemRequest request){
        // look for product
       Optional<Product> productOpt = productRepository.findById(request.getProductId());
       if(productOpt.isEmpty())
           return false;

       Product product = productOpt.get();
       if(product.getStockQuantity() < request.getQuantity())
           return false;

       Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
       if (userOpt.isEmpty())
           return false;

       User user = userOpt.get();

       CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
       if (existingCartItem != null) {

           // Update Quantity
           existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
           existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
           cartItemRepository.save(existingCartItem);

       } else {

           CartItem cartItem = new CartItem();
           cartItem.setUser(user);
           cartItem.setProduct(product);
           cartItem.setQuantity(request.getQuantity());
           cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
           cartItemRepository.save(cartItem);
       }
        return true;
    }
}
