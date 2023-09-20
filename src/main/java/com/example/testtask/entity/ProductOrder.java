package com.example.testtask.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sub entity of {@link Order}. Contains information about order items.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class ProductOrder {

  private int productId;

  private int quantity;
}
