package com.verticallogistica.desafio.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDTO {
    @JsonProperty("user_id")
    private Long userId;
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("oerders")
    private List<OrderDTO> orders;

    public UserDTO(Long userId, String name, List<OrderDTO> orders) {
        this.userId = userId;
        this.name = name;
        this.orders = orders;
    }

}

