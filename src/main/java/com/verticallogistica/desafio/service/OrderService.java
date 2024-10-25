package com.verticallogistica.desafio.service;

import com.verticallogistica.desafio.model.Order;
import com.verticallogistica.desafio.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/**
 * Serviço responsável por operações relacionadas a pedidos.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Retorna todos os pedidos registrados.
     *
     * @return Lista de pedidos.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Busca um pedido específico pelo ID.
     *
     * @param orderId ID do pedido.
     * @return Pedido correspondente, se encontrado.
     */
    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    /**
     * Salva um pedido no banco de dados.
     *
     * @param order Pedido a ser salvo.
     * @return Pedido salvo.
     */
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Retorna pedidos dentro de um intervalo de datas.
     *
     * @param startDate Data inicial.
     * @param endDate   Data final.
     * @return Lista de pedidos no intervalo.
     */
    public List<Order> getAllOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        
        return orderRepository.findByDateBetween(startDate, endDate);
    }
}
