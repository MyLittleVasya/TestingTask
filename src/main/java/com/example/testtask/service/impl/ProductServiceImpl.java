package com.example.testtask.service.impl;

import com.example.testtask.entity.Product;
import com.example.testtask.entity.repository.ProductRepository;
import com.example.testtask.handler.exception.AlreadyExistsException;
import com.example.testtask.handler.exception.NotFoundException;
import com.example.testtask.mapper.ProductMapper;
import com.example.testtask.service.ProductService;
import jakarta.annotation.Nonnull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Implementation service of {@link ProductService}.
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final ProductMapper productMapper;

  @Nonnull
  @Override
  public Product createProduct(@Nonnull final Product product) {
    if (productRepository.findById(product.getId()).isPresent()) {
      throw new AlreadyExistsException(
          String.format("Product with id %d is already exists", product.getId()));
    }
    final var createdProduct = productRepository.save(product);
    return createdProduct;
  }

  @Nonnull
  @Override
  public Product getProduct(final int productId) {
    final var product = productRepository.findById(productId);
    return product.orElseThrow(
        () -> new NotFoundException(String.format("Product with id %d is not found", productId)));
  }

  @Nonnull
  @Override
  public List<Product> getProducts(final int page) {
    return productRepository.findAllBy(PageRequest.of(page, 10));
  }

  @Override
  public boolean deleteProduct(final int productId) {
    final var product = productRepository.findById(productId);
    return product.map(object -> {
      productRepository.delete(object);
      return true;
    }).orElse(false);
  }

  @Override
  public void changeQuantity(int productId, int quantity) {
    final var product = getProduct(productId);
    product.setQuantity(product.getQuantity() + quantity);
    productRepository.save(product);
  }
}
