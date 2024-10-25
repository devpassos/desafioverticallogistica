package com.verticallogistica.desafio.service;

import com.verticallogistica.desafio.model.OrderItem;
import com.verticallogistica.desafio.repository.OrderItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Serviço responsável por operações relacionadas a itens de pedidos.
 */
@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    /**
     * Salva um novo item de pedido no banco de dados.
     *
     * @param orderItem Item de pedido a ser salvo.
     * @return Item de pedido salvo.
     */
    @Transactional
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
