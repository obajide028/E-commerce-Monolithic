package com.example.e_com_mo.repository;

import com.example.e_com_mo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
