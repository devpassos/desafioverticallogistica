package com.verticallogistica.desafio.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderTest {

    @Test
    void testOrderConstructorAndGetters() {
        User user = mock(User.class);
        LocalDate date = LocalDate.of(2021, 10, 10);
        List<OrderItem> orderItems = List.of(new OrderItem());
        
        Order order = new Order(1L, date, user, orderItems);

        assertEquals(1L, order.getOrderId());
        assertEquals(date, order.getDate());
        assertEquals(user, order.getUser());
        assertEquals(orderItems, order.getOrderItems());
    }

    @Test
    void testSetTotal() {
        Order order = new Order();
        order.setTotal(200.0);

        assertEquals(200.0, order.getTotal());
    }
}