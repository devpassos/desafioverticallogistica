package com.verticallogistica.desafio.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private Long userId;

    private String name;

    // Relacionamento 1:N com Pedido
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

 
    public User(Long id, String name) {
        this.userId = id;
        this.name = name;
    }

    public User(){}
}
