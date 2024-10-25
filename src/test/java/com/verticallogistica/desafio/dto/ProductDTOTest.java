package com.verticallogistica.desafio.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductDTOTest {

    @Test
    void testProductDTO() {
        // Cria um ProductDTO com valores de exemplo
        ProductDTO productDTO = new ProductDTO(1L, 100.50);

        // Verifica se os valores foram atribuídos corretamente
        assertEquals(1L, productDTO.getProductId());
        assertEquals(100.50, productDTO.getValue());
    }

    @Test
    void testProductDTOSettersAndGetters() {
        ProductDTO productDTO = new ProductDTO(0L, 0.0);

        // Define novos valores usando setters
        productDTO.setProductId(2L);
        productDTO.setValue(200.75);

        // Verifica se os getters retornam os valores corretos
        assertEquals(2L, productDTO.getProductId());
        assertEquals(200.75, productDTO.getValue());
    }
}