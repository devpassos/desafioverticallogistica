package com.verticallogistica.desafio.dto;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @Test
    void testUserDTO() {
        // Cria uma lista de pedidos para o usuário
        List<OrderDTO> orders = List.of(new OrderDTO(1L, 100.00, null, List.of()));

        // Cria um UserDTO com valores de exemplo
        UserDTO userDTO = new UserDTO(1L, "John Doe", orders);

        // Verifica se os valores foram atribuídos corretamente
        assertEquals(1L, userDTO.getUserId());
        assertEquals("John Doe", userDTO.getName());
        assertEquals(orders, userDTO.getOrders());
    }

    @Test
    void testUserDTOSettersAndGetters() {
        UserDTO userDTO = new UserDTO(0L, "", List.of());

        // Define novos valores usando setters
        userDTO.setUserId(2L);
        userDTO.setName("Jane Doe");
        List<OrderDTO> newOrders = List.of(new OrderDTO(2L, 200.00, null, List.of()));
        userDTO.setOrders(newOrders);

        // Verifica se os getters retornam os valores corretos
        assertEquals(2L, userDTO.getUserId());
        assertEquals("Jane Doe", userDTO.getName());
        assertEquals(newOrders, userDTO.getOrders());
    }
}
