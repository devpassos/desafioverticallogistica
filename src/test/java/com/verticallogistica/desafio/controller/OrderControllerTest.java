package com.verticallogistica.desafio.controller;

import com.verticallogistica.desafio.dto.UserDTO;
import com.verticallogistica.desafio.service.ApiReturnService;
import com.verticallogistica.desafio.service.OrchestratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private ApiReturnService apiReturnService;

    @Mock
    private OrchestratorService orchestratorService;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsersWithOrders() {
        // Configuração do mock
        List<UserDTO> mockUsers = new ArrayList<>();
        mockUsers.add(new UserDTO(1L, "John", new ArrayList<>()));
        when(apiReturnService.getAUsersWithOrders(null, null, null)).thenReturn(mockUsers);

        // Execução do teste
        List<UserDTO> result = orderController.getUsersWithOrders(null, null, null);

        // Verificações
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
        verify(apiReturnService, times(1)).getAUsersWithOrders(null, null, null);
    }

    @Test
    void testUploadOrdersSuccess() throws IOException {
        // Configuração do mock
        when(multipartFile.isEmpty()).thenReturn(false);
        doNothing().when(orchestratorService).processFile(multipartFile);

        // Execução do teste
        ResponseEntity<String> response = orderController.uploadOrders(multipartFile);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Arquivo recebido com sucesso!", response.getBody());
    }

    @Test
    void testUploadOrdersEmptyFile() {
        // Configuração do mock
        when(multipartFile.isEmpty()).thenReturn(true);

        // Execução do teste
        ResponseEntity<String> response = orderController.uploadOrders(multipartFile);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Arquivo Vazio", response.getBody());
    }

    @Test
    void testUploadOrdersInternalServerError() throws IOException {
        // Configuração do mock
        when(multipartFile.isEmpty()).thenReturn(false);
        doThrow(new IOException("Erro ao processar o arquivo")).when(orchestratorService).processFile(multipartFile);

        // Execução do teste
        ResponseEntity<String> response = orderController.uploadOrders(multipartFile);

        // Verificações
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao processar o arquivo", response.getBody());
    }
}