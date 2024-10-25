package com.verticallogistica.desafio.controller;

import com.verticallogistica.desafio.dto.UserDTO;
import com.verticallogistica.desafio.service.ApiReturnService;
import com.verticallogistica.desafio.service.OrchestratorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "API para integração de pedidos de venda dos clientes vindos do sistema legado da Vertical Logística.")
public class OrderController {

    @Autowired
    private ApiReturnService apiReturnService;

    @Autowired
    private OrchestratorService orchestratorService;


     /**
     * Retorna uma lista de usuários e seus pedidos, com base em filtros opcionais de ID do pedido
     * e intervalo de datas.
     *
     * @param orderId    (Opcional) ID do pedido para filtrar.
     * @param startDate  (Opcional) Data inicial do intervalo.
     * @param endDate    (Opcional) Data final do intervalo.
     * @return Lista de usuários e seus pedidos correspondentes.
     */
    @Operation(summary = "Lista usuários com pedidos", 
               description = "Retorna uma lista de usuários com seus pedidos, com filtros opcionais por ID e intervalo de datas.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
                   @ApiResponse(responseCode = "500", description = "Erro interno do servidor", 
                                content = @Content(schema = @Schema(implementation = String.class)))
               })
    @GetMapping
    public List<UserDTO> getUsersWithOrders(
        @RequestParam(required = false) Long orderId,

        @RequestParam(required = false) 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        
        @RequestParam(required = false) 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate ) {

        return apiReturnService.getAUsersWithOrders(orderId, startDate, endDate);
    }


    /**
     * Faz o upload de um arquivo contendo dados de pedidos e processa as informações.
     *
     * @param file Arquivo multipart contendo dados de pedidos.
     * @return Mensagem de status da operação.
     */
    @Operation(summary = "Faz o upload de pedidos", 
               description = "Recebe um arquivo com dados de pedidos e processa as informações.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Arquivo recebido com sucesso"),
                   @ApiResponse(responseCode = "400", description = "Arquivo vazio", 
                                content = @Content(schema = @Schema(implementation = String.class))),
                   @ApiResponse(responseCode = "500", description = "Erro ao processar o arquivo", 
                                content = @Content(schema = @Schema(implementation = String.class)))
               })
    @PostMapping
    public ResponseEntity<String> uploadOrders(@RequestParam("file") MultipartFile file) {

        try {

            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arquivo Vazio");
            }
            orchestratorService.processFile(file);
            return ResponseEntity.ok("Arquivo recebido com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(e.getMessage());

        }

    }

}
