package com.example.testtask.web;

import com.example.testtask.dto.OrderDTO;
import com.example.testtask.mapper.OrderMapper;
import com.example.testtask.service.OrderService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

  private final OrderMapper orderMapper;

  private final OrderService orderService;

  @PostMapping("/create")
  public OrderDTO createOrder(@Validated @RequestBody @NotNull final OrderDTO orderDTO,
                              @AuthenticationPrincipal final User user) {
    var order = orderMapper.orderDTOToOrder(orderDTO);
    order = orderService.createOrder(order, user);
    final var result = orderMapper.orderToOrderDTO(order);
    return result;
  }

  @GetMapping("/{orderId}")
  public OrderDTO getOrder(@PathVariable final int orderId) {
    final var order = orderService.getOrder(orderId);
    final var result = orderMapper.orderToOrderDTO(order);
    return result;
  }

  @DeleteMapping("/{orderId}")
  public boolean deleteOrder(@PathVariable final int orderId) {
    final var result = orderService.deleteOrder(orderId);
    return result;
  }

  @GetMapping("/page/{pageNumber}")
  public List<OrderDTO> getOrders(@PathVariable @Min(value = 0) final int pageNumber) {
    final var orders = orderService.getOrders(pageNumber);
    final var result = orderMapper.orderListToOrderDTOList(orders);
    return result;
  }

  @PostMapping("/pay/{orderId}")
  public boolean payOrder(@PathVariable final int orderId,
                          @AuthenticationPrincipal User user) {
    final var result = orderService.payOrder(orderId, user.getUsername());
    return result;
  }
}
