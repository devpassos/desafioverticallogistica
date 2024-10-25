package com.verticallogistica.desafio.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderItemTest {

    @Test
    void testOrderItemConstructorAndGetters() {
        Order order = mock(Order.class);
        Product product = mock(Product.class);

        OrderItem orderItem = new OrderItem(order, product, 100.0);

        assertEquals(order, orderItem.getOrder());
        assertEquals(product, orderItem.getProduct());
        assertEquals(100.0, orderItem.getProductValue());
    }

    @Test
    void testSetOrderAndProduct() {
        OrderItem orderItem = new OrderItem();
        Order order = new Order();
        Product product = new Product();

        orderItem.setOrder(order);
        orderItem.setProduct(product);

        assertEquals(order, orderItem.getOrder());
        assertEquals(product, orderItem.getProduct());
    }
}