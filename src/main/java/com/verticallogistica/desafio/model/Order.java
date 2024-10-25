package com.verticallogistica.desafio.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long orderId;
    
    private double total;

    private LocalDate date;

    // Relacionamento com a tabela de Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relacionamento 1:N com Itens do Pedido
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Order(Long id, LocalDate date, User user, List<OrderItem> orderItens) {
        this.orderId = id;
        this.date = date;
        this.user = user;
        this.orderItems = orderItens;

    }

    public Order(){}
    
}