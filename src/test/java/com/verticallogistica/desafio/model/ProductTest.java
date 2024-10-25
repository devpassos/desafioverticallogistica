package com.verticallogistica.desafio.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductConstructorAndGetters() {
        Product product = new Product(1L);

        assertEquals(1L, product.getProductId());
    }

    @Test
    void testSetName() {
        Product product = new Product();
        product.setName("Test Product");

        assertEquals("Test Product", product.getName());
    }
}
