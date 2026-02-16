package com.example.e_com_mo.repository;

import com.example.e_com_mo.model.CartItem;
import com.example.e_com_mo.model.Product;
import com.example.e_com_mo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);

   List<CartItem> findByUser(User user);
}
