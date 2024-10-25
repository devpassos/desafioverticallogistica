package com.verticallogistica.desafio.model;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        User user = new User(1L, "John Doe");

        assertEquals(1L, user.getUserId());
        assertEquals("John Doe", user.getName());
    }

    @Test
    void testSetOrders() {
        User user = new User(1L, "John Doe");
        List<Order> orders = List.of(new Order());

        user.setOrders(orders);

        assertEquals(orders, user.getOrders());
    }
}