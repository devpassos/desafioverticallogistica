package com.verticallogistica.desafio.utils;

import org.springframework.web.multipart.MultipartFile;
import java.nio.charset.StandardCharsets;

import java.io.*;
import java.util.*;

/**
 * Classe utilitária para processamento de arquivos.
 * Contém métodos para ler, interpretar e processar arquivos.
 */
public class FileProcessor {

    /**
     * Lê um arquivo linha por linha e retorna uma lista de arrays de Strings.
     * Cada linha do arquivo é convertida em um array de campos.
     *
     * @param file Arquivo a ser lido.
     * @return Lista de arrays de String, onde cada array representa uma linha.
     * @throws IOException Se ocorrer um erro durante a leitura do arquivo.
     */
    public static List<String[]> readFile(MultipartFile file) throws IOException {

        List<String[]> registers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Divide a linha em campos (assumindo colunas fixas ou delimitadores)
                String[] fields = parseLine(line);
                registers.add(fields);
            }
        } catch (IOException e) {
            e.printStackTrace();
            
        }

        return registers;
    }

    /**
     * Realiza o parse de uma linha em um array de Strings,
     * extraindo campos específicos da linha.
     *
     * @param line Linha a ser analisada.
     * @return Array de Strings contendo os campos da linha.
     */
    public static String[] parseLine(String line) {

        String userId = removeLeftZeros(line.substring(0, 10).trim());
        String name = line.substring(10, 55).trim();
        String orderId = removeLeftZeros(line.substring(55, 65).trim());
        String productId = removeLeftZeros(line.substring(65, 75).trim());
        String value = line.substring(75, 87).trim();
        String date = line.substring(87, 95);

        return new String[]{userId, name, orderId, productId, value, date};

    }

     /**
     * Remove os zeros à esquerda de uma string numérica.
     *
     * @param string String numérica com possíveis zeros à esquerda.
     * @return String sem zeros à esquerda.
     */
    public static String removeLeftZeros(String string) {

        // Substitui zeros iniciais por vazio
        return string.replaceFirst("^0+(?!$)", "");
        
        
    }
    
}
