package com.verticallogistica.desafio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductDTO {
    
    @JsonProperty("produtc_id")
    private Long productId;

    @JsonProperty("value")
    private Double value;

    public ProductDTO(Long productId, Double value) {

        this.productId = productId;
        this.value = value;
    }

}