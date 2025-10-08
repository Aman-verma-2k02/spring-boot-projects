package com.amverma.order_service.dto;

import com.amverma.order_service.entity.Order;
import com.amverma.order_service.entity.OrderItem;
import com.amverma.order_service.enums.OrderStatus;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DTOMapper {
    Order toEntity(OrderRequestDTO orderRequestDto);
    List<OrderItem> toEntity(List<OrderItemRequestDTO> orderItemRequestDTOList);
    OrderItem toEntity(OrderItemRequestDTO orderItemRequestDto);

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "orderItemList", target = "orderItemList")
    OrderResponseDTO toDto(Order order);

    List<OrderItemResponseDTO> toDto(List<OrderItem> orderItems);

    @Mapping(source = "id", target = "orderItemId")
    OrderItemResponseDTO toDto(OrderItem orderItem);

    @AfterMapping
    default void setOrderInOrderItems(@MappingTarget Order order, OrderRequestDTO orderRequestDto) {
        for (OrderItem orderItem : order.getOrderItemList()) {
            orderItem.setOrder(order);
        }
        order.setStatus(OrderStatus.PLACED);
    }
}
