package com.verticallogistica.desafio.service;

import com.verticallogistica.desafio.model.OrderItem;
import com.verticallogistica.desafio.repository.OrderItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderItemService orderItemService;

    public OrderItemServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOrderItem() {
        OrderItem orderItem = new OrderItem();
        when(orderItemRepository.save(orderItem)).thenReturn(orderItem);

        OrderItem result = orderItemService.saveOrderItem(orderItem);

        assertNotNull(result);
        verify(orderItemRepository, times(1)).save(orderItem);
    }
}