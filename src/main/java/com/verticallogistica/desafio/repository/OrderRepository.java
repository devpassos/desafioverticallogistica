package com.verticallogistica.desafio.repository;

import com.verticallogistica.desafio.model.Order;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Busca pedidos dentro de um intervalo de datas
    @Query("SELECT o FROM Order o WHERE o.date BETWEEN :startDate AND :endDate")
    List<Order> findByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}