package com.example.testtask.service;

import com.example.testtask.entity.Order;
import com.example.testtask.handler.exception.AlreadyExistsException;
import com.example.testtask.handler.exception.NotFoundException;
import com.example.testtask.handler.exception.OperationNotAllowedException;
import jakarta.annotation.Nonnull;
import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 * Service for business logic linked with {@link Order}.
 */
public interface OrderService {

  /**
   * Create order if not already exists.
   *
   * @param order  basic order info.
   * @param author author of order.
   * @return created order instance.
   * @throws AlreadyExistsException can be thrown if trying to insert order with id.
   */
  @Nonnull
  Order createOrder(@Nonnull Order order, @Nonnull User author) throws AlreadyExistsException;


  /**
   * Get order if exists.
   *
   * @param orderId order to get.
   * @return order instance.
   * @throws NotFoundException thrown if order doesn`t exist.
   */
  @Nonnull
  Order getOrder(int orderId) throws NotFoundException;

  /**
   * Get page of orders.
   *
   * @param page number of page.
   * @return page of orders or empty list if page is empty.
   */
  @Nonnull
  List<Order> getOrders(int page);


  /**
   * Deletes order if exists.
   *
   * @param orderId order to be deleted.
   * @return boolean value of result.
   */
  boolean deleteOrder(int orderId);

  /**
   * Mark order as paid.
   *
   * @param orderId order to be paid.
   * @param author  author of this action.
   * @return boolean value of result.
   * @throws OperationNotAllowedException thrown when order paid by not the author of order.
   * @throws NotFoundException            thrown when trying to pay non existing order.
   */
  boolean payOrder(int orderId, @Nonnull String author)
      throws OperationNotAllowedException, NotFoundException;

  /**
   * Scheduled method for deleting unpaid orders.
   */
  void removeUnpaid();

}
