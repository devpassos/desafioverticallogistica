package com.verticallogistica.desafio.service;

import com.verticallogistica.desafio.model.Order;
import com.verticallogistica.desafio.model.OrderItem;
import com.verticallogistica.desafio.model.Product;
import com.verticallogistica.desafio.model.User;
import com.verticallogistica.desafio.utils.FileProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * Serviço orquestrador responsável por processar arquivos e organizar dados de pedidos e usuários.
 */
@Service
public class OrchestratorService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    /**
     * Processa um arquivo com dados de pedidos e salva as informações no banco.
     *
     * @param file Arquivo contendo dados de pedidos.
     * @throws IOException Se ocorrer um erro ao ler o arquivo.
     */
    @Transactional
    public void processFile(MultipartFile file) throws IOException {
        
        // Processa o arquivo e retorna as linhas como arrays de String
        List<String[]> lines = FileProcessor.readFile(file);

        // Cria uma mapa para usuários, pedidos e itens do pedido.
        Map<Long, User> usersMap = new HashMap<>();
        Map<Long, Order> orderMap = new HashMap<>();
        Map<Long, List<OrderItem>> orderItemsMap = new HashMap<>();

        // Itera sobre as linhas e organiza as informações
        for (String[] line : lines) {
            Long userId = Long.parseLong(line[0]);
            String userName = line[1];
            Long orderId = Long.parseLong(line[2]);
            Long productId = Long.parseLong(line[3]);
            Double productValue = Double.parseDouble(line[4]);
            LocalDate date = LocalDate.parse(line[5], DateTimeFormatter.BASIC_ISO_DATE);

            // Cria ou recupera o usuário.
            User user = usersMap.computeIfAbsent(userId, id -> {
                User newUser = new User(id, userName);
                return userService.saveUser(newUser);
            });

            // // Cria um pedido apenas se ainda não existir no mapa
            orderMap.computeIfAbsent(orderId, id -> new Order(id, date, user, new ArrayList<>()));           

            // Cria ou recupera o produto.
            Product product = productService.saveProduct(new Product(productId));

            // Cria o item do pedido
            OrderItem orderItem = new OrderItem(orderMap.get(orderId), product, productValue);
            orderItemsMap.computeIfAbsent(orderId, item -> new ArrayList<>()).add(orderItem);

          
        }

        // Calcula o total de cada pedido e realiza o salvamento no banco
        for (Map.Entry<Long, List<OrderItem>> orderWithItens : orderItemsMap.entrySet()) {
            Long orderId = orderWithItens.getKey();
            List<OrderItem> orderItems = orderWithItens.getValue();

            // Recupera o pedido correspondente do mapa de pedidos
            Order order = orderMap.get(orderId);

            // Calcula o total do pedido somando o valor de todos os itens
            double totalOrderValue = orderItems.stream()
                    .mapToDouble(OrderItem::getProductValue)
                    .sum();

            // salva o total no pedido
            order.setTotal(totalOrderValue);

            // Salva o pedido no banco
            orderService.saveOrder(order);

            // Salva todos os itens do pedido no banco
            for (OrderItem item : orderItems) {
                orderItemService.saveOrderItem(item);
            }
        }

    }
}