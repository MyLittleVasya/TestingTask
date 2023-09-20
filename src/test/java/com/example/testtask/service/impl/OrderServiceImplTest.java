package com.example.testtask.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.testtask.entity.Order;
import com.example.testtask.entity.ProductOrder;
import com.example.testtask.entity.repository.OrderRepository;
import com.example.testtask.handler.exception.AlreadyExistsException;
import com.example.testtask.handler.exception.NotFoundException;
import com.example.testtask.handler.exception.OperationNotAllowedException;
import com.example.testtask.service.ProductService;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private ProductService productService;

  @InjectMocks
  private OrderServiceImpl orderService;

  @Test
  void testCreateOrder() {
    final var order = new Order();
    order.setOrderItems(List.of(
        new ProductOrder(1, 10),
        new ProductOrder(2, 20)
    ));
    final var user = new User("username", "password", new HashSet<>());

    when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());
    when(orderRepository.save(order)).thenReturn(order);

    Order createdOrder = orderService.createOrder(order, user);

    assertNotNull(createdOrder);
    assertEquals(user.getUsername(), createdOrder.getAuthor());
    assertFalse(createdOrder.isPaid());

    verify(productService, times(order.getOrderItems().size())).changeQuantity(anyInt(), anyInt());
    verify(orderRepository, times(1)).save(order);
  }

  @Test
  void testCreateOrderAlreadyExists() {
    Order order = new Order();
    final var user = new User("username", "password", new HashSet<>());

    when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

    assertThrows(AlreadyExistsException.class, () -> orderService.createOrder(order, user));
  }

  @Test
  void testGetOrder() {
    int orderId = 1;
    Order order = new Order();

    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

    Order retrievedOrder = orderService.getOrder(orderId);

    assertNotNull(retrievedOrder);
    assertEquals(order, retrievedOrder);
  }

  @Test
  void testGetOrderNotFound() {
    int orderId = 1;

    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> orderService.getOrder(orderId));
  }

  @Test
  void testGetOrders() {
    int page = 1;
    List<Order> orders = List.of(new Order(), new Order());

    when(orderRepository.findAllBy(any())).thenReturn(orders);

    List<Order> retrievedOrders = orderService.getOrders(page);

    assertNotNull(retrievedOrders);
    assertEquals(orders, retrievedOrders);
  }

  @Test
  void testDeleteOrder() {
    int orderId = 1;
    Order order = new Order();

    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

    boolean result = orderService.deleteOrder(orderId);

    assertTrue(result);
    verify(orderRepository, times(1)).delete(order);
  }

  @Test
  void testDeleteOrderNotFound() {
    int orderId = 1;

    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

    boolean result = orderService.deleteOrder(orderId);

    assertFalse(result);
    verify(orderRepository, never()).delete(any());
  }

  @Test
  void testPayOrder() {
    int orderId = 1;
    String author = "username";
    Order order = new Order();
    order.setAuthor(author);

    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

    boolean result = orderService.payOrder(orderId, author);

    assertTrue(result);
    assertTrue(order.isPaid());
    verify(orderRepository, times(1)).save(order);
  }

  @Test
  void testPayOrderNotFound() {
    int orderId = 1;
    String author = "username";

    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> orderService.payOrder(orderId, author));
  }

  @Test
  void testPayOrderNotAllowed() {
    int orderId = 1;
    String author = "author";
    Order order = new Order();
    order.setAuthor("different_author");

    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

    assertThrows(OperationNotAllowedException.class, () -> orderService.payOrder(orderId, author));
  }
}


