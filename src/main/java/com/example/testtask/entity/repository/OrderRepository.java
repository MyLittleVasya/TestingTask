package com.example.testtask.entity.repository;

import com.example.testtask.entity.Order;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO for {@link Order}.
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
  List<Order> getAllByPaidAndCreatedAtBefore(boolean isPaid, LocalDateTime time);

  List<Order> findAllBy(PageRequest pageRequest);
}
