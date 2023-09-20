package com.example.testtask.dto;

import com.example.testtask.entity.ProductOrder;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for {@link com.example.testtask.entity.Order} entity.
 */
@Data
@Builder
public class OrderDTO {

  private String id;

  @NotEmpty
  private List<ProductOrder> orderItems;

  private boolean paid;

  private String author;

  private LocalDateTime createdAt;
}
