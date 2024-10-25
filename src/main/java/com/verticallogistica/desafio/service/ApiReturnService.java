package com.verticallogistica.desafio.service;

import com.verticallogistica.desafio.dto.OrderDTO;
import com.verticallogistica.desafio.dto.ProductDTO;
import com.verticallogistica.desafio.dto.UserDTO;
import com.verticallogistica.desafio.model.User;
import com.verticallogistica.desafio.model.Order;
import com.verticallogistica.desafio.model.OrderItem;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Serviço responsável por retornar informações sobre pedidos e usuários.
 * 
 * Este serviço fornece funcionalidades para buscar pedidos e usuários com base 
 * em diferentes critérios, como intervalo de datas e ID de pedido. Ele converte
 * entidades de banco de dados em DTOs (Data Transfer Objects) para serem usados 
 * nas respostas da API.
 */
@Service
public class ApiReturnService {

    @Autowired
    private  UserService userService;

    @Autowired
    private OrderService orderService;

    /**
     * Retorna uma lista de usuários e seus pedidos, com base em filtros opcionais
     * de ID do pedido e intervalo de datas.
     *
     * @param orderId   (Opcional) ID do pedido para filtrar.
     * @param startDate (Opcional) Data inicial do intervalo.
     * @param endDate   (Opcional) Data final do intervalo.
     * @return Lista de usuários com seus pedidos correspondentes.
     */
    public List<UserDTO> getAUsersWithOrders(Long orderId, LocalDate startDate, LocalDate endDate) {
        
        List<UserDTO> listUser = new ArrayList<>();

        if (((startDate != null) && (endDate != null)) && (orderId != null)) {
            
            orderService.getAllOrdersByDateRange(startDate, endDate)
                    .forEach(order -> {
                        if (order.getOrderId() == orderId) {
                            listUser.add(getUserDTOByOrderById(order.getOrderId())); 
                        }
                    });
            return listUser;    
        }
        if ((startDate != null) && (endDate != null)) {
            
            orderService.getAllOrdersByDateRange(startDate, endDate)
            .forEach(order -> {
                listUser.add(getUserDTOByOrderById(order.getOrderId())); 
            });

            return listUser;
        }
        if (orderId != null) {
            listUser.add(getUserDTOByOrderById(orderId));

            return listUser;
        }
        
        List<User> users = userService.getAllUsers();
        return users.stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    /**
     * Retorna um UserDTO correspondente a um pedido específico.
     *
     * @param orderId ID do pedido.
     * @return UserDTO correspondente ao pedido, ou um UserDTO vazio se o pedido não for encontrado.
     */
    private UserDTO getUserDTOByOrderById(Long orderId) {

        Optional<Order> order = orderService.findOrderById(orderId);
        if (!order.isEmpty()) {
      
            return convertToUserDTOByOrderId(userService.findUserById(order.get()
                                        .getUser().getUserId()).get(), orderId);
            
        }  
        return new UserDTO(null, null, null);
    }

    /**
     * Converte um objeto User em um UserDTO, incluindo seus pedidos.
     *
     * @param user Objeto User a ser convertido.
     * @return UserDTO contendo as informações do usuário e seus pedidos.
     */
    private UserDTO convertToUserDTO(User user) {
        List<OrderDTO> orders = user.getOrders().stream()
                                .map(this::convertToOrderDTO)
                                .collect(Collectors.toList());

        return new UserDTO(user.getUserId(), user.getName(), orders);
    }

    /**
     * Converte um objeto User em um UserDTO, filtrando por um pedido específico.
     *
     * @param user    Objeto User a ser convertido.
     * @param orderId ID do pedido para filtrar.
     * @return UserDTO contendo as informações do usuário e o pedido filtrado.
     */
    private UserDTO convertToUserDTOByOrderId(User user, Long orderId) {
        List<OrderDTO> orders = user.getOrders().stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    
        return new UserDTO(user.getUserId(), user.getName(), orders);
    }

    /**
     * Converte um objeto Order em um OrderDTO, incluindo seus produtos.
     *
     * @param order Objeto Order a ser convertido.
     * @return OrderDTO contendo as informações do pedido e seus produtos.
     */
    private OrderDTO convertToOrderDTO(Order order) {
        List<ProductDTO> products = order.getOrderItems().stream()
                        .map(item -> new ProductDTO(item.getProduct()
                                                    .getProductId(), item.getProductValue()))
                        .collect(Collectors.toList());

        Double total = order.getOrderItems().stream()
                            .mapToDouble(OrderItem::getProductValue).sum();

        LocalDate date = order.getDate();

        return new OrderDTO(order.getOrderId(), total, date, products);
    }

}