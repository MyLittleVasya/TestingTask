package com.example.testtask.entity.repository;

import com.example.testtask.entity.Product;
import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO for {@link Product}.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
  @Nonnull
  Optional<Product> findById(@Nonnull int id);

  List<Product> findAllBy(PageRequest pageRequest);

}
