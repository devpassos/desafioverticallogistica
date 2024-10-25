package com.verticallogistica.desafio.service;

import com.verticallogistica.desafio.model.Product;
import com.verticallogistica.desafio.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = List.of(new Product(1L));
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById() {
        Product product = new Product(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.findProductById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getProductId());
    }

    @Test
    void testSaveProduct() {
        Product product = new Product(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.saveProduct(product);

        assertEquals(1L, result.getProductId());
        verify(productRepository, times(1)).save(product);
    }
}