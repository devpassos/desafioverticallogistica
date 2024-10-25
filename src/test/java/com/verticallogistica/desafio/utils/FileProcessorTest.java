package com.verticallogistica.desafio.utils;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class FileProcessorTest {

    @Test
    void testReadFile() throws Exception {
        // Conteúdo simulado do arquivo
        String fileContent = 
        "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308\n" +
        "0000000075                                  Bobbie Batz00000007980000000002     1578.5720211116";

        // Simula o MultipartFile com o conteúdo acima
        MultipartFile mockFile = Mockito.mock(MultipartFile.class);
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        when(mockFile.getInputStream()).thenReturn(inputStream);

        // Chama o método a ser testado
        List<String[]> result = FileProcessor.readFile(mockFile);

        // Validações dos resultados
        assertNotNull(result);
        assertEquals(2, result.size()); // Deve ter 2 registros (linhas)

        // Verifica o primeiro registro (linha)
        String[] firstLine = result.get(0);
        assertEquals("70", firstLine[0]); // userId com zeros à esquerda removidos
        assertEquals("Palmer Prosacco", firstLine[1].trim());
        assertEquals("753", firstLine[2]); // orderId
        assertEquals("3", firstLine[3]); // productId
        assertEquals("1836.74", firstLine[4].trim()); //productValue
        assertEquals("20210308", firstLine[5]); // date

        // Verifica o segundo registro (linha)
        String[] secondLine = result.get(1);
        assertEquals("75", secondLine[0]); // userId
        assertEquals("Bobbie Batz", secondLine[1].trim());
        assertEquals("798", secondLine[2]); // orderId
        assertEquals("2", secondLine[3]); // productId
        assertEquals("1578.57", secondLine[4].trim()); //productValue
        assertEquals("20211116", secondLine[5]); // date
    }

    @Test
    void testRemoveLeftZeros() {
        // Verifica a remoção correta dos zeros à esquerda
        assertEquals("123", FileProcessor.removeLeftZeros("000123"));
    }

    @Test
    void testParseLine() {
        // Exemplo de linha para ser parseada
        String line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";

        // Chama o método parseLine diretamente
        String[] result = FileProcessor.parseLine(line);

        // Validações do array retornado
        assertEquals("70", result[0]); // userId com zeros à esquerda removidos
        assertEquals("Palmer Prosacco", result[1].trim());
        assertEquals("753", result[2]); // orderId
        assertEquals("3", result[3]); // productId
        assertEquals("1836.74", result[4].trim()); //productValue
        assertEquals("20210308", result[5]); // date
    }
}