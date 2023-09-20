package com.example.testtask.mapper;

import com.example.testtask.dto.OrderDTO;
import com.example.testtask.entity.Order;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

/**
 * Mapper for {@link Order}.
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {

  /**
   * Map from Order to it`s DTO.
   *
   * @param order entity to be mapped.
   * @return DTO instance.
   */
  @Mapping(target = "id", source = "id",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "paid", source = "paid",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "author", source = "author",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "createdAt", source = "createdAt",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  OrderDTO orderToOrderDTO(Order order);

  /**
   * Map from DTO to Order.
   *
   * @param order DTO to be mapped.
   * @return instance of entity.
   */
  @Mapping(target = "id", source = "id",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "paid", source = "paid",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "author", source = "author",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "createdAt", source = "createdAt",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  Order orderDTOToOrder(OrderDTO order);

  /**
   * Map from Order list to it`s DTO list.
   *
   * @param list entities to be mapped.
   * @return DTO instances list.
   */
  @Mapping(target = "id", source = "id",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "paid", source = "paid",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "author", source = "author",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "createdAt", source = "createdAt",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  List<OrderDTO> orderListToOrderDTOList(List<Order> list);

  /**
   * Map from DTO list to entities list.
   *
   * @param list DTOs to be mapped.
   * @return entities list.
   */
  @Mapping(target = "id", source = "id",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "paid", source = "paid",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "author", source = "author",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  @Mapping(target = "createdAt", source = "createdAt",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
  List<Order> orderDTOListToOrderList(List<OrderDTO> list);

}
