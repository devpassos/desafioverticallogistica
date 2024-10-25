package com.verticallogistica.desafio.repository;

import com.verticallogistica.desafio.model.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
}