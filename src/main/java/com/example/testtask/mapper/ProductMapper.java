package com.example.testtask.mapper;

import com.example.testtask.dto.ProductDTO;
import com.example.testtask.entity.Product;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for {@link Product}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductMapper {

  /**
   * Map from Product to it`s DTO.
   *
   * @param product entity to be mapped.
   * @return DTO instance.
   */
  @Mapping(target = "id", source = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  ProductDTO productToProductDTO(Product product);

  /**
   * Map from DTO to Product.
   *
   * @param product DTO to be mapped.
   * @return entity instance.
   */
  @Mapping(target = "id", source = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  Product productDTOToProduct(ProductDTO product);

  /**
   * Map from Product list to it`s DTO list.
   *
   * @param list list of products to be mapped.
   * @return list of DTOs
   */
  @Mapping(target = "id", source = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  List<ProductDTO> productListToProductDTOList(List<Product> list);

  /**
   * Map from DTO list to Product list.
   *
   * @param list list of DTO to be mapped.
   * @return entities list.
   */
  @Mapping(target = "id", source = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  List<Product> productDTOListToProductList(List<ProductDTO> list);
}
