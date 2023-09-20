package com.example.testtask.service.impl;

import com.example.testtask.entity.Order;
import com.example.testtask.entity.repository.OrderRepository;
import com.example.testtask.handler.exception.AlreadyExistsException;
import com.example.testtask.handler.exception.NotFoundException;
import com.example.testtask.handler.exception.OperationNotAllowedException;
import com.example.testtask.service.OrderService;
import com.example.testtask.service.ProductService;
import jakarta.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * Implementation service of {@link OrderService}.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final ProductService productService;

  @Nonnull
  @Override
  public Order createOrder(@Nonnull Order order, @Nonnull User author) {
    if (orderRepository.findById(order.getId()).isPresent()) {
      throw new AlreadyExistsException(
          String.format("Order with id %d is already exists", order.getId()));
    }
    final var createdAt = LocalDateTime.now();
    order.setCreatedAt(createdAt);
    order.setAuthor(author.getUsername());
    order.setPaid(false);
    for (final var product : order.getOrderItems()) {
      productService.changeQuantity(product.getProductId(), product.getQuantity() * -1);
    }
    final var savedOrder = orderRepository.save(order);
    return savedOrder;
  }

  @Nonnull
  @Override
  public Order getOrder(int orderId) {
    final var order = orderRepository.findById(orderId);
    return order.orElseThrow(
        () -> new NotFoundException(String.format("Order with id %d is not found", orderId)));
  }

  @Nonnull
  @Override
  public List<Order> getOrders(int page) {
    return orderRepository.findAllBy(PageRequest.of(page, 10));
  }

  @Override
  public boolean deleteOrder(int orderId) {
    final var product = orderRepository.findById(orderId);
    return product.map(object -> {
      orderRepository.delete(object);
      return true;
    }).orElse(false);
  }

  @Override
  public boolean payOrder(int orderId, @Nonnull String author) {
    orderRepository.findById(orderId).ifPresentOrElse(
        object -> {
          if (author.equals(object.getAuthor())) {
            object.setPaid(true);
            orderRepository.save(object);
          } else {
            throw new OperationNotAllowedException(
                String.format("You aren`t author to pay order %d", orderId));
          }
        }, () -> {
          throw new NotFoundException(String.format("Order with id %d is not found", orderId));
        }
    );
    return true;
  }

  @Override
  @Scheduled(fixedDelay = 600_000)
  public void removeUnpaid() {
    final var unpaidOrders =
        orderRepository.getAllByPaidAndCreatedAtBefore(false, LocalDateTime.now().minusMinutes(10));
    orderRepository.deleteAll(unpaidOrders);
  }


}
