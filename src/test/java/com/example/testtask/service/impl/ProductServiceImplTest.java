package com.example.testtask.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.testtask.entity.Product;
import com.example.testtask.entity.repository.ProductRepository;
import com.example.testtask.handler.exception.AlreadyExistsException;
import com.example.testtask.handler.exception.NotFoundException;
import com.example.testtask.mapper.ProductMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  @Mock
  private ProductRepository productRepository;

  @Mock
  private ProductMapper productMapper;

  @InjectMocks
  private ProductServiceImpl productService;

  @Test
  void testCreateProduct() {
    Product product = new Product();

    when(productRepository.findById(product.getId())).thenReturn(Optional.empty());
    when(productRepository.save(product)).thenReturn(product);

    Product createdProduct = productService.createProduct(product);

    assertNotNull(createdProduct);
    assertEquals(product, createdProduct);

    verify(productRepository, times(1)).save(product);
  }

  @Test
  void testCreateProductAlreadyExists() {
    Product product = new Product();

    when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

    assertThrows(AlreadyExistsException.class, () -> productService.createProduct(product));

    verify(productRepository, never()).save(product);
  }

  @Test
  void testGetProduct() {
    int productId = 1;
    Product product = new Product();

    when(productRepository.findById(productId)).thenReturn(Optional.of(product));

    Product retrievedProduct = productService.getProduct(productId);

    assertNotNull(retrievedProduct);
    assertEquals(product, retrievedProduct);
  }

  @Test
  void testGetProductNotFound() {
    int productId = 1;

    when(productRepository.findById(productId)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> productService.getProduct(productId));
  }

  @Test
  void testGetProducts() {
    int page = 1;
    List<Product> products = List.of(new Product(), new Product());

    when(productRepository.findAllBy(any())).thenReturn(products);

    List<Product> retrievedProducts = productService.getProducts(page);

    assertNotNull(retrievedProducts);
    assertEquals(products, retrievedProducts);
  }

  @Test
  void testDeleteProduct() {
    int productId = 1;
    Product product = new Product();

    when(productRepository.findById(productId)).thenReturn(Optional.of(product));

    boolean result = productService.deleteProduct(productId);

    assertTrue(result);
    verify(productRepository, times(1)).delete(product);
  }

  @Test
  void testDeleteProductNotFound() {
    int productId = 1;

    when(productRepository.findById(productId)).thenReturn(Optional.empty());

    boolean result = productService.deleteProduct(productId);

    assertFalse(result);
    verify(productRepository, never()).delete(any());
  }

  @Test
  void testChangeQuantity() {
    int productId = 1;
    int quantity = 5;
    Product product = new Product();
    product.setQuantity(10);

    when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    when(productRepository.save(product)).thenReturn(product);

    productService.changeQuantity(productId, quantity);

    assertEquals(15, product.getQuantity());
    verify(productRepository, times(1)).save(product);
  }
}

