package com.verticallogistica.desafio.service;

import com.verticallogistica.desafio.model.*;
import com.verticallogistica.desafio.utils.FileProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;

class OrchestratorServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderItemService orderItemService;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private OrchestratorService orchestratorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessFile() throws IOException {
        // Simula o retorno da leitura do arquivo
        List<String[]> lines = List.of(
            new String[]{"1", "John", "101", "501", "20.0", "20231020"},
            new String[]{"1", "John", "101", "502", "30.0", "20231020"}
        );

        try (MockedStatic<FileProcessor> mockedFileProcessor = mockStatic(FileProcessor.class)) {
            mockedFileProcessor.when(() -> FileProcessor.readFile(multipartFile))
                               .thenReturn(lines);

            // Mock dos serviços envolvidos
            User user = new User(1L, "John");
            when(userService.saveUser(any(User.class))).thenReturn(user);

            Product product1 = new Product(501L);
            Product product2 = new Product(502L);
            when(productService.saveProduct(any(Product.class))).thenReturn(product1).thenReturn(product2);

            Order order = new Order(101L, LocalDate.of(2023, 10, 20), user, new ArrayList<>());
            when(orderService.saveOrder(any(Order.class))).thenReturn(order);

            // Executa o método que será testado
            orchestratorService.processFile(multipartFile);

            // Verifica se os serviços foram invocados corretamente
            verify(userService, times(1)).saveUser(any(User.class));
            verify(productService, times(2)).saveProduct(any(Product.class));
            verify(orderService, times(1)).saveOrder(any(Order.class));
            verify(orderItemService, times(2)).saveOrderItem(any(OrderItem.class));

            // Verifica se o método estático foi invocado
            mockedFileProcessor.verify(() -> FileProcessor.readFile(multipartFile), times(1));
        }
    }
}