package com.verticallogistica.desafio.service;

import com.verticallogistica.desafio.dto.UserDTO;
import com.verticallogistica.desafio.model.Order;
import com.verticallogistica.desafio.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApiReturnServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private ApiReturnService apiReturnService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAUsersWithOrdersByOrderIdAndDateRange() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        Long orderId = 101L;

        Order order = new Order(orderId, LocalDate.of(2023, 10, 20), new User(1L, "John"), List.of());
        when(orderService.getAllOrdersByDateRange(startDate, endDate)).thenReturn(List.of(order));
        when(userService.findUserById(1L)).thenReturn(Optional.of(new User(1L, "John")));

        List<UserDTO> result = apiReturnService.getAUsersWithOrders(orderId, startDate, endDate);

        assertEquals(1, result.size());
    }

    @Test
    void testGetAUsersWithOrders_InvalidOrderId_ReturnsEmptyList() {
        when(orderService.findOrderById(999L)).thenReturn(Optional.empty());

        List<UserDTO> result = apiReturnService.getAUsersWithOrders(999L, null, null);

        assertTrue(!result.isEmpty());
    }
}