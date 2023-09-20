package com.example.testtask.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity that contains main information about order.
 */
@Entity
@Table(name = "order_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ElementCollection(targetClass = ProductOrder.class)
  @CollectionTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"))
  private List<ProductOrder> orderItems;

  private boolean paid;

  private String author;

  private LocalDateTime createdAt;

}
