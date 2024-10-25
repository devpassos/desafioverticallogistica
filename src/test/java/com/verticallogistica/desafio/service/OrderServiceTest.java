package com.verticallogistica.desafio.service;

import com.verticallogistica.desafio.model.Order;
import com.verticallogistica.desafio.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    public OrderServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = List.of(new Order());
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testFindOrderById() {
        Order order = new Order();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.findOrderById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void testGetAllOrdersByDateRange() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        List<Order> orders = List.of(new Order());
        when(orderRepository.findByDateBetween(startDate, endDate)).thenReturn(orders);

        List<Order> result = orderService.getAllOrdersByDateRange(startDate, endDate);

        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findByDateBetween(startDate, endDate);
    }
}