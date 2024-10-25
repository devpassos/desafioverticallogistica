package com.verticallogistica.desafio.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderDTOTest {

    @Test
    void testOrderDTO() {
        // Cria alguns produtos para o pedido
        List<ProductDTO> products = List.of(new ProductDTO(1L, 50.00), new ProductDTO(2L, 75.00));

        // Cria um OrderDTO com valores de exemplo
        OrderDTO orderDTO = new OrderDTO(1L, 125.00, LocalDate.of(2021, 10, 10), products);

        // Verifica se os valores foram atribuídos corretamente
        assertEquals(1L, orderDTO.getOrderId());
        assertEquals(125.00, orderDTO.getTotal());
        assertEquals(LocalDate.of(2021, 10, 10), orderDTO.getDate());
        assertEquals(products, orderDTO.getProducts());
    }

    @Test
    void testOrderDTOSettersAndGetters() {
        OrderDTO orderDTO = new OrderDTO(0L, 0.0, LocalDate.now(), List.of());

        // Define novos valores usando setters
        orderDTO.setOrderId(2L);
        orderDTO.setTotal(150.00);
        orderDTO.setDate(LocalDate.of(2022, 5, 20));
        List<ProductDTO> newProducts = List.of(new ProductDTO(3L, 99.99));
        orderDTO.setProducts(newProducts);

        // Verifica se os getters retornam os valores corretos
        assertEquals(2L, orderDTO.getOrderId());
        assertEquals(150.00, orderDTO.getTotal());
        assertEquals(LocalDate.of(2022, 5, 20), orderDTO.getDate());
        assertEquals(newProducts, orderDTO.getProducts());
    }
}