package com.example.testtask.web;

import com.example.testtask.dto.ProductDTO;
import com.example.testtask.mapper.ProductMapper;
import com.example.testtask.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductMapper productMapper;

  private final ProductService productService;

  @PostMapping("/create")
  public ProductDTO createProduct(@Validated @RequestBody final ProductDTO productDTO) {
    var product = productMapper.productDTOToProduct(productDTO);
    product = productService.createProduct(product);
    final var result = productMapper.productToProductDTO(product);
    return result;
  }

  @GetMapping("/{productId}")
  public ProductDTO getProduct(@PathVariable final int productId) {
    final var product = productService.getProduct(productId);
    final var result = productMapper.productToProductDTO(product);
    return result;
  }

  @GetMapping("/page/{pageNumber}")
  public List<ProductDTO> getProducts(@PathVariable final int pageNumber) {
    final var products = productService.getProducts(pageNumber);
    final var result = productMapper.productListToProductDTOList(products);
    return result;
  }

  @DeleteMapping("/{productId}")
  public boolean deleteProduct(@PathVariable final int productId) {
    final var result = productService.deleteProduct(productId);
    return result;
  }
}
