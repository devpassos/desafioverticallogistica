package com.verticallogistica.desafio.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class OrderDTO {

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("total")
    private Double total;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("products")
    private List<ProductDTO> products;

    public OrderDTO(Long orderId, Double total, LocalDate date, List<ProductDTO> products) {
        this.orderId = orderId;
        this.total = total;
        this.date = date;
        this.products = products;
    }

}