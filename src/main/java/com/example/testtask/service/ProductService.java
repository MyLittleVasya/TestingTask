package com.example.testtask.service;

import com.example.testtask.entity.Product;
import com.example.testtask.handler.exception.AlreadyExistsException;
import com.example.testtask.handler.exception.NotFoundException;
import jakarta.annotation.Nonnull;
import java.util.List;

/**
 * Service for business logic linked with {@link Product}.
 */
public interface ProductService {

  /**
   * Create new product if not exists.
   *
   * @param product product data.
   * @return create product instance.
   * @throws AlreadyExistsException thrown when trying to create product with id.
   */
  @Nonnull
  Product createProduct(@Nonnull Product product) throws AlreadyExistsException;

  /**
   * Get product if exists.
   *
   * @param productId id of product to get.
   * @return product instance.
   * @throws NotFoundException thrown when trying to get product that doesn`t exist.
   */
  @Nonnull
  Product getProduct(int productId) throws NotFoundException;

  /**
   * Get page of products or empty list if page is empty.
   *
   * @param page number of page
   * @return list of products.
   */
  @Nonnull
  List<Product> getProducts(int page);

  /**
   * Delete product.
   *
   * @param productId product to be deleted.
   * @return boolean value of result.
   */
  boolean deleteProduct(int productId);

  /**
   * Change quantity of existing product.
   *
   * @param productId product to be changed.
   * @param quantity value of quantity.
   */
  void changeQuantity(int productId, int quantity);
}
