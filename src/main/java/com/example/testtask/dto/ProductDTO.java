package com.example.testtask.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for {@link com.example.testtask.entity.Product}.
 */
@Data
@Builder
public class ProductDTO {

  private String id;

  @NotBlank
  private String name;

  @Min(value = 1)
  private int price;

  @Min(value = 1)
  private int quantity;
}
