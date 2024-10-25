package com.verticallogistica.desafio.service;

import com.verticallogistica.desafio.model.Product;
import com.verticallogistica.desafio.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Serviço responsável por operações relacionadas a produtos.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    /**
     * Retorna a lista de todos os produtos cadastrados.
     *
     * @return Lista de produtos.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Busca um produto pelo seu ID.
     *
     * @param productId ID do produto a ser encontrado.
     * @return Um Optional contendo o produto, caso encontrado.
     */
    public Optional<Product> findProductById(Long prodcutId) {
        return productRepository.findById(prodcutId);
    }


    /**
     * Salva um novo produto no banco de dados, se não existir. 
     * Se o produto já existir, retorna o produto existente.
     *
     * @param product Produto a ser salvo.
     * @return Produto salvo ou existente.
     */
    @Transactional
    public Product saveProduct(Product product) {
        
        Optional<Product> prod = productRepository.findById(product.getProductId());
        if (prod.isEmpty()) {
            return productRepository.save(product);
        }
        return prod.get();
    }
}