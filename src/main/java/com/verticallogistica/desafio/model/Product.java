package com.verticallogistica.desafio.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    private Long productId;

    private String name;

    public Product (Long id){
        this.productId = id;
    };

    public Product(){}

}